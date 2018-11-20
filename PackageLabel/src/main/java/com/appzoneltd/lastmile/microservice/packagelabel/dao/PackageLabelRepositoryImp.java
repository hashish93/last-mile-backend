/**
 * Jul 20, 201610:44:56 AM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.packagelabel.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;

/**
 * @author alaa.nabil
 *
 */
@Repository
public class PackageLabelRepositoryImp extends AbstractDao<PackageLabel> implements PackageLabelRepository {

	@Autowired
	public PackageLabelRepositoryImp(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new PackageLabelRowMapper(), null, null, null, SQL_SELECT_ALL, null, null, null);
	}
}
