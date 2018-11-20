package com.appzoneltd.lastmile.microservices.syncengine.engine.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservices.syncengine.engine.service.PackageTypeService;
import com.appzoneltd.lastmile.microservices.syncengine.stats.dao.SyncEngineDao;
import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.SyncEngineEntity;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@Component
public class PackageTypeConsumer {

	@Autowired
	private SyncEngineDao syncEngineDao;
	
	@Autowired
	private PackageTypeService packageTypeService;
	
	@KafkaListener(topics = "PACKAGE_TYPE_TOPIC")
	public void executePackageTypeTopicConsumer(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		
		System.out.println("***************************************************");
		System.out.println("*******PACKAGE TYPE CONSUMER *********");
		System.out.println("***************************************************");
		
		SyncEngineEntity syncEngineEntity = syncEngineDao.findOne(1L);
		if(syncEngineEntity !=null){
			packageTypeService.getAllPackageToDetails(syncEngineEntity.getLastSyncTime());
		}
		
	}
}