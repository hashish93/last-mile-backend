package com.appzoneltd.lastmile.microservice.manualdistribution.job;

import com.appzoneltd.lastmile.microservice.manualdistribution.Utility.DateUtility;
import com.appzoneltd.lastmile.microservice.manualdistribution.cron.QuartzJob;
import com.appzoneltd.lastmile.microservice.manualdistribution.dao.SystemConfigRepo;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Date;

import static org.quartz.CronScheduleBuilder.dailyAtHourAndMinute;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * Created by hashish on 4/5/2017.
 */

@Component
@Scope("singleton")
public class SystemConfigJob {

    @Autowired
    private SystemConfigRepo systemConfigRepo;
    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    void construct() throws SchedulerException {
        String automatic_value = systemConfigRepo.findOne(15L).getTextValue();
        BigDecimal distributionMode = systemConfigRepo.findOne(14L).getValue();
        System.out.println(automatic_value);
        System.out.println(distributionMode);

        if (BigDecimal.ONE.equals(distributionMode)) {
            //TODO Run distribution cronjob automatic
            System.out.println("AUTOMATIC");
//            Integer[] dateInt = DateUtility.StringToHourAndMinute(automatic_value);
            Date date = DateUtility.get24HourTimeFromString(automatic_value);
            System.out.println(">>> HOUR:" + date.getHours());
            System.out.println(">>> MINS:" + date.getMinutes());
            startJob(date.getHours(), date.getMinutes());

        }

        if (BigDecimal.ZERO.equals(distributionMode)) {
            //TODO stop autotmatic cron job OR don't run
            System.out.println("MANUAL");
        }

    }

    public void startJob(int hours, int mins) throws SchedulerException {
        JobDetail job = newJob(QuartzJob.class)
                .withIdentity("quartzTest", "group1")
                .build();

        Trigger trigger = newTrigger()
                .withIdentity(TriggerKey.triggerKey("MyTrigger"))
                .startNow()
                .withSchedule(dailyAtHourAndMinute(hours, mins))
                .build();


        scheduler.scheduleJob(job, trigger);
    }

    public void stopJob() throws SchedulerException {
        scheduler.pauseJob(JobKey.jobKey("quartzTest" , "group1"));
        scheduler.unscheduleJob(TriggerKey.triggerKey("MyTrigger"));
    }

    public void restartJob(int hours , int minutes) throws SchedulerException {
        stopJob();
        startJob(hours, minutes);
    }

}
