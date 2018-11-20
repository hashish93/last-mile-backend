package com.appzoneltd.lastmile.microservice.activevehicle.couchbasedao;

import java.util.List;

import org.springframework.data.couchbase.core.query.ViewIndexed;
import org.springframework.data.repository.CrudRepository;

/**
 * @author alaa.nabil
 *
 */
@ViewIndexed(designDoc = "registrationrepository")
public interface RegistrationRepository extends CrudRepository<RegistrationModel, Long> {
	RegistrationModel findByVehicleIdAndDriverIdAndDeviceIdAndWorkShiftId(Long vehicleId, Long driverId, Long deviceId,
			Long workShiftId);

	List<RegistrationModel> findAllByVehicleId(Long id);

	RegistrationModel findByDriverId(Long id);

}
