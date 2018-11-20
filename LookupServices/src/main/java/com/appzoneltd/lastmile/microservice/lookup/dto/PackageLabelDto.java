package com.appzoneltd.lastmile.microservice.lookup.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class PackageLabelDto implements Serializable{

	private static final long serialVersionUID = 1461166529269550336L;
	
	private Long packageLabelId;
	
	private String label;
	
	private Date created;
	
	private Long version;
	
}
