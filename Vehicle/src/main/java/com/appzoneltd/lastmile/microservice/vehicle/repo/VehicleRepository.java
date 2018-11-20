package com.appzoneltd.lastmile.microservice.vehicle.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.vehicle.entity.VehicleEntity;

/**
 * Repository : Vehicle.
 */
public interface VehicleRepository extends PagingAndSortingRepository<VehicleEntity, Long> {
	
//	@Query("SELECT v FROM VehicleEntity v WHERE v.status ='ACTIVE'")
//	public Page<VehicleEntity> getVehicleEntitiesForAdmin(Pageable pageable);

	@Query("SELECT v FROM VehicleEntity v inner join v.building b  WHERE b.buildingId IN (:hubs)")
	public Page<VehicleEntity> getVehicleEntitiesForNonAdmin(Pageable pageable , @Param("hubs") List<Long> hubs);
	
	@Query("SELECT v FROM VehicleEntity v WHERE v.vehicleId =:vehicleId ")
	public VehicleEntity findVehicleById(@Param("vehicleId") Long vehicleId);
	
	@Query("SELECT DISTINCT v FROM VehicleEntity v WHERE v.vehicleId <> :vehicleId AND v.plate = :plate")
	VehicleEntity checkPlateExists(@Param("vehicleId") Long vehicleId,@Param("plate") String plate);
	
	@Query("SELECT DISTINCT v FROM VehicleEntity v WHERE v.vehicleId <> :vehicleId AND v.motor = :motor")
	VehicleEntity checkMotorExists(@Param("vehicleId") Long vehicleId,@Param("motor") String motor);
}
