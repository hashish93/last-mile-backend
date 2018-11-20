package com.appzoneltd.lastmile.microservice.employee.service;

import java.security.Principal;
import java.util.List;

import com.appzoneltd.lastmile.microservice.employee.dao.Driver;
import com.appzoneltd.lastmile.microservice.employee.dao.DriverInfo;
import com.appzoneltd.lastmile.microservice.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface DriverService {
	Object saveDriver(Driver driver) throws JsonProcessingException;

	Object updateDriver(Driver driver) throws EntityNotFoundException;

	Driver findDriverById(Long driverId);

	List<DriverInfo> findAllDriversByPage(int pageCount, int pageNum, OrderBy orderType);

	int markDeleteDriver(Long driverId) throws JsonProcessingException;

	int countAllDrivers();

	boolean checkDriverRelatedToActiveVehicle(Long driverId);

	Driver getDriverProfileByPrincipal(Principal principal);
}
