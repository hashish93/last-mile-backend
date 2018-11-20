package com.appzoneltd.lastmile.microservice.employee.dao;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.employee.entity.ActiveVehicleEntity;

/**
 * Repository : ActiveVehicle.
 */
public interface ActiveVehicleJpaRepository extends PagingAndSortingRepository<ActiveVehicleEntity, Long> {
	
	@Query("SELECT DISTINCT a FROM ActiveVehicleEntity a inner join a.driver d WHERE d.id=:driverId")
	ActiveVehicleEntity getActiveVehicleByDriverId(@Param("driverId")Long driverId);

}
