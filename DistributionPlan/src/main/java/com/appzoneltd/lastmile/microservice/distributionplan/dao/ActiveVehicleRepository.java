package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : ActiveVehicle.
 */
public interface ActiveVehicleRepository extends PagingAndSortingRepository<ActiveVehicleEntity, Long> {

	ActiveVehicleEntity findByDriverId(Long driverId);

	ActiveVehicleEntity findByDriverIdAndVehicleVehicleIdAndDevicesDeviceIdAndWorkShiftId(Long driverId,
			Long vehicleVehicleId, Long devicesDeviceId, Long workShiftId);

}
