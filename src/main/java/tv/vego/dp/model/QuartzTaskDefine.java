package tv.vego.dp.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * 定时任务定义
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/1/12 9:41
 */
public class QuartzTaskDefine extends BaseSerializer
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    private String taskName;

    private Integer taskType; //任务类型 1、脚本任务 2、Java任务

    private String taskPath; //当任务类型为脚本任务（1）时，此值为脚本的classpath路径，当任务类型为Java任务（2）时，此值为Java类全限定名

    private String cronExpression;

    private Integer taskStatus; //任务状态 0、禁用 1、启用 2、删除

    private Integer failedRetry; //失败是否重试  0、不重试 1、重试

    private Integer retryTimes; //重试次数，若为0则一直重试

    private Integer retryInterval; //重试时间间隔（秒）

    private Timestamp createTime;

    private Timestamp modifyTime;

    public Long getTaskId()
    {
        return taskId;
    }

    public void setTaskId(Long taskId)
    {
        this.taskId = taskId;
    }

    public String getTaskName()
    {
        return taskName;
    }

    public void setTaskName(String taskName)
    {
        this.taskName = taskName;
    }

    public Integer getTaskType()
    {
        return taskType;
    }

    public void setTaskType(Integer taskType)
    {
        this.taskType = taskType;
    }

    public String getTaskPath()
    {
        return taskPath;
    }

    public void setTaskPath(String taskPath)
    {
        this.taskPath = taskPath;
    }

    public String getCronExpression()
    {
        return cronExpression;
    }

    public void setCronExpression(String cronExpression)
    {
        this.cronExpression = cronExpression;
    }

    public Integer getTaskStatus()
    {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus)
    {
        this.taskStatus = taskStatus;
    }

    public Integer getFailedRetry()
    {
        return failedRetry;
    }

    public void setFailedRetry(Integer failedRetry)
    {
        this.failedRetry = failedRetry;
    }

    public Integer getRetryTimes()
    {
        return retryTimes;
    }

    public void setRetryTimes(Integer retryTimes)
    {
        this.retryTimes = retryTimes;
    }

    public Integer getRetryInterval()
    {
        return retryInterval;
    }

    public void setRetryInterval(Integer retryInterval)
    {
        this.retryInterval = retryInterval;
    }

    public Timestamp getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime)
    {
        this.createTime = createTime;
    }

    public Timestamp getModifyTime()
    {
        return modifyTime;
    }

    public void setModifyTime(Timestamp modifyTime)
    {
        this.modifyTime = modifyTime;
    }
}
