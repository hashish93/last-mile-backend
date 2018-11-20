package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.model;

import lombok.Data;

@Data
public class RescheduleModel {

	private Long requestId;
	private Long packageId;
	private Long driverId;
	
}
