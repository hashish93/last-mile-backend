package com.appzoneltd.lastmile.microservice.deliveryworkflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.model.DeliveryParameter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CustomerFoundConsumer {
	
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private RuntimeService runTimeService;
	
	@Transactional
	@KafkaListener(topics = "DELIVERY_CUSTOMER_FOUND")
	public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
		
		DeliveryParameter deliveryParameter= mapper.readValue(payload, DeliveryParameter.class);
		
		Map<String, Object> workflowData = new HashMap<String, Object>();
		workflowData.put("packageId", deliveryParameter.getPackageId());
		workflowData.put("customerFound", deliveryParameter.isCustomerFound());

		runTimeService.startProcessInstanceByMessage("startCustomerFound", workflowData);
		
	}

}
