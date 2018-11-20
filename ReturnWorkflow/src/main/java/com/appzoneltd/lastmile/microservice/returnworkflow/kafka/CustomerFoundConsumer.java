package com.appzoneltd.lastmile.microservice.returnworkflow.kafka;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.model.ReturnParameter;
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
public class CustomerFoundConsumer {
	
	@Autowired
	private ObjectMapper mapper;
	@Autowired
	private RuntimeService runTimeService;
	
	@Transactional
	@KafkaListener(topics = "RETURN_CUSTOMER_FOUND")
	public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
		System.out.println(" >>>>>>>>>>>>>>>>>>>>>> start WORKFLOW_CUSTOMER_FOUND");
		System.out.println("payload >>>>>>>>>>>>>>>"+payload);
		ReturnParameter returnParameter = mapper.readValue(payload, ReturnParameter.class);
		Map<String, Object> workflowData = new HashMap<String, Object>();
		workflowData.put("customerId", returnParameter.getCustomerId());
		workflowData.put("packageId", returnParameter.getPackageId());
		workflowData.put("customerFound", returnParameter.getCustomerFound());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>> preparing to start customer found");
		runTimeService.startProcessInstanceByMessage("startCustomerFoundMessage", workflowData);
		
	}

}
