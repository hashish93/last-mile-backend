package com.appzoneltd.lastmile.microservice.workflowservice.kafka;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowBase;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowDriverResponse;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPackageOrderInfo;
import com.appzoneltd.lastmile.microservice.workflowservice.model.JobOrder;
import com.appzoneltd.lastmile.microservice.workflowservice.model.Location;
import com.appzoneltd.lastmile.microservice.workflowservice.service.JobOrdersService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AssignJobOrderToDriverConsumer {

	@Autowired
	private ObjectMapper mapper;
	
	@Autowired
	private WorkflowServiceProducer workflowServiceProducer;
	
	@Autowired
	private JobOrdersService jobOrdersService;
	
	
	@KafkaListener(topics = "AssignTheOrderToDriver")
	public void assignTheOrderToDriver(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
		// Getting Driver Action 
		System.out.println("PAYLOAD "+payload);
		
		WorkflowDriverResponse workflowDriverResponse= mapper.readValue(payload, WorkflowDriverResponse.class);
		Long packageId=workflowDriverResponse.getPackageId();
		System.out.println("CONSUMER ABOUT TO SET THE JOB ORDER IN COUCHBASE");
		
		workflowServiceProducer.sendMessage("PackageJobOrderInfoRequest",
				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowDriverResponse));
		
	}//end nearByVehicleRequest Method
	
	
	@KafkaListener(topics = "PackageJobOrderInfoResponse")
	public void packageJobOrderInfoResponse(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
		// Getting Driver Action 
		System.out.println("PackageJobOrderInfoResponse "+payload);
		WorkflowPackageOrderInfo workflowPackageOrderInfo= mapper.readValue(payload, WorkflowPackageOrderInfo.class);
		// Creating JobOrder 
		JobOrder jobOrder=new JobOrder();
		jobOrder.setJobOrderId(workflowPackageOrderInfo.getRequestId());
		jobOrder.setOrderType(workflowPackageOrderInfo.getPickupRequestType());
		jobOrder.setCustomerName(workflowPackageOrderInfo.getCustomerName());
		jobOrder.setCustomerPhone(workflowPackageOrderInfo.getCustomerPhone());
		// Init the Orginal Location 
		Location originalLocation=new Location();
		Location actualLocation=new Location();
		// Setting OriginLocation 
		originalLocation.setLatitude(workflowPackageOrderInfo.getPickupLatitiude());
		originalLocation.setLongitude(workflowPackageOrderInfo.getPickupLongitude());
		// Setting OriginLocation 
		actualLocation.setLatitude(workflowPackageOrderInfo.getPickupLatitiude());
		actualLocation.setLongitude(workflowPackageOrderInfo.getPickupLongitude());
		//// assing Locations 
		jobOrder.setOriginalLocation(originalLocation);
		jobOrder.setActualLocation(actualLocation);
		
		jobOrder.setRecepientName(workflowPackageOrderInfo.getRecepientName());
		jobOrder.setRecepientPhone(workflowPackageOrderInfo.getRecepientPhone());
		jobOrder.setOrderStatus(workflowPackageOrderInfo.getRequestStatus());
		jobOrder.setActualWeight(workflowPackageOrderInfo.getActualWeight());
		jobOrder.setPriority(1);
		
		// Assiging the JobOrderToDriver
		jobOrdersService.assignJobOrderForDriver(workflowPackageOrderInfo.getDriverId(), jobOrder);
		
		workflowServiceProducer.sendMessage("AssignTheOrderToDriverResponse",
				mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowPackageOrderInfo));
		
	}//end nearByVehicleRequest Method
	
}
