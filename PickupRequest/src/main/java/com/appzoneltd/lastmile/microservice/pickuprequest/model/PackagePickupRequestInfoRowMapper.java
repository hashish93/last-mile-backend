package com.appzoneltd.lastmile.microservice.pickuprequest.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PackagePickupRequestInfoRowMapper implements RowMapper<PackagePickupRequestInfo> {

	@Override
	public PackagePickupRequestInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PackagePickupRequestInfo pickupRequestInfo = new PackagePickupRequestInfo();
		pickupRequestInfo.setRequestId(rs.getLong("pickup_request_id"));
		pickupRequestInfo.setRequesterId(rs.getLong("requester_id"));
		pickupRequestInfo.setAddress(rs.getString("pickupformatedaddress"));
		// return result
		return pickupRequestInfo;
	}
	
}
