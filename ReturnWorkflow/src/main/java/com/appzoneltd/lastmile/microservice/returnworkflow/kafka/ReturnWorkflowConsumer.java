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

import com.appzoneltd.lastmile.microservice.returnworkflow.kafka.models.ReturnBase;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ReturnWorkflowConsumer {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private RuntimeService runTimeService;

	@Transactional
	@KafkaListener(topics = "RETURN_WORKFLOW_START")
	public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {

		ReturnBase returnBase = mapper.readValue(payload, ReturnBase.class);
		
		Map<String, Object> workflowData = new HashMap<String, Object>();
		workflowData.put("packageId", returnBase.getPackageId());
		runTimeService.startProcessInstanceByKey("returnProcess", workflowData);

	}

}
