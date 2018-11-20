/**
 * Jul 20, 201610:44:56 AM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.vehicletype.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;

/**
 * @author alaa.nabil
 *
 */
@Repository
public class VehicleTypeRepositoryImpl extends AbstractDao<VehicleType> implements VehicleTypeRepository {
	@Autowired
	public VehicleTypeRepositoryImpl(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new VehicleTypeRowMapper(), null, null, null, SQL_SELECT_ALL, null, null, null);
	}

}
