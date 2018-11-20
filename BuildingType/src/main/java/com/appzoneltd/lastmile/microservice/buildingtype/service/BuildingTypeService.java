package com.appzoneltd.lastmile.microservice.buildingtype.service;

import java.util.List;

import com.appzoneltd.lastmile.microservice.buildingtype.dao.BuildingTypeEntity;

/**
 * @author alaa.nabil
 *
 */
public interface BuildingTypeService {
	List<BuildingTypeEntity> getAllBuildingtypes();
}
