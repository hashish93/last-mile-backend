package com.appzoneltd.lastmile.microservice.drivinglicensetype.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;

@Repository
public class DrivingLicenseTypeRepositoryImp extends AbstractDao<DrivingLicenseType> implements DrivingLicenseTypeRepository {

	@Autowired
	public DrivingLicenseTypeRepositoryImp(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new DrivingLicenseTypeRowMapper(), null, null, null, SQL_SELECT_ALL, SQL_SELECT, null,
				null);
	}
}
