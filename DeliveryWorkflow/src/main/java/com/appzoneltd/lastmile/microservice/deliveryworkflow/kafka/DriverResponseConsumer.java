package com.appzoneltd.lastmile.microservice.deliveryworkflow.kafka;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.model.DeliveryParameter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class DriverResponseConsumer {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private RuntimeService runTimeService;

	@Transactional
	@KafkaListener(topics = "DELIVERY_DRIVER_RESPONSE")
	public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {

		DeliveryParameter deliveryParameter= mapper.readValue(payload, DeliveryParameter.class);
		
		Map<String, Object> workflowData = new HashMap<String, Object>();
		workflowData.put("packageId", deliveryParameter.getPackageId());
		workflowData.put("driverAcceptance", deliveryParameter.isDriverAcceptance());
		runTimeService.startProcessInstanceByMessage("startDriverPackageConfirmation", workflowData);
	}

}
