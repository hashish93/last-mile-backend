package com.appzoneltd.lastmile.microservice.activevehicle.dao;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Repository : ActiveVehicle.
 */
@Repository
public interface ActiveVehicleJpaRepository extends PagingAndSortingRepository<ActiveVehicleEntity, Long> {

	List<ActiveVehicleEntity> findAllByOrderByCreatedAsc();

	List<ActiveVehicleEntity> findAllByOrderByCreatedDesc();

	@Query(value = "SELECT COUNT(*) FROM lastmile_core.active_vehicle v WHERE v.vehicle_id=:vid AND v.driver_id=:drid AND v.device_id=:deid  AND v.work_shift_id=:wsid", nativeQuery = true)
	int countActiveVehiceWithAllCriteria(@Param("vid") Long vId, @Param("drid") Long drId, @Param("deid") Long deId,
			@Param("wsid") Long wsId);

	@Query(value = "SELECT COUNT(*) FROM lastmile_core.active_vehicle v WHERE v.vehicle_id=:vid AND v.work_shift_id=:wsid", nativeQuery = true)
	int countActiveVehiceWithVehicleAndWorkShift(@Param("vid") Long vId, @Param("wsid") Long wsId);

	@Query(value = "SELECT COUNT(*) FROM lastmile_core.active_vehicle v WHERE v.driver_id=:drid AND v.work_shift_id=:wsid", nativeQuery = true)
	int countActiveVehiceWithDriverAndWorkShift(@Param("drid") Long drId, @Param("wsid") Long wsId);

	@Query(value = "SELECT COUNT(*) FROM lastmile_core.active_vehicle v WHERE v.device_id=:deid AND v.work_shift_id=:wsid", nativeQuery = true)
	int countActiveVehiceWithDeviceAndWorkShift(@Param("deid") Long deId, @Param("wsid") Long wsId);

	@Query(value = "SELECT a from ActiveVehicleEntity a inner join a.vehicle v where v.purpose = 'SCHEDULED_SERVICES' AND v.buildingId = :hubId  ")
	List<ActiveVehicleEntity> getScheduledActiveVehicles(@Param("hubId") Long hubId);

	@Query(value = "SELECT a from ActiveVehicleEntity a inner join a.vehicle v where v.purpose = 'SCHEDULED_SERVICES' AND v.buildingId = :hubId  ")
	List<ActiveVehicleEntity> getScheduledActiveVehiclesPaged(Pageable pageable, @Param("hubId") Long hubId);

	@Query(value = "SELECT a from ActiveVehicleEntity a where a.vehicle.purpose = 'SCHEDULED_SERVICES'  ")
	List<ActiveVehicleEntity> getScheduledActiveVehiclesToSuperAdmin();

	@Query(value = "SELECT a from ActiveVehicleEntity a where a.vehicle.purpose = 'SCHEDULED_SERVICES'  ")
	List<ActiveVehicleEntity> getScheduledActiveVehiclesPagedToSuperAdmin(Pageable pageable);

	@Query(value = "SELECT a from ActiveVehicleEntity a WHERE a.vehicle.buildingId IN (:hubs)")
	List<ActiveVehicleEntity> findAllByHubs(Pageable pageable, @Param("hubs") List<Long> hubs);
	
	
	@Query(value="SELECT COUNT(*) FROM ActiveVehicleEntity a WHERE a.vehicle.buildingId IN (:hubs)")
	long countActiveVehiclesToNonSuperAdmin(@Param("hubs") List<Long> hubs);

}
