package com.appzoneltd.lastmile.microservice.vehicletype.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class VehicleTypeRowMapper implements RowMapper<VehicleType> {

	@Override
	public VehicleType mapRow(ResultSet rs, int rowNum) throws SQLException {
		VehicleType vehiclType = new VehicleType();

		vehiclType.setVehicleTypeId(rs.getLong("vehicle_type_id"));
		vehiclType.setType(rs.getString("type"));
		vehiclType.setCreated(rs.getDate("created"));
		vehiclType.setVersion(rs.getLong("version"));
		return vehiclType;
	}
}
