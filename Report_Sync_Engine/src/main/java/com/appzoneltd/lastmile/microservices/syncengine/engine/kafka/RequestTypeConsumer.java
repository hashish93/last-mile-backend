package com.appzoneltd.lastmile.microservices.syncengine.engine.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservices.syncengine.engine.service.RequestTypeService;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.SyncEngineDao;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.SyncEngineEntity;

@Component
public class RequestTypeConsumer {
	@Autowired
	private SyncEngineDao syncEngineDao;
	
	@Autowired
	private RequestTypeService requestTypeService;

	@KafkaListener(topics = "REQUEST_TYPE_TOPIC")
	public void executeRequestTypeTopicConsumer(@Payload String payload){
			
		
		System.out.println("***************************************************");
		System.out.println("*******REQUEST TYPE CONSUMER *********");
		System.out.println("***************************************************");		
		SyncEngineEntity syncEngineEntity = syncEngineDao.findOne(3L);
		if(syncEngineEntity !=null){
			requestTypeService.getAllRequestDetails(syncEngineEntity.getLastSyncTime());
		}
    	
	}
}
