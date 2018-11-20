package com.appzoneltd.lastmile.microservice.packagetype.dao;

public interface PackageTypeRepository {

	/** Query for Finding all records in Package type table . **/
	String FIND_ALL_PACKAGE_TYPE_QUERY = "SELECT package_type_id, packagetype, packagedimension, expectedweight, "
			+ "description, created, version " + "FROM lastmile_core.package_type";

}
