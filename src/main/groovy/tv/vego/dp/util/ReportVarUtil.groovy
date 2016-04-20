package tv.vego.dp.util

import tv.vego.dp.model.QuartzTaskParam

/**
 * 报表变量解析
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/1/15 9:51
 */
class ReportVarUtil
{
    static Object getEvalValue(Object taskParam)
    {
        if (taskParam instanceof QuartzTaskParam)
        {
            def rawParamValue = taskParam.getParamValue()
            if (ParamConstant.QUARTZ_TASK_PARAM_TYPE_TXT.equalsIgnoreCase(taskParam.getParamType()))
            {
                return rawParamValue
            }
            else if (ParamConstant.QUARTZ_TASK_PARAM_TYPE_GROOVY.equalsIgnoreCase(taskParam.getParamType()))
            {
                def shell = new GroovyShell()

                return shell.evaluate(rawParamValue)
            }
            else
            {
                throw new RuntimeException("定时任务参数类型【" + taskParam.getParamType() + "】错误")
            }
        }
        else
        {
            return taskParam
        }

    }
}
