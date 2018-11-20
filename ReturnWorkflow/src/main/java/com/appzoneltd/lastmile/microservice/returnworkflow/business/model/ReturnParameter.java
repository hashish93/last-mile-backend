package com.appzoneltd.lastmile.microservice.returnworkflow.business.model;

import lombok.Data;

@Data
public class ReturnParameter {

	private Long packageId;
	private Long driverId;
	private Long customerId;
	private Boolean driverAcceptance;
	private Boolean customerApproveInvoice;
	private Boolean customerFound;
	
}