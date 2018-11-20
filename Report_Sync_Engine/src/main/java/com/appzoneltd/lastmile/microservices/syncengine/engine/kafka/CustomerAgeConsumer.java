package com.appzoneltd.lastmile.microservices.syncengine.engine.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservices.syncengine.engine.service.CustomerAgeService;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.SyncEngineDao;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.SyncEngineEntity;

@Component
public class CustomerAgeConsumer {
	
	@Autowired
	private SyncEngineDao syncEngineDao;
	
	@Autowired
	private CustomerAgeService customerAgeService;

	@KafkaListener(topics = "CUSTOMER_AGE_TOPIC")
	public void executeCustomerAgeTopicConsumer(@Payload String payload){
			
		
		System.out.println("***************************************************");
		System.out.println("*******CUSTOMER TYPE CONSUMER *********");
		System.out.println("***************************************************");		
		SyncEngineEntity syncEngineEntity = syncEngineDao.findOne(2L);
		if(syncEngineEntity !=null){
			customerAgeService.getAllCustomerDetails(syncEngineEntity.getLastSyncTime());
		}
    	
	}
}