package com.appzoneltd.lastmile.microservice.workflow.kafka.models;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class WorkflowNearByVehicles extends WorkflowBase implements Serializable{

	private List<Long> vehicles;
	private boolean automatic;
	private Long requestId;
	private Long requesterId;
	private String requestAddress;
	private String requestWeight;
	private String packageType;
	
}
