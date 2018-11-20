package com.appzoneltd.lastmile.microservice.ondemandrequest.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;

@Repository
public class PickupRequestRepositoryImp extends AbstractDao<PickupRequestInfo> implements PickupRequestRepository {
	
	@Autowired
	public PickupRequestRepositoryImp(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new PickupRequestRowMapper(), SQL_INSERT, null, null, null, SQL_SELECT_BY_ID,
				null, SQL_COUNT_ALL);
	}

}
