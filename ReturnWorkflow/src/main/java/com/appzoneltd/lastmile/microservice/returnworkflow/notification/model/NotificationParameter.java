package com.appzoneltd.lastmile.microservice.returnworkflow.notification.model;

import java.util.Date;

import lombok.Data;

@Data
public class NotificationParameter {

	private Long packageId;
	private Long requestId;
	private Long driverId;
	private Long customerId;
	private Date returnDate;
	
}
