package com.appzoneltd.lastmile.microservice.workflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowPackageStatusCouchbase;
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
			
			WorkflowPackageStatusCouchbase workflowPackageStatusCouchbase=mapper.readValue(payload, WorkflowPackageStatusCouchbase.class);
			
			Map<String, Object> workflowData= new HashMap<String, Object>();
	    	workflowData.put("packageId", workflowPackageStatusCouchbase.getPackageId());
	    	workflowData.put("driverId", workflowPackageStatusCouchbase.getDriverId());
	    	workflowData.put("requestId", workflowPackageStatusCouchbase.getRequestId());
	    	workflowData.put("requesterId", workflowPackageStatusCouchbase.getRequesterId());
	    	
	    	
			System.out.println("***************************************************");
			System.out.println("*******DRIVER RESPONSE TO ONDEMAND REQUEST*********");
			System.out.println("***************************************************");		
			System.out.println("PAYLOAD TO COMPLETE WORKFLOW "+payload);
			
	    	// Starting WorkFlow 
			runTimeService.startProcessInstanceByMessage("startPackagePickedupModule",workflowData);
			
		}
	}

