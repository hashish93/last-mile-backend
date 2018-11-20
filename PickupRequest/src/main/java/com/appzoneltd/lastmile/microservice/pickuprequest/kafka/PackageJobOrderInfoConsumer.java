package com.appzoneltd.lastmile.microservice.pickuprequest.kafka;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.WorkflowBase;
import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.WorkflowDriverResponse;
import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.WorkflowPackageLocation;
import com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models.WorkflowPackageOrderInfo;
import com.appzoneltd.lastmile.microservice.pickuprequest.model.PackageOrderInfoModel;
import com.appzoneltd.lastmile.microservice.pickuprequest.model.PackagePickupLocation;
import com.appzoneltd.lastmile.microservice.pickuprequest.service.PickupServiceImp;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class PackageJobOrderInfoConsumer {

	@Autowired
	private ObjectMapper mapper ;

	@Autowired
	private PackagePickupProducer changePackageStatusProducer;

	@Autowired
	private PickupServiceImp pickupServiceImp;
	
	@KafkaListener(topics = "PackageJobOrderInfoRequest")
    public void receiveMessage(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
    	// Getting Payload From Kafka  
		WorkflowDriverResponse workflowDriverResponse= mapper.readValue(payload, WorkflowDriverResponse.class);
    	// Getting Packagef O
    	PackageOrderInfoModel packageOrderInfoModel= pickupServiceImp.getPackageOrderInfo(workflowDriverResponse.getPackageId());	
    	// Assing WorkFlowPackageOrder Info 
    	WorkflowPackageOrderInfo workflowPackageOrderInfo=new WorkflowPackageOrderInfo();
    	workflowPackageOrderInfo.setPackageId(workflowDriverResponse.getPackageId());
    	workflowPackageOrderInfo.setRequestId(packageOrderInfoModel.getRequestId());
    	workflowPackageOrderInfo.setRecepientName(packageOrderInfoModel.getRecepientName());
    	workflowPackageOrderInfo.setRecepientPhone(packageOrderInfoModel.getRecepientPhone());
    	workflowPackageOrderInfo.setRequestStatus(packageOrderInfoModel.getRequestStatus());
    	workflowPackageOrderInfo.setPickupLatitiude(packageOrderInfoModel.getPickupLatitiude());
    	workflowPackageOrderInfo.setPickupLongitude(packageOrderInfoModel.getPickupLongitude());
    	workflowPackageOrderInfo.setActualWeight(packageOrderInfoModel.getActualWeight());
    	workflowPackageOrderInfo.setPickupRequestType(packageOrderInfoModel.getPickupRequestType());
    	workflowPackageOrderInfo.setCustomerId(packageOrderInfoModel.getCustomerId());
    	workflowPackageOrderInfo.setCustomerName(packageOrderInfoModel.getCustomerName());
    	workflowPackageOrderInfo.setCustomerPhone(packageOrderInfoModel.getCustomerPhone());
    	workflowPackageOrderInfo.setDriverId(workflowDriverResponse.getDriverId());
    	// Send to Kafka
    	changePackageStatusProducer.sendMessage("PackageJobOrderInfoResponse", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowPackageOrderInfo));
    }
	
}
