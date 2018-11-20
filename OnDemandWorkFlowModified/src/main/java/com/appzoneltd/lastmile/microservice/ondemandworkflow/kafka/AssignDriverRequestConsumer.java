package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.model.WorkflowNearByVehicles;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AssignDriverRequestConsumer {

	@Autowired
	private ObjectMapper mapper ;

	@Autowired
	private RuntimeService runTimeService;

    @KafkaListener(topics = "ASSIGN_DRIVER_REQUEST")
    public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException  {
   
    	WorkflowNearByVehicles workflowNearByVehicles = mapper.readValue(payload, WorkflowNearByVehicles.class);  
    	
    	Map<String, Object> workflowData= new HashMap<String, Object>();
    	workflowData.put("packageId", workflowNearByVehicles.getPackageId());
    	workflowData.put("driverId", workflowNearByVehicles.getVehicles().get(0));
    	workflowData.put("requestId", workflowNearByVehicles.getRequestId());
    	workflowData.put("adminAssign", true);
    	workflowData.put("nearByVehicles", workflowNearByVehicles.getVehicles());
    	// Starting WorkFlow 
    	
    	MyPrinter.print("ASSIGN VEHICLE", workflowNearByVehicles.toString());
    	
		runTimeService.startProcessInstanceByMessage("startNearByVehicleModule",workflowData);
    }
	
}
