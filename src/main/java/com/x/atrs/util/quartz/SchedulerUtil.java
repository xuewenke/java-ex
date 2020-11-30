
package com.x.atrs.util.quartz;

import com.x.atrs.util.time.LocalDateUtil;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Map;

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * 定时任务执行器
 */
public class SchedulerUtil {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	private static SchedulerUtil instance;
	private static SchedulerFactory sf;
	private static Scheduler sched;

	private SchedulerUtil() throws Exception {
		sf = new StdSchedulerFactory();
		sched = sf.getScheduler();
	}

	public synchronized static SchedulerUtil getInstance() throws Exception {
		if (instance == null) {
			instance = new SchedulerUtil();
		}
		return instance;
	}

	/**
	 * 添加定时任务
	 *
	 * @param jobName  任务名
	 * @param jobClass 要执行任务的类，根据需要实现 @BaseJob 或者 @BaseConcurrentJob 接口
	 * @param cron     定时表达式
	 * @return
	 * @throws Exception
	 * @author sxb
	 */
	public boolean addNewJob(String jobName, Class<? extends Job> jobClass, String cron) throws Exception {
		if (sched.checkExists(JobKey.jobKey(jobName))) {
			throw new Exception("定时任务-" + jobName + "已存在");
		}

		JobDetail job = newJob(jobClass).withIdentity(jobName, Scheduler.DEFAULT_GROUP).build();
		CronTrigger trigger = newTrigger().withIdentity(jobName, Scheduler.DEFAULT_GROUP).withSchedule(cronSchedule(cron))
				.build();
		Date ft = sched.scheduleJob(job, trigger);
		sched.start();

		if (logger.isDebugEnabled()) {
			logger.debug("job {} will run at {}", jobName, ft);
		}
		return true;
	}

	/**
	 * 添加定时任务
	 *
	 * @param jobName    任务名
	 * @param jobClass   要执行任务的类，根据需要实现 @BaseJob 或者 @BaseConcurrentJob 接口
	 * @param cron       定时表达式
	 * @param jobDataMap 存储定时任务参数
	 * @return
	 * @throws Exception
	 * @author sxb
	 */
	public boolean addNewJob(String jobName, Class<? extends Job> jobClass, String cron, Map<String, Object> jobDataMap) throws Exception {
		if (sched.checkExists(JobKey.jobKey(jobName))) {
			throw new Exception("定时任务-" + jobName + "已存在");
		}

		JobDetail job = newJob(jobClass).withIdentity(jobName, Scheduler.DEFAULT_GROUP).build();
		for (Map.Entry<String, Object> entry : jobDataMap.entrySet()) {
			job.getJobDataMap().put(entry.getKey(), entry.getValue());
		}
		CronTrigger trigger = newTrigger().withIdentity(jobName, Scheduler.DEFAULT_GROUP).withSchedule(cronSchedule(cron))
				.build();
		Date ft = sched.scheduleJob(job, trigger);
		sched.start();

		if (logger.isDebugEnabled()) {
			logger.debug("job {} will run at {}", jobName, ft);
		}
		return true;
	}

	public boolean addNewJob(String jobName, Class<? extends Job> jobClass, String cron, Map<String, Object> jobDataMap, Date startTime, Date endTime) throws Exception {
		if (sched.checkExists(JobKey.jobKey(jobName))) {
			throw new Exception("定时任务-" + jobName + "已存在");
		}

		JobDetail job = newJob(jobClass).withIdentity(jobName, Scheduler.DEFAULT_GROUP).build();
		for (Map.Entry<String, Object> entry : jobDataMap.entrySet()) {
			job.getJobDataMap().put(entry.getKey(), entry.getValue());
		}
		logger.debug(" job start time ---------->" + LocalDateUtil.toTimeStr(startTime.getTime()));
		CronTrigger trigger = newTrigger().withIdentity(jobName, Scheduler.DEFAULT_GROUP).startAt(startTime).withSchedule(cronSchedule(cron)).endAt(endTime)
				.build();
		Date ft = sched.scheduleJob(job, trigger);
		sched.start();

		if (logger.isDebugEnabled()) {
			logger.debug("job {} will run at {}", jobName, ft);
		}
		return true;
	}


	/**
	 * 分钟周期执行
	 *
	 * @param jobName
	 * @param jobClass
	 * @param jobDataMap
	 * @param startTime
	 * @param endTime
	 * @param minInterval
	 * @return
	 * @throws Exception
	 */
	public boolean addNewJobWithMin(String jobName, Class<? extends Job> jobClass, Map<String, Object> jobDataMap, Date startTime, Date endTime, Integer minInterval) throws Exception {
		if (sched.checkExists(JobKey.jobKey(jobName))) {
			throw new Exception("定时任务-" + jobName + "已存在");
		}
		JobDetail job = newJob(jobClass).withIdentity(jobName, Scheduler.DEFAULT_GROUP).build();
		for (Map.Entry<String, Object> entry : jobDataMap.entrySet()) {
			job.getJobDataMap().put(entry.getKey(), entry.getValue());
		}
		SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity(jobName, Scheduler.DEFAULT_GROUP).startAt(startTime).withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(minInterval)).endAt(endTime).build();
		Date ft = sched.scheduleJob(job, trigger);
		sched.start();

