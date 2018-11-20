package com.appzoneltd.lastmile.microservice.drivinglicensetype.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DrivingLicenseTypeRowMapper implements RowMapper<DrivingLicenseType> {

	private DrivingLicenseType drivingLicenseType;

	@Override
	public DrivingLicenseType mapRow(ResultSet rs, int num) throws SQLException {
		drivingLicenseType = new DrivingLicenseType();
		drivingLicenseType.setDrivingLicenseTypeId(rs.getLong("driving_license_type_id"));
		drivingLicenseType.setLicenseType(rs.getString("license_type"));
		drivingLicenseType.setCreated(rs.getDate("created"));
		drivingLicenseType.setVersion(rs.getLong("version"));
		return drivingLicenseType;
	}

}
