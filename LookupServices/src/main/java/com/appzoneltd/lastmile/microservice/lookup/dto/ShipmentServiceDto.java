package com.appzoneltd.lastmile.microservice.lookup.dto;

import java.util.Date;

import lombok.Data;

@Data
public class ShipmentServiceDto {

	private Long shipmentServiceId;

	private String service;

	private String description;

	private Date created;

	private Long version;

}
