package com.appzoneltd.lastmile.microservice.workflowservice.kafka;

import java.io.IOException;
import java.util.List;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.workflowservice.dao.ActiveVehicleEntity;
import com.appzoneltd.lastmile.microservice.workflowservice.dao.ActiveVehicleRepository;
import com.appzoneltd.lastmile.microservice.workflowservice.dao.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.workflowservice.dao.PickupRequestJpaRepository;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowDriverResponse;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowNearByVehicles;
import com.appzoneltd.lastmile.microservice.workflowservice.model.NearByVehicleModel;
import com.appzoneltd.lastmile.microservice.workflowservice.service.NearByVehicleService;
import com.appzoneltd.lastmile.microservice.workflowservice.service.PushNotificationService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DriverResponseConsumer {

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private NearByVehicleService nearByVehicleService;
	
	@Autowired
	private PushNotificationService pushNotificationService;
	
	@Autowired
	private WorkflowServiceProducer workflowServiceProducer;
	
	@Autowired
	private ActiveVehicleRepository activeVehicleRepository;
	
	@Autowired
	private PickupRequestJpaRepository pickupRequestJpaRepository;
	
	@KafkaListener(topics = "DRIVER_RESPONSE_ONDEMAND_PICKUP_REQUEST")
	public void nearByVehicleRequest(@Payload String payload) throws JsonParseException, JsonMappingException, IOException {
		// Getting Driver Action 
		System.out.println("PAYLOAD "+payload);
		
		WorkflowDriverResponse workflowDriverResponse= mapper.readValue(payload, WorkflowDriverResponse.class);
		
		System.out.println("***************************************************");
		System.out.println("*******DRIVER RESPONSE TO ONDEMAND REQUEST*********");
		System.out.println("***************************************************");		
		System.out.println("PAYLOAD "+workflowDriverResponse.toString());		
		// If Driver Accept 
		if(workflowDriverResponse.isResponse()){
			List<NearByVehicleModel> acceptedDrivers=nearByVehicleService.findAcceptedVehiclesForPackage(workflowDriverResponse.getPackageId());
			if((acceptedDrivers!=null) && (acceptedDrivers.isEmpty())){
				  /// Gettting NearByVehicleModel
					NearByVehicleModel nearByVehicleModel=nearByVehicleService.getNearbyVehicleForPackage(workflowDriverResponse.getPackageId(),
							workflowDriverResponse.getDriverId());
					
					
					
					System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
					System.out.println("HERE");
					System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
					if(nearByVehicleModel !=null){
						PickupRequestEntity pickupRequestEntity = pickupRequestJpaRepository.findByPickupRequestId(nearByVehicleModel.getRequestId());
						if(pickupRequestEntity !=null){
							System.out.println(">>>>>>>> nearByVehicleModel >>>>>>>>>>>>"+nearByVehicleModel.toString());
							System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
							System.out.println("HERE");
							System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
							nearByVehicleModel.setResponse("ACCEPT");
							// Save the Data 
						    nearByVehicleService.save(nearByVehicleModel);
						    
						    workflowDriverResponse.setGoExtra(true);
						    workflowDriverResponse.setRequesterId(pickupRequestEntity.getRequesterId());
						    workflowDriverResponse.setRequestId(pickupRequestEntity.getPickupRequestId());
						    // Send To Kafka that a Driver Accept the Request
						    workflowServiceProducer.sendMessage("DriverResponseToRequest",
									mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowDriverResponse));
						}
						
					}
				
							    
			}else{
				// Send Push Notification to Driver that the Request is Already Assigned
				pushNotificationService.sendPackageAlreadyAssignedNotification(workflowDriverResponse);
			}//end if-Else 			
		}//end if Condition
		// If Driver Reject 
		else{
			// Getting All Rejected Drivers
			List<NearByVehicleModel> rejectedDrivers=nearByVehicleService.findRejectedVehiclesForPackage(workflowDriverResponse.getPackageId());
			List<NearByVehicleModel> allDrivers=nearByVehicleService.findAllNearVehiclesForPackageId(workflowDriverResponse.getPackageId());
			if(rejectedDrivers.size()==(allDrivers.size()-1)){
				// Save it as a rejected
				NearByVehicleModel nearByVehicleModel=nearByVehicleService.getNearbyVehicleForPackage(workflowDriverResponse.getPackageId(),
						workflowDriverResponse.getDriverId());
				nearByVehicleModel.setResponse("REJECT");
				// Save the Data 
			    nearByVehicleService.save(nearByVehicleModel);
				// Send to Kafka that That is the Last Driver Reject the Request So all reject
				workflowServiceProducer.sendMessage("DriverResponseToRequest",
						mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowDriverResponse));				
			}//end if Condition 
			else{
				// Save it as a rejected
				NearByVehicleModel nearByVehicleModel=nearByVehicleService.getNearbyVehicleForPackage(workflowDriverResponse.getPackageId(),
						workflowDriverResponse.getDriverId());
				nearByVehicleModel.setResponse("REJECT");
				// Save the Data 
			    nearByVehicleService.save(nearByVehicleModel);
			}//end else
		}//end if-Else		
	}//end nearByVehicleRequest Method
	
}
