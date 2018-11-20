package com.appzoneltd.lastmile.microservice.workflowservice.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 * Repository : ActiveVehicle.
 */
public interface ActiveVehicleRepository extends PagingAndSortingRepository<ActiveVehicleEntity, Long> {
	
	ActiveVehicleEntity findByDriverId(Long driverId);

}
