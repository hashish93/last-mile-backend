package com.appzoneltd.lastmile.microservice.workflowservice.kafka.models;

import java.util.List;

import lombok.Data;

@Data
public class WorkflowNearByVehicles extends WorkflowBase{

	private List<Long> vehicles;
	private boolean automatic;
	private Long requestId;
	private Long requesterId;
	private String requestAddress;
	private String requestWeight;
	private String packageType;
	
}
