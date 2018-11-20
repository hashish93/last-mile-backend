package com.appzoneltd.lastmile.microservice.pickuptime.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PickupTimeRowMapper implements RowMapper<PickupTime> {

	@Override
	public PickupTime mapRow(ResultSet rs, int rowNum) throws SQLException {
		PickupTime pickupTime = new PickupTime();
		pickupTime.setPickupTimeId(rs.getLong("pickup_time_id"));
		pickupTime.setFromTime(rs.getString("time_from"));
		pickupTime.setToTime(rs.getString("time_to"));
		pickupTime.setCreated(rs.getDate("created"));
		pickupTime.setVersion(rs.getLong("version"));
		return pickupTime;
	}

}
