package com.appzoneltd.lastmile.microservice.pickuprequest.model;

import lombok.Data;

@Data
public class PickupRequestData {

	private Long requestId;
	private String requestType;
	private Long requesterId;
	private Long hubId;
	
}
