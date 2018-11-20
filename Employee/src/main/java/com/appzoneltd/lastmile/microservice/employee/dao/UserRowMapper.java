package com.appzoneltd.lastmile.microservice.employee.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.appzoneltd.lastmile.microservice.enums.UserType;

public class UserRowMapper implements RowMapper<Employee> {
	
	private Employee employee;

	@Override
	public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
		employee = new Employee();
		employee.setUserId(rs.getLong("id"));
		employee.setUsername(rs.getString("username"));
		employee.setPhone(rs.getString("phone"));
		employee.setEmail(rs.getString("email"));
		employee.setUserType(UserType.valueOf(rs.getString("user_type")));
		employee.setCreated(rs.getTimestamp("created"));
		return employee;
	}

}
