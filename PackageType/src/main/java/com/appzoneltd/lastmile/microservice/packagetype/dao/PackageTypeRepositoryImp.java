package com.appzoneltd.lastmile.microservice.packagetype.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;

/**
 * 
 * 
 * 
 * @author Alaa Nabil All Queries used in Package Service .
 */

@Repository
public class PackageTypeRepositoryImp extends AbstractDao<PackageType> implements PackageTypeRepository{

	@Autowired
	public PackageTypeRepositoryImp(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new PackageTypeRowMapper(), null, null, null, FIND_ALL_PACKAGE_TYPE_QUERY, null, null,
				null);
	}
}
