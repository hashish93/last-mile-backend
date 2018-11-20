package com.appzoneltd.lastmile.microservice.lookup.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class VehicleTypeDto implements Serializable {
	
	private static final long serialVersionUID = -624781514825879103L;
	
	private Long vehicleTypeId;
	
	private String type;
	
	private Date created;
	
	private Long version;
}
