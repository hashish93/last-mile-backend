package com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class WorkflowNearByVehicles implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long packageId;
	private List<Long> vehicles;
	private boolean automatic;
	private Long requestId;
	private Long requesterId;
	private String requestAddress;
	private String requestWeight;
	private String packageType;
	
}
