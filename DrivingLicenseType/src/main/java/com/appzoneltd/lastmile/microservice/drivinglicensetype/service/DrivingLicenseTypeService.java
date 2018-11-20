package com.appzoneltd.lastmile.microservice.drivinglicensetype.service;

import java.util.List;

import com.appzoneltd.lastmile.microservice.drivinglicensetype.dao.DrivingLicenseType;

public interface DrivingLicenseTypeService {
	List<DrivingLicenseType> drivingLicenseTypeFindAll();
}
