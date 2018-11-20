package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.model;

import java.util.Date;

import lombok.Data;

@Data
public class DeliverySchedulerHolder {

	private Date date;
	private Integer frequency;

}
