package com.appzoneltd.lastmile.microservices.syncengine.engine.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservices.syncengine.engine.service.DriverRatingService;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.SyncEngineDao;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.SyncEngineEntity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class DriverRatingConsumer {

	@Autowired
	private SyncEngineDao syncEngineDao;
	
	@Autowired
	private DriverRatingService driverRatingService;
	
	@KafkaListener(topics = "DRIVER_RATING_TOPIC")
	public void executePackageTypeTopicConsumer(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		
		System.out.println("***************************************************");
		System.out.println("*******  	DRIVER RATING CONSUMER *********");
		System.out.println("***************************************************");
		
		SyncEngineEntity syncEngineEntity = syncEngineDao.findOne(6L);
		if(syncEngineEntity !=null){
			driverRatingService.getAllDriverRatingDetails(syncEngineEntity.getLastSyncTime());
		}
		
	}
}