package com.appzoneltd.lastmile.microservice.drivinglicensetype.dao;

public interface DrivingLicenseTypeRepository {

	String SQL_SELECT_ALL = "SELECT driving_license_type_id, license_type, created, version "
			+ "FROM lastmile_core.driving_license_type";
	String SQL_SELECT = "SELECT driving_license_type_id, license_type, created, version "
			+ "FROM lastmile_core.driving_license_type WHERE driving_license_type_id=?";
}
