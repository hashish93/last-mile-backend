package com.appzoneltd.lastmile.microservice.ondemandworkflow.job;


import java.io.IOException;
import java.util.List;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.HubConfigurationRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.HubConfigurationEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.ActionNeededBase;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by hashish on 4/5/2017.
 */
@Component
@Scope("singleton")
public class PackageTimoutSchedularConsumer {

 
   
    @Autowired
    private HubConfigurationRepository hubConfigurationRepository;
    
    @Autowired
    private TimeOutJobScheduler timeOutJobScheduler;
    
    @KafkaListener(topics = {"AUTOMATIC_ACTION_NEEDED"})
    private void configConsumer(@Payload String payload) throws IOException, SchedulerException {
        final ObjectMapper objectMapper = new ObjectMapper();
        ActionNeededBase actionNeededBase = objectMapper.readValue(payload , ActionNeededBase.class);
       
        if(actionNeededBase !=null){
        	if(actionNeededBase.isSwitched()){
        		MyPrinter.print("AUTOMATIC_ACTION_NEEDED", "start timer with package id "+actionNeededBase.getPackageId());
        		List<HubConfigurationEntity> configurationEntities= hubConfigurationRepository.findSystemConfigurationForHub(actionNeededBase.getHubId(), 1L);
            	if(configurationEntities !=null && configurationEntities.size()>0){
            		HubConfigurationEntity hubConfigurationEntity = configurationEntities.get(0);
            	int minites = hubConfigurationEntity.getValue().intValue();
            	timeOutJobScheduler.restartJob(actionNeededBase.getPackageId(),minites);
            	}
        		
        	}else{
        		MyPrinter.print("AUTOMATIC_ACTION_NEEDED", "stop timer from exection with package id "+actionNeededBase.getPackageId());
        		timeOutJobScheduler.stopJob(actionNeededBase.getPackageId());
        	}
        }    	

    }
}
