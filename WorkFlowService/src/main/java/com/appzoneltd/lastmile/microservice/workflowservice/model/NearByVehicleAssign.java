package com.appzoneltd.lastmile.microservice.workflowservice.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class NearByVehicleAssign {

	private Long packageId;
	private Long requestId;
	private Long requesterId;
	private Long hubId;
	private BigDecimal diameter;
	private BigDecimal weight;
	private NearByAssingLocation location;
	
}
