package com.appzoneltd.lastmile.microservice.activevehicle.service;

import java.security.Principal;
import java.util.List;

import com.appzoneltd.lastmile.microservice.activevehicle.dao.ActiveVehicleEntity;
import com.appzoneltd.lastmile.microservice.activevehicle.dto.ActiveVehicle;
import com.appzoneltd.lastmile.microservice.activevehicle.dto.ActiveVehicleInfo;
import com.appzoneltd.lastmile.microservice.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.exception.DuplicatedKeyException;

public interface ActiveVehicleService {

	ActiveVehicleEntity createActiveVehicle(ActiveVehicle activeVehicle) throws DuplicatedKeyException;

	void deleteActiveVehicle(Long id);

	ActiveVehicleEntity getActiveVehicleById(Long id);

	ActiveVehicleEntity updateActiveVehicle(ActiveVehicle activeVehicle);

	long countActiveVehicles(Principal principal);

	List<ActiveVehicleInfo> getActiveVehicles(int page, int maxResult, OrderBy orderBy, Principal principal);

	List<ActiveVehicleInfo> getActiveVehiclesOnDemand(int page, int maxResult, OrderBy orderBy, Long hubId,  Principal principal);

	ActiveVehicleEntity updateActiveVehicleStatus(boolean active, Long id);

	ActiveVehicleInfo getActiveVehicle(Long id);

}
