package com.appzoneltd.lastmile.microservice.pickuprequest.dto;

import java.util.Date;

public class PackageDetailsDto {
	
	private String requestType ;
	private Date requestDate ;
	public String getRequestType() {
		return requestType;
	}
	public Date getRequestDate() {
		return requestDate;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}

	
}
