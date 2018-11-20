package com.appzoneltd.lastmile.microservices.syncengine.engine.model;

import java.util.Date;

import lombok.Data;

@Data
public class PickupStatisticsDetailsReportModel {

	private Long id;
	private Long hubId;
	private Long requestId;
	private String requestType;
	private String requestStatus;
	private Date created;
	private Date updated;
	
}
