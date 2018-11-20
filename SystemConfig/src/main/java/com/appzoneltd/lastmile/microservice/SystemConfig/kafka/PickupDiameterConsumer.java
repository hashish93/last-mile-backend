package com.appzoneltd.lastmile.microservice.SystemConfig.kafka;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.SystemConfig.dao.SystemConfig;
import com.appzoneltd.lastmile.microservice.SystemConfig.kafka.models.WorkflowBase;
import com.appzoneltd.lastmile.microservice.SystemConfig.kafka.models.WorkflowPickupDiameter;
import com.appzoneltd.lastmile.microservice.SystemConfig.service.SystemConfigService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component
public class PickupDiameterConsumer {

	@Autowired
	private ObjectMapper mapper ;

	@Autowired
	private SystemConfigProducer systemConfigProducer;

	@Autowired
	private SystemConfigService systemConfigService;
	
	private static final Logger LOGGER = LoggerFactory
            .getLogger(PickupDiameterConsumer.class);

    @KafkaListener(topics = "PickDiameterConfigRequest")
    public void pickDiameterConfigRequest(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
    	// receive Object 
    	WorkflowBase workflowBase= mapper.readValue(payload, WorkflowBase.class);
    	/// Getting Diameter
    	SystemConfig systemConfig=systemConfigService.findConfigById(3L);
    	
    	WorkflowPickupDiameter workflowPickupDiameter=new WorkflowPickupDiameter();
    	workflowPickupDiameter.setPackageId(workflowBase.getPackageId());
    	workflowPickupDiameter.setDiameter(systemConfig.getValue());
    	// Send to Kafka
    	systemConfigProducer.sendMessage("PickDiameterConfigResponse", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowPickupDiameter));
    }
}
