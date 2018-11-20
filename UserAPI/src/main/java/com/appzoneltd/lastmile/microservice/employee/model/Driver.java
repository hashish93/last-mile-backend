package com.appzoneltd.lastmile.microservice.employee.model;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class Driver extends Employee {

	private static final long serialVersionUID = 5533311237669674828L;

	@NotNull(message = "driver.drivingLicenseExpDate.required")
	private Date drivingLicenseExpDate;

	@NotNull(message = "driver.drivingLicenseTypeId.required")
	private Long drivingLicenseTypeId;

	@NotBlank(message = "driver.drivingLicenseId.required")
	@Pattern(regexp = "[a-zA-Z0-9\u0621-\u064A\u0660-\u0669 ]*$", message = "employee.drivingLicenseId.errorformat")
	private String drivingLicenseId;
	
	

}
