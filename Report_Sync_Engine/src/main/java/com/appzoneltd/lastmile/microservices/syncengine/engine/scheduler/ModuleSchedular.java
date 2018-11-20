package com.appzoneltd.lastmile.microservices.syncengine.engine.scheduler;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;


import javax.annotation.PostConstruct;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservices.syncengine.engine.job.QuartzJob;
import com.appzoneltd.lastmile.microservices.syncengine.engine.model.ModuleExecutionModel;


@Component
public class ModuleSchedular {

    @Autowired
    private Scheduler scheduler;

    
    @PostConstruct
    void construct() throws SchedulerException {
     
    }

    
	public void startJob(ModuleExecutionModel moduleExecution) throws SchedulerException {
        
		JobDetail job = newJob(QuartzJob.class)
                .withIdentity("module_"+moduleExecution.getModuleName(), "modules")
                .build();      
		 job.getJobDataMap().put("moduleExecution", moduleExecution);
		SimpleScheduleBuilder.simpleSchedule();
		Trigger trigger = newTrigger()
                .withIdentity(TriggerKey.triggerKey("JobTrigger_"+moduleExecution.getModuleName()))
                .startNow()
                .withSchedule(SimpleScheduleBuilder.repeatSecondlyForever(moduleExecution.getSeconds().intValue()))
                .build();

        scheduler.scheduleJob(job, trigger);
    }

    public void stopJob(ModuleExecutionModel moduleExecution) throws SchedulerException {
    	System.out.println(">>> STOP JOB "+"module_"+moduleExecution.getModuleName());
        scheduler.pauseJob(JobKey.jobKey("module_"+moduleExecution.getModuleName() , "modules"));
        scheduler.unscheduleJob(TriggerKey.triggerKey("JobTrigger_"+moduleExecution.getModuleName()));
    }

    public void restartJob(ModuleExecutionModel moduleExecution) throws SchedulerException {
        stopJob(moduleExecution);
        startJob(moduleExecution);
    }

}
