package com.appzoneltd.lastmile.microservice.employee.dao;

import javax.validation.constraints.Pattern;
import java.util.Date;

public class Driver extends Employee {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5533311237669674828L;
//	@NotNull(message = "driver.expdate.notnull")
	private Date drivingLicenseExpDate;
	private Long drivingLicenseTypeId;
	@Pattern(regexp = "[a-zA-Z0-9\u0621-\u064A\u0660-\u0669 ]*$", message = "employee.drivinglicenseid.errorformat")
	private String drivingLicenseId;

	public Driver() {
	}

	public Driver(Date drivingLicenseExpDate, Long drivingLicenseTypeId, String drivingLicenseId) {
		super();
		this.drivingLicenseExpDate = drivingLicenseExpDate;
		this.drivingLicenseTypeId = drivingLicenseTypeId;
		this.drivingLicenseId = drivingLicenseId;
	}

	public Date getDrivingLicenseExpDate() {
		return drivingLicenseExpDate;
	}

	public void setDrivingLicenseExpDate(Date drivingLicenseExpDate) {
		this.drivingLicenseExpDate = drivingLicenseExpDate;
	}

	public Long getDrivingLicenseTypeId() {
		return drivingLicenseTypeId;
	}

	public void setDrivingLicenseTypeId(Long drivingLicenseTypeId) {
		this.drivingLicenseTypeId = drivingLicenseTypeId;
	}

	public String getDrivingLicenseId() {
		return drivingLicenseId;
	}

	public void setDrivingLicenseId(String drivingLicenseId) {
		this.drivingLicenseId = drivingLicenseId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
