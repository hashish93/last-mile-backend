package com.appzoneltd.lastmile.microservice.packagetype.service;

import java.util.List;

import com.appzoneltd.lastmile.microservice.packagetype.dao.PackageType;

public interface PackageTypeService {
	List<PackageType> findAllPackageTypes();
}
