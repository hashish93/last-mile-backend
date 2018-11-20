package com.appzoneltd.lastmile.microservice.lookup.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PackageTypeDto implements Serializable {

	private static final long serialVersionUID = -430850492617722126L;
	
	private Long packageTypeId;
	
	private String packageType;
	
	private String packageDimension;
	
	private Long expectedWeight;
	
	private String description;
	
	private Date created;
	
	private Long version;
}
