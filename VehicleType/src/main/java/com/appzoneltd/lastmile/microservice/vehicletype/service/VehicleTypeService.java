package com.appzoneltd.lastmile.microservice.vehicletype.service;

import java.util.List;

import com.appzoneltd.lastmile.microservice.vehicletype.dao.VehicleType;

/**
 * @author alaa.nabil
 *
 */
public interface VehicleTypeService {
	List<VehicleType> vehicleTypeFindAll();
}
