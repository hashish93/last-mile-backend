package com.appzoneltd.lastmile.microservice.manualdistribution.service.newAlgorithm.model;

import java.util.Date;

import lombok.Data;

@Data
public class OrderPoint {

	private Long orderId;
	private String timeFrom;
	private String timeTo;
	private LocationPoint locationPoint;
	private String address;
	private Date date;
	private String status;
	
}
