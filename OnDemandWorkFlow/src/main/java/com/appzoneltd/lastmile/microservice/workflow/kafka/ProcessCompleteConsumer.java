package com.appzoneltd.lastmile.microservice.workflow.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.workflow.handler.ProcessServicesHandler;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowProcess;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component

public class ProcessCompleteConsumer {

	@Autowired
	private ObjectMapper mapper;

	@KafkaListener(topics = "PROCESS_COMPLETE")
	public void userCancelPickupRequest(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException, InterruptedException {
		// Getting WorkflowCancelRequest 
		WorkflowProcess workflowProcess= mapper.readValue(payload, WorkflowProcess.class);
		
		System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH5");
		System.out.println(">>>> COMPLETE  "+workflowProcess.toString());
		System.out.println("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH5");
		
		ProcessServicesHandler.get(workflowProcess.getPackageId(),workflowProcess.getActionId()).processConsumed(); 
		
	}//end receiveMessage Method
	

}
