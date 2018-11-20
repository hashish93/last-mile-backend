package com.appzoneltd.lastmile.microservice.pickuprequest.model;

import lombok.Data;

@Data
public class PackagePickupRequestInfo {

	private Long requestId;
	private Long requesterId;
	private String address;
	
}
