package com.appzoneltd.lastmile.microservices.syncengine.engine.model;

import lombok.Data;

@Data
public class PackageTypeSummeryReportModel{

	private Long hubId;
	private Long packageTypeId;
	private Long packageValue;
	private Long count;
	
	public PackageTypeSummeryReportModel(Long hubId, Long packageTypeId, Long packageValue , Long count) {
		this.hubId = hubId;
		this.packageTypeId = packageTypeId;
		this.packageValue = packageValue;
		this.count = count;
	}
	
	public PackageTypeSummeryReportModel(){
		
	}
	
	
}
