package com.appzoneltd.lastmile.microservices.syncengine.engine.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class DriverRatingSummeryReportModel {
	private Long hubId;
	private String type;
	private BigDecimal rate;
	private Long count;
	public DriverRatingSummeryReportModel(Long hubId,String type ,BigDecimal rate, Long count) {
		this.hubId = hubId;
		this.type = type;
		this.rate = rate;
		this.count = count;
	}
	
}
