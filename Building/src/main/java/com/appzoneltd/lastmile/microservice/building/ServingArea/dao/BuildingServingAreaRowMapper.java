package com.appzoneltd.lastmile.microservice.building.ServingArea.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;



public class BuildingServingAreaRowMapper implements RowMapper<BuildingServingAreaLocationHolder> {

	@Override
	public BuildingServingAreaLocationHolder mapRow(ResultSet rs, int rowNum) throws SQLException {
		BuildingServingAreaLocationHolder building = new BuildingServingAreaLocationHolder();

		building.setBuildingId(rs.getLong("building_id"));
		building.setLongitude(rs.getString("longitude"));
		building.setLatitude(rs.getString("latitude"));

	
		return building;
	}

}
