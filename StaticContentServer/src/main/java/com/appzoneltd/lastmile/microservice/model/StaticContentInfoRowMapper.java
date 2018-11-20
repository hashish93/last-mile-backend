package com.appzoneltd.lastmile.microservice.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class StaticContentInfoRowMapper implements RowMapper<StaticContentInfo> {

	@Override
	public StaticContentInfo mapRow(ResultSet rs, int rowNum) throws SQLException {

		StaticContentInfo staticContentInfo = new StaticContentInfo();
		staticContentInfo.setFilePhysicalPath(rs.getString("contentpath"));
		staticContentInfo.setHttpContentType(rs.getString("httpcontenttype"));
		staticContentInfo.setExtension(rs.getString("extension"));
		staticContentInfo.setServerId(rs.getString("server_id"));

		return staticContentInfo;
	}

}
