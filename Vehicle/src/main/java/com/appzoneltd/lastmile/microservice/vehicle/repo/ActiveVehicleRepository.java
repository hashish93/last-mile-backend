package com.appzoneltd.lastmile.microservice.vehicle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.vehicle.entity.ActiveVehicleEntity;

/**
 * Repository : ActiveVehicle.
 */
public interface ActiveVehicleRepository extends PagingAndSortingRepository<ActiveVehicleEntity, Long> {

	
	@Query("SELECT a From ActiveVehicleEntity a inner join a.vehicle v WHERE v.vehicleId = :vehicleId")
	List<ActiveVehicleEntity> getActiveVehicleByVehicleId(@Param("vehicleId") Long vehicleId);
}
