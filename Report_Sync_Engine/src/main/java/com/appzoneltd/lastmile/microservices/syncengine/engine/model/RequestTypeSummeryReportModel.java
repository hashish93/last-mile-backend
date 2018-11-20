package com.appzoneltd.lastmile.microservices.syncengine.engine.model;

import lombok.Data;

@Data
public class RequestTypeSummeryReportModel {
	
	private Long hubId;
	private String requestType;
	private Long count;
	public RequestTypeSummeryReportModel(Long hubId, String requestType , Long count) {
		super();
		this.hubId = hubId;
		this.requestType = requestType;
		this.count = count;
	}
	
}
