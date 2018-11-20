package com.appzoneltd.lastmile.microservices.devices.dto;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
public class DeviceDto {

	private Long deviceId;

	@NotNull(message="device.brand.required")
	private Long brandId;

	@NotEmpty(message = "device.model.required")
	//@Pattern(regexp = "^$|^[a-zA-Z0-9\u0621-\u064A\u0660-\u0669][a-zA-Z0-9\u0621-\u064A\u0660-\u0669 ]*-{0,1}[^\\p{Punct}]$", message = "device.model.errorformat")
	//@Pattern(regexp = "^$|^[a-zA-Z0-9\u0621-\u064A\u0660-\u0669][a-zA-Z0-9\u0621-\u064A\u0660-\u0669 ]*[-()+]{0,1}$", message = "device.model.errorformat")
	@Pattern(regexp = "^$|^[a-zA-Z0-9\u0621-\u064A\u0660-\u0669][a-zA-Z0-9\u0621-\u064A\u0660-\u0669 -]*[a-zA-Z0-9\u0621-\u064A\u0660-\u0669]+", message = "device.model.errorformat")
	private String model;

	@NotEmpty(message = "device.IMEI.required")
	@Pattern(regexp = "^[0-9]?[0-9]*$", message = "device.IMEI.errorformat")
	@Size(max = 15 ,min=15, message="device.size.required")
	private String imeiInfo;

	@NotEmpty(message = "device.phonenumber.required")
	@Pattern(regexp = "^[0-9+]?[0-9]*$", message = "device.devicenumber.errorformat")
	private String phoneNumber;

	private String status;
	
	private String brandName ;
	
	private Long hubId;
	
	private String hubName;
}
