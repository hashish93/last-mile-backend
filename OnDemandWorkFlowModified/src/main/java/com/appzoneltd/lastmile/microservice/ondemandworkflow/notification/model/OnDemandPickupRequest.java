package com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.model;

import lombok.Data;

@Data
public class OnDemandPickupRequest {

	private Long packageId;
	private Long requestId;
	private String weight;
	private String address;
	private String packageType;
	
}
