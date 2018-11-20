package com.appzoneltd.lastmile.microservices.syncengine.engine.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservices.syncengine.engine.service.GoExtraService;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.SyncEngineDao;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.SyncEngineEntity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class GoExtraConsumer {

	@Autowired
	private SyncEngineDao syncEngineDao;
	
	@Autowired
	private GoExtraService goExtraService;
	
	@KafkaListener(topics = "GO_EXTRA_TOPIC")
	public void executeGoExtraTopicConsumer(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		
		System.out.println("***************************************************");
		System.out.println("*******GO EXTRA CONSUMER *********");
		System.out.println("***************************************************");
		
		SyncEngineEntity syncEngineEntity = syncEngineDao.findOne(4L);
		if(syncEngineEntity !=null){
			goExtraService.getAllGoExtraResponseDetails(syncEngineEntity.getLastSyncTime());
		}
		
	}
}
