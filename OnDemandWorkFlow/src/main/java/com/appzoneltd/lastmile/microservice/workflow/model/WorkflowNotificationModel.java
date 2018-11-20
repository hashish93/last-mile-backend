package com.appzoneltd.lastmile.microservice.workflow.model;

import lombok.Data;

@Data
public class WorkflowNotificationModel {

	private Long packageId;
	private Long requestId;
	private Long customerId;
	private Long driverId;
	private String trackingNumber;
	
}
