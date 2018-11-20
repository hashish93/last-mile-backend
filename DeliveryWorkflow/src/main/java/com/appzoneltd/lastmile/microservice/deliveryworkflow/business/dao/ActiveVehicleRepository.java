package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.ActiveVehicleEntity;

/**
 * Repository : ActiveVehicle.
 */
public interface ActiveVehicleRepository extends CrudRepository<ActiveVehicleEntity, Long> {

	@Query("SELECT a from ActiveVehicleEntity a where a.driver.id=:driverId")
	public ActiveVehicleEntity getActiveVehicleForDriverId(@Param("driverId") Long driverId);

	public ActiveVehicleEntity findById(Long driverId);

}
