package com.appzoneltd.lastmile.microservice.building.ServingArea.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;
import com.appzoneltd.lastmile.microservice.building.dao.BuildingRepository;


@Repository
public class BuildingServingAreaFindAllExceptLocationRepositoryImp extends AbstractDao<BuildingServingAreaLocationHolder> implements BuildingRepository{

	@Autowired
	public BuildingServingAreaFindAllExceptLocationRepositoryImp(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new BuildingServingAreaRowMapper(), null, null, null , BUILDING_SERVING_AREA_FIND_ALL_EXCEPT_DETERMINED_ID, null, null, null);
		
	}

}
