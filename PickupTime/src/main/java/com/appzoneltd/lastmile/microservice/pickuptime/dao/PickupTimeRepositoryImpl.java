/**
 * Jul 20, 201610:44:56 AM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.pickuptime.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;

/**
 * @author alaa.nabil
 *
 */
@Repository
public class PickupTimeRepositoryImpl extends AbstractDao<PickupTime> implements PickupTimeRepository {
	@Autowired
	public PickupTimeRepositoryImpl(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new PickupTimeRowMapper(), null, null, null, SQL_SELECT_ALL, null, null, null);
	}
}
