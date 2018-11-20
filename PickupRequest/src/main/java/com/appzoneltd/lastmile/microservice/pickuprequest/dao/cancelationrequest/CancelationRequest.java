package com.appzoneltd.lastmile.microservice.pickuprequest.dao.cancelationrequest;

import lombok.Data;

@Data
public class CancelationRequest {
	
	private Long reasonId ; 
	
	private String reason ;
}
