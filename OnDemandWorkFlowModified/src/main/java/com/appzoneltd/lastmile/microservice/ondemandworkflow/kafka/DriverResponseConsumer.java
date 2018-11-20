package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.PickupRequestRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.RequestHistoryRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model.WorkflowDriverResponse;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.NearByVehicleEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.WorkflowNotificationModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.service.OnDemandNotificationService;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.Utils;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowService.NearByVehicleService;
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
	private OnDemandNotificationService onDemandNotificationService;
	
	@Autowired
	private PickupRequestRepository pickupRequestJpaRepository;

	@Autowired
	private RequestHistoryRepository requestHistoryRepository;

	@Autowired
	private RuntimeService runTimeService;

	@KafkaListener(topics = "DRIVER_RESPONSE_ONDEMAND_PICKUP_REQUEST")
	public void nearByVehicleRequest(@Payload String payload)
			throws JsonParseException, JsonMappingException, IOException {
		WorkflowDriverResponse workflowDriverResponse = mapper.readValue(payload, WorkflowDriverResponse.class);
		System.out.println("***************************************************");
		System.out.println("*******DRIVER RESPONSE TO ONDEMAND REQUEST*********");
		System.out.println("***************************************************");
		System.out.println("PAYLOAD " + workflowDriverResponse.toString());
		// If Driver Accept
		if (workflowDriverResponse.isResponse()) {
			List<NearByVehicleEntity> acceptedDrivers = nearByVehicleService
					.findAcceptedVehiclesForPackage(workflowDriverResponse.getPackageId());
			if ((acceptedDrivers != null) && (acceptedDrivers.isEmpty())) {
				/// Gettting NearByVehicleModel
				NearByVehicleEntity nearByVehicleModel = nearByVehicleService.getNearbyVehicleForPackage(
						workflowDriverResponse.getPackageId(), workflowDriverResponse.getDriverId());
				if (nearByVehicleModel != null) {
					PickupRequestEntity pickupRequestEntity = pickupRequestJpaRepository
							.findByPickupRequestId(nearByVehicleModel.getRequestId());
					if (pickupRequestEntity != null) {
						nearByVehicleModel.setResponse("ACCEPT");
						nearByVehicleService.save(nearByVehicleModel);
						workflowDriverResponse.setGoExtra(true);
						workflowDriverResponse.setRequesterId(pickupRequestEntity.getRequesterId());
						workflowDriverResponse.setRequestId(pickupRequestEntity.getPickupRequestId());
						// Send Driver Response
						driverResponse(workflowDriverResponse);
					}
				}
			} else {
				WorkflowNotificationModel workflowNotificationModel = new WorkflowNotificationModel();
				workflowNotificationModel.setPackageId(workflowDriverResponse.getPackageId());
				workflowNotificationModel.setDriverId(workflowDriverResponse.getDriverId());
				onDemandNotificationService.sendPackageAlreadyAssignedNotification(workflowNotificationModel);
			}
		} else {
			
			NearByVehicleEntity nearByVehicleModel = nearByVehicleService.getNearbyVehicleForPackage(
					workflowDriverResponse.getPackageId(), workflowDriverResponse.getDriverId());
			if(nearByVehicleModel==null){
				nearByVehicleModel =new NearByVehicleEntity();
				Long id=Utils.generateUUID();
				nearByVehicleModel.setId(id);
				nearByVehicleModel.setActiveVehicleId(workflowDriverResponse.getDriverId());
				nearByVehicleModel.setPackageId(workflowDriverResponse.getPackageId());
				nearByVehicleModel.setRequestId(workflowDriverResponse.getRequestId());
			}
			nearByVehicleModel.setResponse("REJECT");
			nearByVehicleService.save(nearByVehicleModel);
			
			List<NearByVehicleEntity> rejectedDrivers = nearByVehicleService
					.findRejectedVehiclesForPackage(workflowDriverResponse.getPackageId());
			List<NearByVehicleEntity> allDrivers = nearByVehicleService
					.findAllNearVehiclesForPackageId(workflowDriverResponse.getPackageId());
			
			MyPrinter.print("REJECTED DRIVERS ", rejectedDrivers.size()+"");
			MyPrinter.print("ALL DRIVERS ", allDrivers.size()+"");
			
			if (rejectedDrivers.size() == (allDrivers.size() )) {
				MyPrinter.print("CONSUMER", "We are about to reject from driver " + nearByVehicleModel.toString());
				// Send Driver Response
				driverResponse(workflowDriverResponse);
			}
		}
	}

	private void driverResponse(WorkflowDriverResponse workflowDriverResponse) {
		Long packageId = workflowDriverResponse.getPackageId();
		Long requestId = workflowDriverResponse.getRequestId();

		Map<String, Object> workflowData = new HashMap<String, Object>();
		workflowData.put("packageId", packageId);
		workflowData.put("requesterId", workflowDriverResponse.getRequesterId());
		workflowData.put("driverResponse", workflowDriverResponse.isResponse());
		workflowData.put("driverId", workflowDriverResponse.getDriverId());
		MyPrinter.print("CONSUMER", "consumer data to start cancel " + workflowData.toString());
		if (packageId != null) {
			requestId = (requestId != null) ? requestId : requestHistoryRepository.getRequestIdFromPackageId(packageId);
			workflowData.put("requestId", requestId);
			System.out.println("***************************************************");
			System.out.println("*******DRIVER RESPONSE TO ONDEMAND REQUEST ssssss*********");
			System.out.println("******* DRIVER ID " + workflowDriverResponse.getDriverId());
			System.out.println("******* RESPONSE " + workflowDriverResponse.isResponse());
			System.out.println("***************************************************");
			runTimeService.startProcessInstanceByMessage("startDriverResponse", workflowData);
		}
	}
}
