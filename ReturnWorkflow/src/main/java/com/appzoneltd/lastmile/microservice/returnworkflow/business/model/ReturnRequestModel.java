package com.appzoneltd.lastmile.microservice.returnworkflow.business.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ReturnRequestModel {

	@NotNull(message="return.packageId.NotNull")
	private Long packageId;

	@NotNull(message="return.returnDate.NotNull")
	private Date returnDate;

	@NotNull(message="return.latitude.NotNull")
	private String latitude;

	@NotNull(message="return.longitude.NotNull")
	private String longitude;
}
