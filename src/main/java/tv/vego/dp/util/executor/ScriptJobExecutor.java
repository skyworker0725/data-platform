package tv.vego.dp.util.executor;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tv.vego.dp.model.QuartzTaskDefine;
import tv.vego.dp.util.ParamConstant;

import javax.script.Bindings;
import javax.script.ScriptContext;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * 脚本定时任务执行器
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/1/12 10:03
 */
public class ScriptJobExecutor implements Job
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ScriptJobExecutor.class);

    private static final ScriptEngineManager SCRIPT_ENGINE_MANAGER = new ScriptEngineManager();

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException
    {
        Map<String, Object> params = new HashMap<>();

        //1、获取任务入参
        params.putAll(context.getMergedJobDataMap());

        //2、获取执行任务信息
        QuartzTaskDefine taskDefine = (QuartzTaskDefine) params.get(ParamConstant.EXEC_TASK_INFO_KEY);
        String execScriptPath = taskDefine.getTaskPath();

        int executedTimes = 0;
        boolean execSuccess = false;

        while (!execSuccess && needExec(taskDefine, executedTimes))
        {
            try
            {
                executedTimes++;

                //3、构建脚本执行环境
                ScriptEngine engine = SCRIPT_ENGINE_MANAGER.getEngineByExtension(execScriptPath.substring(execScriptPath.lastIndexOf(".") + 1));
                Bindings bindings = engine.createBindings();
                bindings.putAll(params);
                engine.setBindings(bindings, ScriptContext.ENGINE_SCOPE);

                //4、执行脚本
                LOGGER.info("执行任务【{}】第【{}】次开始", execScriptPath, executedTimes);
                engine.eval(new InputStreamReader(getClass().getClassLoader().getResourceAsStream(execScriptPath)));
                execSuccess = true;
                LOGGER.info("执行任务【{}】第【{}】次结束", execScriptPath, executedTimes);
            }
            catch (Exception e)
            {
                LOGGER.error("任务【" + taskDefine.getTaskName() + "】执行出错，执行脚本路径为【" + execScriptPath + "】，脚本参数为【" + params + "】", e);
            }
        }
    }


    private boolean needExec(QuartzTaskDefine taskDefine, int executedTimes) throws JobExecutionException
    {
        if (executedTimes == 0)
        {
            return true;
        }
        else if (ParamConstant.TASK_FAILED_RETRY_ENABLE.equals(taskDefine.getFailedRetry())
                && taskDefine.getRetryInterval() > 0 && (executedTimes < taskDefine.getRetryTimes() || 0 == taskDefine.getRetryTimes()))
        {
            try
            {
                Thread.sleep(1000 * taskDefine.getRetryInterval());
            }
            catch (InterruptedException e)
            {
                throw new JobExecutionException(e);
            }

            return true;
        }

        return false;
    }
}