		if (logger.isDebugEnabled()) {
			logger.debug("job {} will run at {}", jobName, ft);
		}
		return true;
	}


	public boolean addNewJobWithHour(String jobName, Class<? extends Job> jobClass, Map<String, Object> jobDataMap, Date startTime, Date endTime, Integer hourInterval) throws Exception {
		if (sched.checkExists(JobKey.jobKey(jobName))) {
			throw new Exception("定时任务-" + jobName + "已存在");
		}
		JobDetail job = newJob(jobClass).withIdentity(jobName, Scheduler.DEFAULT_GROUP).build();
		for (Map.Entry<String, Object> entry : jobDataMap.entrySet()) {
			job.getJobDataMap().put(entry.getKey(), entry.getValue());
		}
		SimpleTrigger trigger = (SimpleTrigger) newTrigger().withIdentity(jobName, Scheduler.DEFAULT_GROUP).startAt(startTime).withSchedule(SimpleScheduleBuilder.repeatHourlyForever(hourInterval)).endAt(endTime).build();
		Date ft = sched.scheduleJob(job, trigger);
		sched.start();

		if (logger.isDebugEnabled()) {
			logger.debug("job {} will run at {}", jobName, ft);
		}
		return true;
	}


	/**
	 * 执行指定的任务只执行一次, 次数小于或者等于0 只执行一次
	 *
	 * @param jobName
	 * @param jobClass
	 * @param jobDataMap
	 * @param execTime
	 * @param times      重复执行次数
	 * @return
	 * @throws Exception
	 */
	public boolean addNewJobWithExecOneTime(String jobName, Class<? extends Job> jobClass, Map<String, Object> jobDataMap, Date execTime, int times) throws Exception {
		if (sched.checkExists(JobKey.jobKey(jobName))) {
			throw new Exception("定时任务-" + jobName + "已存在");
		}

		JobDetail job = newJob(jobClass).withIdentity(jobName, Scheduler.DEFAULT_GROUP).build();
		for (Map.Entry<String, Object> entry : jobDataMap.entrySet()) {
			job.getJobDataMap().put(entry.getKey(), entry.getValue());
		}
		SimpleTrigger trigger = null;
		if (times <= 0) {
			trigger = (SimpleTrigger) newTrigger().withIdentity(jobName, Scheduler.DEFAULT_GROUP).startAt(execTime).build();
		} else {
			trigger = (SimpleTrigger) newTrigger().withIdentity(jobName, Scheduler.DEFAULT_GROUP).startAt(execTime).withSchedule(SimpleScheduleBuilder.repeatHourlyForTotalCount(times)).build();
		}
		Date ft = sched.scheduleJob(job, trigger);
		sched.start();

		if (logger.isDebugEnabled()) {
			logger.debug("job {} will run at {}", jobName, ft);
		}
		return true;
	}


	/**
	 * 更新执行任务
	 *
	 * @param jobName 定时任务名
	 * @param newCron 新的时间表达式
	 * @return
	 * @throws Exception
	 * @author sxb
	 */
	public boolean updateJob(String jobName, String newCron) throws Exception {
		TriggerKey triggerkey = TriggerKey.triggerKey(jobName);
		CronTrigger trigger = newTrigger().withIdentity(jobName, Scheduler.DEFAULT_GROUP).withSchedule(cronSchedule(newCron))
				.build();
		Date ft = sched.rescheduleJob(triggerkey, trigger);

		if (logger.isDebugEnabled()) {
			logger.debug("job {} will run at {}", jobName, ft);
		}
		return true;
	}

	public void reloadJobs() throws SchedulerException {
		for (JobKey jobKey : sched.getJobKeys(GroupMatcher.anyGroup())) {
			System.out.println("keyName = " + jobKey.getName());
			TriggerKey triggerkey = TriggerKey.triggerKey(jobKey.getName());
			Trigger trigger = sched.getTrigger(triggerkey);
			sched.rescheduleJob(triggerkey, trigger);
		}
	}

	/**
	 * 删除定时任务
	 *
	 * @param jobName 定时任务名
	 * @return
	 * @throws Exception
	 * @author sxb
	 */
	public boolean deleteJob(String jobName) throws Exception {
		sched.deleteJob(JobKey.jobKey(jobName));
		return true;
	}

}
