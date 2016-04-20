package tv.vego.dp.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 定时任务参数
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/1/14 16:23
 */
public class QuartzTaskParam extends BaseSerializer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paramId;

    private Long taskId;

    private String paramName;

    private String paramValue;

    private String paramType;

    public Long getParamId()
    {
        return paramId;
    }

    public void setParamId(Long paramId)
    {
        this.paramId = paramId;
    }

    public Long getTaskId()
    {
        return taskId;
    }

    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public String getParamName()
    {
        return paramName;
    }

    public void setParamName(String paramName)
    {
        this.paramName = paramName;
    }

    public String getParamValue()
    {
        return paramValue;
    }

    public void setParamValue(String paramValue)
    {
        this.paramValue = paramValue;
    }

    public String getParamType()
    {
        return paramType;
    }

    public void setParamType(String paramType)
    {
        this.paramType = paramType;
    }
}
