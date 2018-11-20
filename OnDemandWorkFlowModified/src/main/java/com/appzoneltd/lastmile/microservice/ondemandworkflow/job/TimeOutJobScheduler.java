package com.appzoneltd.lastmile.microservice.ondemandworkflow.job;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.quartz.DateBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.DateUtility;

/**
 * Created by hashish on 4/5/2017.
 */

@Component
@Scope("singleton")
public class TimeOutJobScheduler {

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    void construct() throws SchedulerException {
     
    }

    @SuppressWarnings("deprecation")
	public void startJob(Long packageId , int minutes) throws SchedulerException {
        JobDetail job = newJob(QuartzJob.class)
                .withIdentity("job_"+packageId, "jobs")
                .build();
        job.getJobDataMap().put("packageId", packageId);
        Date scheduledDate=DateUtility.addMinutesToDate(new Date(), minutes);
        
        Trigger trigger = newTrigger()
                .withIdentity(TriggerKey.triggerKey("JobTrigger_"+packageId))
                .startAt(DateBuilder.todayAt(scheduledDate.getHours(), scheduledDate.getMinutes(), scheduledDate.getSeconds()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withRepeatCount(0))
                .build();

        scheduler.scheduleJob(job, trigger);
    }

    public void stopJob(Long packageId) throws SchedulerException {
    	System.out.println(">>> STOP JOB "+"job_"+packageId);
        scheduler.pauseJob(JobKey.jobKey("job_"+packageId , "jobs"));
        scheduler.unscheduleJob(TriggerKey.triggerKey("JobTrigger_"+packageId));
    }

    public void restartJob(Long packageId , int minutes) throws SchedulerException {
        stopJob(packageId);
        startJob(packageId, minutes);
    }

}
