package com.appzoneltd.lastmile.microservice.ondemandrequest.dao;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RequestHistoryRowMapper implements RowMapper<RequestHistory> {

	@Override
	public RequestHistory mapRow(ResultSet rs, int rowNum) throws SQLException {
		return null;
	}

}
