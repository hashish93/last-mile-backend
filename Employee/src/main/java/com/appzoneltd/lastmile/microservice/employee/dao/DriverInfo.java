package com.appzoneltd.lastmile.microservice.employee.dao;

public class DriverInfo extends Driver {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8238306309572134711L;
	private String drivingLicenseType;

	public String getDrivingLicenseType() {
		return drivingLicenseType;
	}

	public void setDrivingLicenseType(String drivingLicenseType) {
		this.drivingLicenseType = drivingLicenseType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
