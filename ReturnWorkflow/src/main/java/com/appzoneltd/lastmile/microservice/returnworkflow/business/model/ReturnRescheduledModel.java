package com.appzoneltd.lastmile.microservice.returnworkflow.business.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class ReturnRescheduledModel {

	@NotNull(message="return.packageId.NotNull")
	private Long packageId;

	@NotNull(message="return.returnDate.NotNull")
	private Date returnDate;
}
