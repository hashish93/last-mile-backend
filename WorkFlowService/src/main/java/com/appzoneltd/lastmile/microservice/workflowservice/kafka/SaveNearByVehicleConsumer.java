package com.appzoneltd.lastmile.microservice.workflowservice.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowNearByVehicles;
import com.appzoneltd.lastmile.microservice.workflowservice.service.NearByVehicleService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class SaveNearByVehicleConsumer {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private NearByVehicleService nearByVehicleService;
	
	@Autowired
	private WorkflowServiceProducer workflowServiceProducer;

	@KafkaListener(topics = "SaveNearByVehicleRequest")
	public void saveNearByVehicleRequest(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException {
		// Getting NearByVehicles from the WorkFlow to persist
		WorkflowNearByVehicles workflowNearByVehicles= mapper.readValue(payload, WorkflowNearByVehicles.class);		
		// Saving Data 
		nearByVehicleService.saveNearByVehicles(workflowNearByVehicles);
		// Viewing Result 
		System.out.println("KAFKA CONSUMER ... SAVING THE WORKFLOW NEARBY VEHICLES "+workflowNearByVehicles);
		// Sending the Saved Status Back to the WorkFlow
		workflowServiceProducer.sendMessage("SaveNearByVehicleResponse",
				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowNearByVehicles));
		
	}//end saveNearByVehicleRequest 
	
}
