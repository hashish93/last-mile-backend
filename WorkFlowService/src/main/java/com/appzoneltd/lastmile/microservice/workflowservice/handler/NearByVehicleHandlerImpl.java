package com.appzoneltd.lastmile.microservice.workflowservice.handler;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.workflowservice.kafka.WorkflowServiceProducer;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowBase;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowDispatchMode;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowNearByVehicles;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPackageHub;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPackageLocation;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPackageType;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPackageWeight;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPickupDiameter;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPickupRequestInfo;
import com.appzoneltd.lastmile.microservice.workflowservice.service.NearByVehicleDetectorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class NearByVehicleHandlerImpl implements WorkflowHandler {

	private static final int WORK_FLOW_OBJECTS_COUND = 7;
	
	@Autowired
	private NearByVehicleDetectorService nearByVehicleService;
	
	@Autowired
	private WorkflowServiceProducer workflowServiceProducer;
	
	@Autowired
	private ObjectMapper mapper;
	
	@Override
	public void onNext(WorkFlowHandlerParams p) {
		System.out.println("NEW ADDED");
		System.out.println("OBJECT INSTANCE "+p.toString());
		p.workflowObjects.add(p.workflowBase);
		if (p.workflowObjects.size() == WORK_FLOW_OBJECTS_COUND) {
			process(p.workflowObjects, p.clearable);
		}

	}

	public void process(List<WorkflowBase> workflowObjects, Clearable clearable) {
		// take action

		WorkflowPackageLocation workflowPackageLocation = null;
		WorkflowPackageHub workflowPackageHub = null;
		WorkflowPickupDiameter workflowPickupDiameter = null;
		WorkflowDispatchMode workflowDispatchMode = null;
		WorkflowPackageWeight workflowPackageWeight= null;
		WorkflowPickupRequestInfo workflowPickupRequestInfo=null;
		WorkflowPackageType workflowPackageType=null;
		// Filling Data
		for (WorkflowBase workflowBase : workflowObjects) {

			if (workflowBase instanceof WorkflowPackageLocation) {
				workflowPackageLocation = (WorkflowPackageLocation) workflowBase;
			} // end if

			if (workflowBase instanceof WorkflowPackageHub) {
				workflowPackageHub = (WorkflowPackageHub) workflowBase;
			} // end if

			if (workflowBase instanceof WorkflowPickupDiameter) {
				workflowPickupDiameter = (WorkflowPickupDiameter) workflowBase;
			} // end if

			if (workflowBase instanceof WorkflowDispatchMode) {
				workflowDispatchMode = (WorkflowDispatchMode) workflowBase;
			} // end if
			
			if (workflowBase instanceof WorkflowPackageWeight) {
				workflowPackageWeight = (WorkflowPackageWeight) workflowBase;
			} // end if
			
			if (workflowBase instanceof WorkflowPickupRequestInfo) {
				workflowPickupRequestInfo = (WorkflowPickupRequestInfo) workflowBase;
			} // end if
			
			if (workflowBase instanceof WorkflowPackageType) {
				workflowPackageType = (WorkflowPackageType) workflowBase;
			} // end if
			
		} // end for-each

		System.out.println(">>> ALL : " + workflowObjects.toString());
		System.out.println(">>> 1-> " + workflowPackageLocation.toString());
		System.out.println(">>> 2-> " + workflowPackageHub.toString());
		System.out.println(">>> 3-> " + workflowPickupDiameter.toString());
		System.out.println(">>> 4-> " + workflowDispatchMode.toString());
		System.out.println(">>> 5-> " + workflowPackageWeight.toString());
		System.out.println(">>> 6-> " + workflowPickupRequestInfo.toString());
		System.out.println(">>> 7-> " + workflowPickupRequestInfo.toString());
		// Init the NearBy Needed Values 
		Long packageId=workflowPackageLocation.getPackageId();
		Long requestId=workflowPickupRequestInfo.getRequestId();
		Long requesterId=workflowPickupRequestInfo.getRequesterId();
		String requestAddress=workflowPickupRequestInfo.getAddress();
		String packageType=workflowPackageType.getPackageType();
		String requestWeight=packageType+"("+workflowPackageWeight.getWeight().intValue()+"Kg)";
		boolean automatic=workflowDispatchMode.isAutomaticMode();
		
		List<Long> nearByVehicles=nearByVehicleService.getNearByVehicles(workflowPickupRequestInfo,workflowPackageLocation, 
				workflowPickupDiameter, workflowPackageHub,workflowPackageWeight,true);
		
		/// Prepare Response
		WorkflowNearByVehicles workflowNearByVehicles=new WorkflowNearByVehicles();
		workflowNearByVehicles.setPackageId(packageId);
		workflowNearByVehicles.setVehicles(nearByVehicles);
		workflowNearByVehicles.setAutomatic(automatic);
		workflowNearByVehicles.setRequestId(requestId);
		workflowNearByVehicles.setRequesterId(requesterId);
		workflowNearByVehicles.setRequestAddress(requestAddress);
		workflowNearByVehicles.setRequestWeight(requestWeight);	
		workflowNearByVehicles.setPackageType(packageType);
		// Send to Kafka 
		try {
			workflowServiceProducer.sendMessage("NearByVehicleResponse",
					mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowNearByVehicles));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//end try_catch
		
		// finalize object
		if (clearable != null) {
			clearable.clear();
		}//end if Condition 
	}

	@Override
	public void onError(WorkFlowHandlerParams p) {
		// TODO Auto-generated method stub
		
	}

	
}