package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dto;

import java.util.Date;

import lombok.Data;

@Data
public class DeliveryScheduledDTO {

	private Long deliveryId;
	private Date deliveryDate;
	private String timeFrom;
	private String timeTo;
	
}
