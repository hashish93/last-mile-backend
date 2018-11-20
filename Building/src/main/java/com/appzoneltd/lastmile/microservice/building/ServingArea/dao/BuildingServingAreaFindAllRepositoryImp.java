package com.appzoneltd.lastmile.microservice.building.ServingArea.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;
import com.appzoneltd.lastmile.microservice.building.dao.BuildingRepository;

@Repository
public class BuildingServingAreaFindAllRepositoryImp extends AbstractDao<BuildingServingAreaLocationHolder> implements BuildingRepository{

	@Autowired
	public BuildingServingAreaFindAllRepositoryImp(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new BuildingServingAreaRowMapper(), null, null, null , BUILDING_SERVING_AREA_FIND_ALL, null, null, null);
		
	}

}
