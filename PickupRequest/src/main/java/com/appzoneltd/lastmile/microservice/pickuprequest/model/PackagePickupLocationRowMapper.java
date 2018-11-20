package com.appzoneltd.lastmile.microservice.pickuprequest.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PackagePickupLocationRowMapper implements RowMapper<PackagePickupLocation>  {
	


	@Override
	public PackagePickupLocation mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PackagePickupLocation location = new PackagePickupLocation();
		location.setLatitude(rs.getString("pickuplatitude"));
		location.setLongitude(rs.getString("pickuplongitude"));
		
		return location;
	}

}
