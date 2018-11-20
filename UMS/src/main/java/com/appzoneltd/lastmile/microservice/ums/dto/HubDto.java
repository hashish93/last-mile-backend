package com.appzoneltd.lastmile.microservice.ums.dto;

import lombok.Data;

@Data
public class HubDto {
	private Long id;
	private String name;
	private String buildingNumber;
	private String street;
	private String area;
	private String phone;
	private String latitude;
	private String longitude;
}
