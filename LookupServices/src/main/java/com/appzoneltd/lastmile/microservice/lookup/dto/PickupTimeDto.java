package com.appzoneltd.lastmile.microservice.lookup.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PickupTimeDto implements Serializable{

	private static final long serialVersionUID = 4099444152928786345L;
	
	private Long pickupTimeId;
	
	private String fromTime;
	
	private String toTime;
	
	private Date created;
	
	private Long version;
	
}
