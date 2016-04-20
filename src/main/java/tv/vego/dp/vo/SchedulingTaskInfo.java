package tv.vego.dp.vo;

import tv.vego.dp.model.BaseSerializer;

import java.util.Date;
import java.util.Map;

/**
 * 调度中的任务信息
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/3/15 14:46
 */
public class SchedulingTaskInfo extends BaseSerializer
{
    private String jobGroup;

    private String jobName;

    private String triggerGroup;

    private String triggerName;

    private String jobClassName;

    private Date previousFireTime;

    private Date nextFireTime;

    private boolean durable;

    private boolean persistJobDataAfterExecution;

    private boolean concurrentExectionDisallowed;

    private Map jobData;

    public String getJobGroup()
    {
        return jobGroup;
    }

    public void setJobGroup(String jobGroup)
    {
        this.jobGroup = jobGroup;
    }

    public String getJobName()
    {
        return jobName;
    }

    public void setJobName(String jobName)
    {
        this.jobName = jobName;
    }

    public String getTriggerGroup()
    {
        return triggerGroup;
    }

    public void setTriggerGroup(String triggerGroup)
    {
        this.triggerGroup = triggerGroup;
    }

    public String getTriggerName()
    {
        return triggerName;
    }

    public void setTriggerName(String triggerName)
    {
        this.triggerName = triggerName;
    }

    public String getJobClassName()
    {
        return jobClassName;
    }

    public void setJobClassName(String jobClassName)
    {
        this.jobClassName = jobClassName;
    }

    public Date getPreviousFireTime()
    {
        return previousFireTime;
    }

    public void setPreviousFireTime(Date previousFireTime)
    {
        this.previousFireTime = previousFireTime;
    }

    public Date getNextFireTime()
    {
        return nextFireTime;
    }

    public void setNextFireTime(Date nextFireTime)
    {
        this.nextFireTime = nextFireTime;
    }

    public boolean isDurable()
    {
        return durable;
    }

    public void setDurable(boolean durable)
    {
        this.durable = durable;
    }

    public boolean isPersistJobDataAfterExecution()
    {
        return persistJobDataAfterExecution;
    }

    public void setPersistJobDataAfterExecution(boolean persistJobDataAfterExecution)
    {
        this.persistJobDataAfterExecution = persistJobDataAfterExecution;
    }

    public boolean isConcurrentExectionDisallowed()
    {
        return concurrentExectionDisallowed;
    }

    public void setConcurrentExectionDisallowed(boolean concurrentExectionDisallowed)
    {
        this.concurrentExectionDisallowed = concurrentExectionDisallowed;
    }

    public Map getJobData()
    {
        return jobData;
    }

    public void setJobData(Map jobData)
    {
        this.jobData = jobData;
    }
}
