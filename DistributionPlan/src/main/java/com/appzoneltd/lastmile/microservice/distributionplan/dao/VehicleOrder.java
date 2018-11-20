package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import lombok.Data;

@Data
public class VehicleOrder {

	private Long orderId;
	private String orderType;
	private Long priorty ;
	private String activeVehicleAddress;
}
