package com.appzoneltd.lastmile.microservice.pickuprequest.model;

public enum RequestType {
	
	PICKUP("PICKUP") , DELIVERY("DELIVERY") , TRANSIENT("TRANSIENT") ;
	
	private final String requestType ; 
	
	private RequestType (String requestType){
		
		this.requestType=requestType;
	}

	public String getRequestType() {
		return requestType;
	} 
}
