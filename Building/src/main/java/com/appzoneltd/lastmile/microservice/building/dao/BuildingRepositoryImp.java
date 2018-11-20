package com.appzoneltd.lastmile.microservice.building.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;
import com.appzoneltd.lastmile.microservice.building.model.BuildingInfo;

/**
 * 
 * @author Ahmed Atef Queries used in Building Service .
 */

@Repository
public class BuildingRepositoryImp extends AbstractDao<BuildingInfo> implements BuildingRepository {

	@Autowired
	public BuildingRepositoryImp(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new BuildingRowMapper(), BUILDING_CREATE_QUERY, BUILDING_UPDATE_QUERY,
				BUILDING_MARK_AS_DELETED_QUERY, BUILDING_FIND_ALL_QUERY, BUILDING_FIND_BY_ID_QUERY,
				BUILDING_DELETED_QUERY, BUILDING_COUNT_ALL);
	}
	
	
	public Long countAllVehicleRealtedWithBuilding(Long buildingId) {
		return jdbcTemplate.queryForObject(BUILDING_COUNT_RELATED_VEHICLE, Long.class, buildingId);
	}

}
