package com.appzoneltd.lastmile.microservice.SystemConfig.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;

/**
 * 
 * @author Ahmed Atef Queries used in System Config Service .
 */

@Repository
public class SystemConfigRepositoryImp extends AbstractDao<SystemConfig> implements SystemConfigRepository {

	@Autowired
	public SystemConfigRepositoryImp(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new SystemConfigRowMapper(), CREATE_SYSTEM_CONFIG_QUERY, UPDATE_SYSTEM_CONFIG_QUERY, null,
				FIND_ALL_SYSTEM_CONFIG_QUERY, FIND_SYSTEM_CONFIG_BY_ID, null, COUNT_ALL_QUERY);
	}

}
