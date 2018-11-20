package com.appzoneltd.lastmile.microservices.syncengine.engine.model;

import lombok.Data;

@Data
public class pickupStatisticsSummeryReportModel {
	
	private Long hubId;
	private String requestType;
	private String requestStatus;
	private Long count;
	
	public pickupStatisticsSummeryReportModel(){
	}

	public pickupStatisticsSummeryReportModel(Long hubId, String requestType, String requestStatus, Long count) {
		this.hubId = hubId;
		this.requestType = requestType;
		this.requestStatus = requestStatus;
		this.count = count;
	}
	
	
}
