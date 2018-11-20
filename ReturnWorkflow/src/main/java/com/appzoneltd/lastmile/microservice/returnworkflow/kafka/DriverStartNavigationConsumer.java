package com.appzoneltd.lastmile.microservice.returnworkflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.model.ReturnParameter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DriverStartNavigationConsumer {
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private RuntimeService runTimeService;
	
	@Transactional
	@KafkaListener(topics = "RETURN_DRIVER_START_NAVIGATION")
	public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
		System.out.println(" >>>>>>>>>>>>>>>>>>>>>> start WORKFLOW_DRIVER_START_NAVIGATION");
		System.out.println("payload >>>>>>>>>>>>>>>"+payload);
		ReturnParameter returnParameter = mapper.readValue(payload, ReturnParameter.class);
		Map<String, Object> workflowData = new HashMap<String, Object>();
		workflowData.put("driverId", returnParameter.getDriverId());
		workflowData.put("packageId", returnParameter.getPackageId());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>> preparing to start driver navigation");
		runTimeService.startProcessInstanceByMessage("startDriverNavigationMessage", workflowData);
		
	}

}
//startDriverNavigationMessage