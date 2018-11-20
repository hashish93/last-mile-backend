package com.appzoneltd.lastmile.microservice.workflow.notification.model;

import lombok.Data;

@Data
public class CustomerNotificationModel {

	private Long packageId;
	private Long requestId;
	private Long driverId;
	private String value;
	
}
