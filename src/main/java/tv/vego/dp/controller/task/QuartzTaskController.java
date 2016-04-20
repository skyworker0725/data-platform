package tv.vego.dp.controller.task;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mybatis.mapper.entity.Condition;
import tv.vego.dp.controller.BaseController;
import tv.vego.dp.model.QuartzTaskDefine;
import tv.vego.dp.model.QuartzTaskParam;
import tv.vego.dp.service.QuartzTaskDefineService;
import tv.vego.dp.service.QuartzTaskParamService;
import tv.vego.dp.util.ParamConstant;
import tv.vego.dp.util.QuartzTaskUtil;
import tv.vego.dp.util.executor.ScriptJobExecutor;
import tv.vego.dp.vo.BaseResultInfo;
import tv.vego.dp.vo.SchedulingTaskInfo;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 定时任务管理
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/1/12 11:38
 */
@Controller
@RequestMapping(value = "/mng/task")
public class QuartzTaskController extends BaseController implements InitializingBean, DisposableBean
{
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzTaskController.class);

    @Autowired
    private QuartzTaskDefineService quartzTaskDefineService;

    @Autowired
    private QuartzTaskParamService quartzTaskParamService;

    @RequestMapping(value = "/schedulingNow/{taskId}")
    @ResponseBody
    public BaseResultInfo schedulingTaskNow(@PathVariable(value = "taskId") Long taskId, HttpServletRequest request) throws ClassNotFoundException
    {
        QuartzTaskDefine quartzTaskDefine = quartzTaskDefineService.selectByKey(taskId);

        if (null == quartzTaskDefine)
        {
            return BaseResultInfo.getFailResult("任务ID为【" + taskId + "】的任务信息不存在。");
        }
        else
        {
            Map<String, Object> params = getTaskParams(quartzTaskDefine);

            //任务属性覆盖
            params.putAll(getQueryParams(request));

            String taskName = quartzTaskDefine.getTaskName();
            Integer taskType = quartzTaskDefine.getTaskType();
            if (ParamConstant.QUARTZ_TASK_TYPE_JAVA.equals(taskType))
            {
                QuartzTaskUtil.schedulingJobNow(taskName, Class.forName(quartzTaskDefine.getTaskPath()), params);
            }
            else if (ParamConstant.QUARTZ_TASK_TYPE_SCRIPT.equals(taskType))
            {
                QuartzTaskUtil.schedulingJobNow(taskName, ScriptJobExecutor.class, params);
            }
            else
            {
                LOGGER.error("定时任务【{}】类型【{}】错误", taskName, taskType);
            }

            LOGGER.info("增加即时调度定时任务【{}】成功", taskName);

            return new BaseResultInfo();
        }
    }

    @RequestMapping(value = "/reload/{taskId}")
    @ResponseBody
    public BaseResultInfo reloadQuartzTask(@PathVariable(value = "taskId") Long taskId) throws ClassNotFoundException
    {
        QuartzTaskDefine quartzTaskDefine = quartzTaskDefineService.selectByKey(taskId);

        if (null == quartzTaskDefine)
        {
            return BaseResultInfo.getFailResult("任务ID为【" + taskId + "】的任务信息不存在。");
        }
        else
        {
            removeSchedulingTask(quartzTaskDefine);
            loadQuartzTask(quartzTaskDefine);

            LOGGER.info("重新加载定时任务【{}】成功", quartzTaskDefine.getTaskName());

            return new BaseResultInfo();
        }
    }

    @RequestMapping(value = "/remove/{taskId}")
    @ResponseBody
    public BaseResultInfo removeSchedulingTask(@PathVariable(value = "taskId") Long taskId)
    {
        QuartzTaskDefine quartzTaskDefine = quartzTaskDefineService.selectByKey(taskId);

        if (null == quartzTaskDefine)
        {
            return BaseResultInfo.getFailResult("任务ID为【" + taskId + "】的任务信息不存在。");
        }
        else
        {
            removeSchedulingTask(quartzTaskDefineService.selectByKey(taskId));

            return new BaseResultInfo();
        }
    }

    @RequestMapping(value = "/listSchedulingTask")
    @ResponseBody
    public List<SchedulingTaskInfo> listSchedulingTask()
    {
        return QuartzTaskUtil.listSchedulingTask();
    }

    @Override
    public void afterPropertiesSet() throws Exception
    {
        //加载并启动定时任务
        LOGGER.info("【开始】加载数据库定时任务");

        List<QuartzTaskDefine> quartzTaskDefines = getQuartzValidTasks();
        if (CollectionUtils.isNotEmpty(quartzTaskDefines))
        {
            for (QuartzTaskDefine quartzTaskDefine : quartzTaskDefines)
            {
                loadQuartzTask(quartzTaskDefine);
            }
        }

        LOGGER.info("【完成】加载数据库定时任务");

        QuartzTaskUtil.startupJobs();
    }

    /**
     * 移除调度中的任务
     *
     * @param quartzTaskDefine 任务信息
     */
    private void removeSchedulingTask(QuartzTaskDefine quartzTaskDefine)
    {
        QuartzTaskUtil.removeJob(quartzTaskDefine.getTaskName());

        LOGGER.info("移除定时任务【{}】成功", quartzTaskDefine.getTaskName());
    }

    /**
     * 加载定时任务至调度
     *
     * @param quartzTaskDefine 定时任务定义
     */
    private void loadQuartzTask(QuartzTaskDefine quartzTaskDefine) throws ClassNotFoundException
    {
        String taskName = quartzTaskDefine.getTaskName();
        Integer taskType = quartzTaskDefine.getTaskType();
        String cronExpression = quartzTaskDefine.getCronExpression();

        Map<String, Object> params = getTaskParams(quartzTaskDefine);

        if (ParamConstant.QUARTZ_TASK_TYPE_JAVA.equals(taskType))
        {
            QuartzTaskUtil.addJob(taskName, Class.forName(quartzTaskDefine.getTaskPath()), cronExpression, params);
        }
        else if (ParamConstant.QUARTZ_TASK_TYPE_SCRIPT.equals(taskType))
        {
            QuartzTaskUtil.addJob(taskName, ScriptJobExecutor.class, cronExpression, params);
        }
        else
        {
            LOGGER.error("加载定时任务【{}】类型【{}】错误", taskName, taskType);
        }

        LOGGER.info("增加定时任务【{}】成功", taskName);
    }

    /**
     * 获取任务执行参数
     *
     * @param quartzTaskDefine 任务定义
     *
     * @return 任务执行参数映射
     */
    private Map<String, Object> getTaskParams(QuartzTaskDefine quartzTaskDefine)
    {
        Map<String, Object> params = new HashMap<>();

        //执行任务信息
        params.put(ParamConstant.EXEC_TASK_INFO_KEY, quartzTaskDefine);

        //加载任务参数
        List<QuartzTaskParam> quartzTaskParams = getTaskParams(quartzTaskDefine.getTaskId());
        if (CollectionUtils.isNotEmpty(quartzTaskParams))
        {
            for (QuartzTaskParam quartzTaskParam : quartzTaskParams)
            {
                params.put(quartzTaskParam.getParamName(), quartzTaskParam);
            }
        }
        return params;
    }

    /**
     * 获取可用的任务列表
     */
    private List<QuartzTaskDefine> getQuartzValidTasks()
    {
        Condition cond = new Condition(QuartzTaskDefine.class);
        Condition.Criteria criteria = cond.createCriteria();
        criteria.andEqualTo("taskStatus", ParamConstant.QUARTZ_TASK_STATUS_VALID);

        return quartzTaskDefineService.selectByExample(cond);
    }

    @Override
    public void destroy() throws Exception
    {
        QuartzTaskUtil.shutdownJobs();
    }

    private List<QuartzTaskParam> getTaskParams(Long taskId)
    {
        Condition cond = new Condition(QuartzTaskParam.class);
        Condition.Criteria criteria = cond.createCriteria();
        criteria.andEqualTo("taskId", taskId);

        return quartzTaskParamService.selectByExample(cond);
    }
}
