package com.appzoneltd.lastmile.microservice.workflow.model;

import lombok.Data;

@Data
public class CustomerNotificationModel {

	private Long packageId;
	private Long driverId;
	private String value;
	
}
