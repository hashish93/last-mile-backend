package com.appzoneltd.lastmile.microservice.employee.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;

@Repository
public class UserRepositoryImp extends AbstractDao<Employee> implements UserRepository {

	@Autowired
	public UserRepositoryImp(JdbcTemplate jdbcTemplate) {
		super(jdbcTemplate, new UserRowMapper(), null, null, null , FIND_ALL_EMPLOYEE, null, null,null);
	}

}
