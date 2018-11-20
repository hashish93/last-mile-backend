package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.WorkflowPickupRequestInfo;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


	@Component
	public class PickedupRequestConsumer {
		
		@Autowired
		private ObjectMapper mapper ;

		@Autowired
		private RuntimeService runTimeService;

		@KafkaListener(topics = "DRIVER_SUBMIT_PICKUP_REQUEST")
		public void nearByVehicleRequest(@Payload String payload)
				throws JsonParseException, JsonMappingException, IOException, InterruptedException {
			
			WorkflowPickupRequestInfo workflowPickupRequestInfo=mapper.readValue(payload, WorkflowPickupRequestInfo.class);
			
			Map<String, Object> workflowData= new HashMap<String, Object>();
	    	workflowData.put("packageId", workflowPickupRequestInfo.getPackageId());
	    	workflowData.put("requestId", workflowPickupRequestInfo.getRequestId());
	    	workflowData.put("driverId", workflowPickupRequestInfo.getDriverId());
	    	
	    	
			System.out.println("***************************************************");
			System.out.println("*******DRIVER RESPONSE TO ONDEMAND REQUEST*********");
			System.out.println("***************************************************");		
			System.out.println("PAYLOAD TO COMPLETE WORKFLOW "+payload);
			
	    	// Starting WorkFlow 
			runTimeService.startProcessInstanceByMessage("startPackagePickedupModule",workflowData);
			
		}
	}

