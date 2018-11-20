package com.appzoneltd.lastmile.microservice.returnworkflow.notification.model;

import java.util.Date;

import lombok.Data;

@Data
public class CustomerNotificationModel {

	private Long packageId;
	private Long requestId;
	private Long driverId;
	private String value;
	private Date returnDate;
	
}
