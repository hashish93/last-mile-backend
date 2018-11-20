package com.appzoneltd.lastmile.microservice.pickuprequest.dao.cancelationrequest;

import lombok.Data;

@Data
public class PickupRequestCancelation {
	
	private Long reasonId;

	private Long RequestId;

	private String description;

}
