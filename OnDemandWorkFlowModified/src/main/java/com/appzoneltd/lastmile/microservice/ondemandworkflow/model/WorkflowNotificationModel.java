package com.appzoneltd.lastmile.microservice.ondemandworkflow.model;

import lombok.Data;

@Data
public class WorkflowNotificationModel {

	private Long packageId;
	private Long requestId;
	private Long customerId;
	private Long driverId;
	private String trackingNumber;
	
}
