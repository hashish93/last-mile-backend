package com.appzoneltd.lastmile.microservice.workflowservice.kafka.models;

import lombok.Data;

@Data
public class WorkflowPackageOrderInfo extends WorkflowBase{

	private Long requestId;
	private String recepientName;
	private String recepientPhone;
	private String requestStatus;
	private String pickupLatitiude;
	private String pickupLongitude;
	private Integer actualWeight; 
	private String pickupRequestType;
	private Long customerId;
	private String customerPhone;
	private String customerName;
	private Long driverId;
	
}
