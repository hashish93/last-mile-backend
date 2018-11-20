package com.appzoneltd.lastmile.microservice.hubconfig.dto;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Data;

@Data
public class HubCofigurationDto {

	private Long configId;

	private BigDecimal value;

	private String textValue;

	private String displayname;

	private String description;

	private String status;

	private String unit;
	
	private Date created;

	private Long version;
	
	private Long hubId;
	
	private String configType;

}
