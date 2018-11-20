package com.appzoneltd.lastmile.microservice.ondemandworkflow.model;

import lombok.Data;

@Data
public class PackageStatusModel {

	private Long packageId;
	private Long requestId;
	private String status;
}
