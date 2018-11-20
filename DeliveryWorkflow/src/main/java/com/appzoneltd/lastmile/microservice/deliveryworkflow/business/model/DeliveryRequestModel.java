package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.model;

import java.util.Date;

import lombok.Data;

@Data
public class DeliveryRequestModel {

	private Long packageId;
	private String shipmentServiceType;
	private Date pickupDate;

}
