package com.appzoneltd.lastmile.microservice.workflowservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.workflowservice.kafka.WorkflowServiceProducer;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowBase;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowNearByVehicles;
import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowPickupRequestInfo;
import com.appzoneltd.lastmile.microservice.workflowservice.model.DriverAssignRequest;
import com.appzoneltd.lastmile.microservice.workflowservice.model.NearByVehicleAssign;
import com.appzoneltd.lastmile.microservice.workflowservice.model.Vehicle;
import com.appzoneltd.lastmile.microservice.workflowservice.service.NearByVehicleDetectorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class NearByVehicleController {

	@Autowired
	private NearByVehicleDetectorService nearByVehicleDetectorService;

	@Autowired
	private WorkflowServiceProducer workflowServiceProducer;
	
	@Autowired
	private ObjectMapper mapper ;
	
	@RequestMapping(value = "/getNearByVehicles", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<?> gettingNearByVehicleForAssign(@RequestBody NearByVehicleAssign nearByVehicleAssign) throws JsonProcessingException {
	     // Getting the Request Purpose 
		 System.out.println(">>>>>>>>>>>>>>>>>>>>>>NEAR BY VEHICLE ASSIGN "+nearByVehicleAssign.toString());
		 // Find NearByVehicles
		 WorkflowPickupRequestInfo workflowPickupRequestInfo=new WorkflowPickupRequestInfo();
		 workflowPickupRequestInfo.setPackageId(nearByVehicleAssign.getPackageId());
		 workflowPickupRequestInfo.setRequestId(nearByVehicleAssign.getRequestId());
		 workflowPickupRequestInfo.setRequesterId(nearByVehicleAssign.getRequesterId());
		 
		 
		 List<Vehicle> vehicles=nearByVehicleDetectorService.getNearByVehiclesForAssing(nearByVehicleAssign,workflowPickupRequestInfo); 
		 System.out.println(">>>> SHOWING VEHIVLES "+vehicles.toString());
		// return results
		 return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
	 }
	
	@RequestMapping(value = "/assignvehicle", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> gettingNearByVehicleForAssign(@RequestBody DriverAssignRequest driverAssignRequest) throws JsonProcessingException {
	     // Getting the Request Purpose 
		 System.out.println(">>>>>>>>>>>>>>>>>>>>>>ADMIN SEND ASSING DRIVER REQUEST WITH INFORMATION");
		 System.out.println(">>>  "+driverAssignRequest.toString());
		 
		 WorkflowNearByVehicles workflowNearByVehicles=new WorkflowNearByVehicles();
		 workflowNearByVehicles.setPackageId(driverAssignRequest.getPackageId());
		 workflowNearByVehicles.setAutomatic(true);
		 workflowNearByVehicles.setRequestAddress(driverAssignRequest.getRequestAddress());
		 workflowNearByVehicles.setRequesterId(driverAssignRequest.getRequesterId());
		 workflowNearByVehicles.setRequestId(driverAssignRequest.getRequestId());
		 workflowNearByVehicles.setRequestWeight(driverAssignRequest.getRequestWeight());
		 // List of Vehicles 
		 List<Long> vehicles=new ArrayList<>();
		 vehicles.add(driverAssignRequest.getDriverId());
		 
		 workflowNearByVehicles.setVehicles(vehicles);
		 
		 System.out.println(">>>>>>>>>>>>>>>>>>>>>>AFTER FILLING "+workflowNearByVehicles.toString());
		 
		 // Sending Pickup request to Service
		 workflowServiceProducer.sendMessage("ASSIGN_DRIVER_REQUEST", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(workflowNearByVehicles));
		// return results
		 return new ResponseEntity<WorkflowBase>(workflowNearByVehicles, HttpStatus.OK);
	 }
	
}
