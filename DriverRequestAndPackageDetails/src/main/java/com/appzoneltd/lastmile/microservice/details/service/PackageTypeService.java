package com.appzoneltd.lastmile.microservice.details.service;

import java.util.List;

import com.appzoneltd.lastmile.microservice.details.dto.PackageType;

public interface PackageTypeService {
	List<PackageType> findAllPackageTypes();
}
