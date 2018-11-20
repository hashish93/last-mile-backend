package com.appzoneltd.lastmile.microservice.packagelabel.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

/**
 * @author alaa.nabil
 *
 */
public class PackageLabelRowMapper implements RowMapper<PackageLabel> {

	@Override
	public PackageLabel mapRow(ResultSet rs, int num) throws SQLException {
		PackageLabel packageLabel = new PackageLabel();
		packageLabel.setPackageLabelId(rs.getLong("package_label_id"));
		packageLabel.setLabel(rs.getString("label"));
		packageLabel.setCreated(rs.getTimestamp("created"));
		packageLabel.setVersion(rs.getLong("version"));
		return packageLabel;
	}
}
