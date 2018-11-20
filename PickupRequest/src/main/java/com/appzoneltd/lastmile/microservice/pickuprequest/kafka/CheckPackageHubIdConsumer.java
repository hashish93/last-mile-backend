package com.appzoneltd.lastmile.microservice.pickuprequest.kafka;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.WorkflowBase;
import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.WorkflowPackageHub;
import com.appzoneltd.lastmile.microservice.pickuprequest.service.PickupServiceImp;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CheckPackageHubIdConsumer {

	@Autowired
	private ObjectMapper mapper ;

	@Autowired
	private PickupServiceImp pickupService;
	
	@Autowired
	private PackagePickupProducer pickupProducer;
	
	private static final Logger LOGGER = LoggerFactory
            .getLogger(ChangePickupRequestStatusConsumer.class);

    @KafkaListener(topics = {"PackageHubIdRequest"})
    public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
   //// Receiving the PackageStatus from Kafka
    	//
    	
       	WorkflowBase workflowBase= mapper.readValue(payload, WorkflowBase.class);
       	System.out.println(">>> HERE In GETTING PACKAGE HUB ID REQUEST TO FIND PICKUP REQUEST FOR {} "+workflowBase.toString());
       	//Changing Status of the Package
       	Long hubId=pickupService.getPackageHubId(workflowBase.getPackageId());    	
       	System.out.println(">>> FINING PACKAGE HUB ID FOR {} "+workflowBase.toString()+" WITH HUBID "+hubId);
       	/// Filling Object 
       	WorkflowPackageHub workflowPackageHub=new WorkflowPackageHub();
       	workflowPackageHub.setHubId(hubId);
       	workflowPackageHub.setPackageId(workflowBase.getPackageId());       	
       	
       	System.out.println(">>> TO BE SENT FOR PACKAGEHUB ID RESPONSE WITH  {} "+workflowPackageHub.toString()+" WITH HUBID "+hubId);
       	// Sending To HubId
       	pickupProducer.sendMessage("PackageHubIdResponse", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowPackageHub));       	
    }
	
}
