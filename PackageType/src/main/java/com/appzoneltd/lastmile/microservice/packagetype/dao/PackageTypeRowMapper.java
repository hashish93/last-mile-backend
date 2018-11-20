package com.appzoneltd.lastmile.microservice.packagetype.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PackageTypeRowMapper implements RowMapper<PackageType> {

	@Override
	public PackageType mapRow(ResultSet rs, int rowNum) throws SQLException {
		PackageType packageType = new PackageType();

		packageType.setPackageTypeId(rs.getLong("package_type_id"));
		packageType.setPackageType(rs.getString("packagetype"));
		packageType.setPackageDimension(rs.getString("packagedimension"));
		packageType.setExpectedWeight(rs.getLong("expectedweight"));
		packageType.setDescription(rs.getString("description"));
		packageType.setCreated(rs.getTimestamp("created"));
		packageType.setVersion(rs.getLong("version"));
		return packageType;
	}

}
