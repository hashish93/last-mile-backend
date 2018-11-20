package com.appzoneltd.lastmile.microservice.pickuprequest.model;

import lombok.Data;

@Data
public class RequestRating {
	
	private Long packageId ;
	
	private Long driverId ;
	
	private Double driverRate ;
	
	private Double serviceRate ;
	
	

}
