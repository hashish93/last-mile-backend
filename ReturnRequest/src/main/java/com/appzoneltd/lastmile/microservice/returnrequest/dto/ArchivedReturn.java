package com.appzoneltd.lastmile.microservice.returnrequest.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ArchivedReturn {

	private Long requestId;
	private String requestType;
	private Date requestDate;
	private String requestStatus;
	private String packageType ;
	private String hubName ;
	private String cancellationReason ;

}
