package com.appzoneltd.lastmile.microservice.model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DownloadStaticContentRowMapper implements RowMapper<LastMileFile> {

	@Override
	public LastMileFile mapRow(ResultSet rs, int rowNum) throws SQLException {

		LastMileFile result = new LastMileFile();
		result.setFileName(rs.getString("contentname"));
		result.setFilePhysicalPath(rs.getString("contentpath"));
		result.setHttpContentType(rs.getString("httpcontenttype"));
		result.setExtension(rs.getString("extension"));
		result.setServerId(rs.getString("server_id"));

		return result;
	}

}
