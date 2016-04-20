package tv.vego.dp.util;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tv.vego.dp.vo.SchedulingTaskInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 定时任务工具类（调度管理器）
 * <p/>
 * Author   : wangxp
 * <p/>
 * DateTime : 2016/1/12 11:56
 */
public class QuartzTaskUtil
{
    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzTaskUtil.class);

    private static final SchedulerFactory SCHEDULER_FACTORY = new StdSchedulerFactory();

    private static String JOB_GROUP_NAME_DEFAULT = "JOBGROUP_NAME_DEFAULT";

    private static String JOB_GROUP_NAME_TEMPORARY = "JOBGROUP_NAME_TEMPORARY";

    private static String TRIGGER_GROUP_NAME_DEFAULT = "TRIGGERGROUP_NAME_DEFAULT";

    private static String TRIGGER_GROUP_NAME_TEMPORARY = "TRIGGERGROUP_NAME_TEMPORARY";

    /**
     * 添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     *
     * @param jobName        任务名
     * @param cls            任务类
     * @param cronExpression 时间设置，参考quartz说明文档
     */
    public static void addJob(String jobName, Class cls, String cronExpression, Map<String, Object> params)
    {
        try
        {
            Scheduler scheduler = SCHEDULER_FACTORY.getScheduler();

            // 创建Job信息
            JobDetail jobDetail = JobBuilder.newJob(cls).withIdentity(jobName, JOB_GROUP_NAME_DEFAULT).build();

            // 任务参数
            if (MapUtils.isNotEmpty(params))
            {
                jobDetail.getJobDataMap().putAll(params);
            }

            // 表达式调度构建器
            CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

            // 构建trigger
            CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME_DEFAULT).withSchedule(scheduleBuilder)
                    .build();

            // 调度Job
            scheduler.scheduleJob(jobDetail, trigger);
        }
        catch (Exception e)
        {
            LOGGER.error("增加调度任务出错", e);

            throw new RuntimeException(e);
        }
    }

    /**
     * 即时调度任务
     *
     * @param jobName 任务名称
     * @param cls     任务执行类
     * @param params  任务参数
     */
    public static void schedulingJobNow(String jobName, Class cls, Map<String, Object> params)
    {
        try
        {
            Scheduler scheduler = SCHEDULER_FACTORY.getScheduler();

            JobDetail temporaryJobDetail = JobBuilder.newJob(cls).withIdentity(jobName, JOB_GROUP_NAME_TEMPORARY).build();

            // 任务参数
            if (MapUtils.isNotEmpty(params))
            {
                temporaryJobDetail.getJobDataMap().putAll(params);
            }

            Trigger emporaryTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, TRIGGER_GROUP_NAME_TEMPORARY).startNow()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()).build();

            scheduler.scheduleJob(temporaryJobDetail, emporaryTrigger);
        }
        catch (SchedulerException e)
        {
            LOGGER.error("即时调度任务出错", e);

            throw new RuntimeException(e);
        }
    }

    /**
     * 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     *
     * @param jobName        任务名称
     * @param cronExpression 时间设置，参考quartz说明文档
     */
    public static void modifyJobCronTime(String jobName, String cronExpression)
    {
        try
        {
            Scheduler scheduler = SCHEDULER_FACTORY.getScheduler();

            //获取触发器标识
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName, JOB_GROUP_NAME_DEFAULT);

            //获取触发器trigger
            CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
            if (null != trigger)
            {
                // Trigger已存在，那么更新相应的定时设置
                // 表达式调度构建器
                CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

                // 按新的cronExpression表达式重新构建trigger
                trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();

                // 按新的trigger重新设置job执行
                scheduler.rescheduleJob(triggerKey, trigger);
            }
        }
        catch (Exception e)
        {
            LOGGER.error("修改调度任务时间出错", e);

            throw new RuntimeException(e);
        }
    }

    /**
     * 移除一个任务(使用默认的任务组名，触发器名，触发器组名)
     *
     * @param jobName 任务名称
     */
    public static void removeJob(String jobName)
    {
        try
        {
            Scheduler scheduler = SCHEDULER_FACTORY.getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME_DEFAULT);
            scheduler.deleteJob(jobKey);
        }
        catch (Exception e)
        {
            LOGGER.error("移除调度任务出错", e);

            throw new RuntimeException(e);
        }
    }

    /**
     * 暂停任务
     *
     * @param jobName 任务名称
     */
    public void pauseJob(String jobName)
    {
        try
        {
            Scheduler scheduler = SCHEDULER_FACTORY.getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME_DEFAULT);

            scheduler.pauseJob(jobKey);
        }
        catch (Exception e)
        {
            LOGGER.error("暂停调度任务出错", e);

            throw new RuntimeException(e);
        }
    }

    /**
     * 恢复任务
     *
     * @param jobName 任务名称
     */
    public void resumeJob(String jobName)
    {
        try
        {
            Scheduler scheduler = SCHEDULER_FACTORY.getScheduler();
            JobKey jobKey = JobKey.jobKey(jobName, JOB_GROUP_NAME_DEFAULT);

            scheduler.resumeJob(jobKey);
        }
        catch (Exception e)
        {
            LOGGER.error("恢复调度任务出错", e);

            throw new RuntimeException(e);
        }
    }

    /**
     * 启动所有定时任务
     */
    public static void startupJobs()
    {
        try
        {
            Scheduler sched = SCHEDULER_FACTORY.getScheduler();
            sched.start();
        }
        catch (Exception e)
        {
            LOGGER.error("启动调度器出错", e);

            throw new RuntimeException(e);
        }
    }

    public static List<SchedulingTaskInfo> listSchedulingTask()
    {
        List<SchedulingTaskInfo> schedulingTaskInfos = new ArrayList<>();

        try
        {
            Scheduler scheduler = SCHEDULER_FACTORY.getScheduler();
            Set<JobKey> jobKeys = scheduler.getJobKeys(GroupMatcher.anyJobGroup());

            if (CollectionUtils.isNotEmpty(jobKeys))
            {
                for (JobKey jobKey : jobKeys)
                {
                    for (Trigger trigger : scheduler.getTriggersOfJob(jobKey))
                    {
                        SchedulingTaskInfo schedulingTaskInfo = new SchedulingTaskInfo();
                        schedulingTaskInfo.setJobGroup(jobKey.getGroup());
                        schedulingTaskInfo.setJobName(jobKey.getName());

                        TriggerKey triggerKey = trigger.getKey();
                        schedulingTaskInfo.setTriggerGroup(triggerKey.getGroup());
                        schedulingTaskInfo.setTriggerName(triggerKey.getName());

                        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
                        schedulingTaskInfo.setJobClassName(jobDetail.getJobClass().getName());
                        schedulingTaskInfo.setDurable(jobDetail.isDurable());
                        schedulingTaskInfo.setPersistJobDataAfterExecution(jobDetail.isPersistJobDataAfterExecution());
                        schedulingTaskInfo.setConcurrentExectionDisallowed(jobDetail.isConcurrentExectionDisallowed());
                        schedulingTaskInfo.setJobData(jobDetail.getJobDataMap());

                        schedulingTaskInfo.setPreviousFireTime(trigger.getPreviousFireTime());
                        schedulingTaskInfo.setNextFireTime(trigger.getNextFireTime());

                        schedulingTaskInfos.add(schedulingTaskInfo);
                    }
                }
            }

        }
        catch (SchedulerException e)
        {
            LOGGER.error("获取正在调度的任务出错", e);

            throw new RuntimeException(e);
        }

        return schedulingTaskInfos;
    }

    /**
     * 关闭所有定时任务
     */
    public static void shutdownJobs()
    {
        try
        {
            Scheduler sched = SCHEDULER_FACTORY.getScheduler();
            if (!sched.isShutdown())
            {
                sched.shutdown();
            }
        }
        catch (Exception e)
        {
            LOGGER.error("停止调度器出错", e);

            throw new RuntimeException(e);
        }
    }
}
