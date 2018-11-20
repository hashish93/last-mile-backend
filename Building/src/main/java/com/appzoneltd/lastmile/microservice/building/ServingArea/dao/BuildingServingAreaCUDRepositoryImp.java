package com.appzoneltd.lastmile.microservice.building.ServingArea.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;
import com.appzoneltd.lastmile.microservice.building.dao.BuildingRepository;
import com.appzoneltd.lastmile.microservice.building.dao.BuildingRowMapper;
import com.appzoneltd.lastmile.microservice.building.model.BuildingInfo;

@Repository
public class BuildingServingAreaCUDRepositoryImp extends AbstractDao<BuildingInfo> implements BuildingRepository{

	@Autowired
	public BuildingServingAreaCUDRepositoryImp(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new BuildingRowMapper(), BUILDING_SERVING_AREA_CREATE_QUERY, null, null , null, null, BUILDING_SERVING_AREA_DELETED_QUERY, null);
		
	}

}
