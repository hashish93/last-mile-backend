package com.appzoneltd.lastmile.microservice.lookup.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ShipmentServiceTypeDto {

	private Long shipmentServiceTypeId;

	private Long shipmentServiceId;
	
	private String type;

	private String description;

	private Date created;

	private Long version;

}
