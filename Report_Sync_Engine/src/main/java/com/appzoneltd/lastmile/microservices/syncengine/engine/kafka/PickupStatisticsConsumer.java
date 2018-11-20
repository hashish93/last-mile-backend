package com.appzoneltd.lastmile.microservices.syncengine.engine.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservices.syncengine.engine.service.PickupStatisticsService;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.SyncEngineDao;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.SyncEngineEntity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class PickupStatisticsConsumer {

	@Autowired
	private SyncEngineDao syncEngineDao;
	
	@Autowired
	private PickupStatisticsService pickupStatisticsService;
	
	@KafkaListener(topics = "PICKUP_STATISTICS_TOPIC")
	public void executePickupStatisticsTopicConsumer(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		
		System.out.println("***************************************************");
		System.out.println("*******PICKUP STATISTICS CONSUMER *********");
		System.out.println("***************************************************");
		
		SyncEngineEntity syncEngineEntity = syncEngineDao.findOne(5L);
		if(syncEngineEntity !=null){
			pickupStatisticsService.getAllPickupStatisticsDetails(syncEngineEntity.getLastSyncTime());
		}
		
	}
}
