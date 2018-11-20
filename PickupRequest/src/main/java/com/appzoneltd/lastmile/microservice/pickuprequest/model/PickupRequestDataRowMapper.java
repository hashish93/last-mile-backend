package com.appzoneltd.lastmile.microservice.pickuprequest.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PickupRequestDataRowMapper implements RowMapper<PickupRequestData> {

	@Override
	public PickupRequestData mapRow(ResultSet rs, int rowNum) throws SQLException {
		
		PickupRequestData pickupRequestData = new PickupRequestData();
		pickupRequestData.setRequestId(rs.getLong("pickup_request_id"));
		pickupRequestData.setRequesterId(rs.getLong("requester_id"));
		pickupRequestData.setRequestType(rs.getString("request_type"));
		pickupRequestData.setHubId(rs.getLong("hub_id"));
		
		return pickupRequestData;
	}

}
