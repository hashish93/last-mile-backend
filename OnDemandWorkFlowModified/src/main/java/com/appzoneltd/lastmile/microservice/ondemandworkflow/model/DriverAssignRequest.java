package com.appzoneltd.lastmile.microservice.ondemandworkflow.model;

import lombok.Data;

@Data
public class DriverAssignRequest {

	private Long vehicleId;
	private Long packageId;
	private Long driverId;
	private Long requestId;
	private Long requesterId;
	private String requestAddress;
	private String requestWeight;
	
}
