package com.appzoneltd.lastmile.microservice.pickuprequest.kafka;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.WorkflowBase;
import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.WorkflowPackageLocation;
import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.WorkflowPickupRequestInfo;
import com.appzoneltd.lastmile.microservice.pickuprequest.model.PackagePickupLocation;
import com.appzoneltd.lastmile.microservice.pickuprequest.model.PackagePickupRequestInfo;
import com.appzoneltd.lastmile.microservice.pickuprequest.service.PickupServiceImp;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PackagePickupRequestInfoConsumer {

	@Autowired
	private ObjectMapper mapper ;

	@Autowired
	private PackagePickupProducer changePackageStatusProducer;

	@Autowired
	private PickupServiceImp pickupServiceImp;
	
	private static final Logger LOGGER = LoggerFactory
            .getLogger(PackagePickupLocationConsumer.class);

    @KafkaListener(topics = "PackagePickupRequestInfoRequest")
    public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
    	// Getting Payload From Kafka  
    	WorkflowBase workflowBase= mapper.readValue(payload, WorkflowBase.class);
    	// View Data
    	LOGGER.info("Kafka Consumer (PackagePickupLocationConsumer) : "+workflowBase.toString());
    	/// Getting PackagePickupRequestInfo
    	PackagePickupRequestInfo pickupRequestInfo=pickupServiceImp.getPackagePickupRequestInfo(workflowBase.getPackageId());
    	WorkflowPickupRequestInfo workflowPickupRequestInfo=new WorkflowPickupRequestInfo();
    	// Check if Not Null 
    	if(pickupRequestInfo!=null){
	    	// Prepare the Package Location Object
	    	workflowPickupRequestInfo.setPackageId(workflowBase.getPackageId());
	    	workflowPickupRequestInfo.setRequestId(pickupRequestInfo.getRequestId());
	    	workflowPickupRequestInfo.setRequesterId(pickupRequestInfo.getRequesterId());
	    	workflowPickupRequestInfo.setAddress(pickupRequestInfo.getAddress());
    	}//end if Condition
    	// Send to Kafka
    	changePackageStatusProducer.sendMessage("PackagePickupRequestInfoResponse", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowPickupRequestInfo));
    }
	
}
