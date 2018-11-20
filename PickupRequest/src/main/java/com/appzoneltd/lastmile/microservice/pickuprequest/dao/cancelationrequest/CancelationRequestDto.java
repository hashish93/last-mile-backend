package com.appzoneltd.lastmile.microservice.pickuprequest.dao.cancelationrequest;

import lombok.Data;

@Data
public class CancelationRequestDto {
	
	private Long id;

	private Long packageId;

	private Long reasonId;

	private String description;

	private String status;
	
	private Long requesterId;
	
	private Long requestId;

}
