package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model;

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
