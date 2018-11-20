package com.appzoneltd.lastmile.microservices.syncengine.engine.model;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class DriverDetailsModel {
	private Long driverId;
	private String driverType;
	private BigDecimal rating;
	private Long hubId;
	private Date updated;
}
