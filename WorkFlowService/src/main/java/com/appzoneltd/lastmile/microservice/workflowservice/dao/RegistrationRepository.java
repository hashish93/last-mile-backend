package com.appzoneltd.lastmile.microservice.workflowservice.dao;

import java.util.List;

import org.springframework.data.couchbase.core.query.N1qlPrimaryIndexed;
import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservice.workflowservice.model.RegistrationModel;

/**
 * @author alaa.nabil
 *
 */
@N1qlPrimaryIndexed
public interface RegistrationRepository extends CrudRepository<RegistrationModel, Long> {
	RegistrationModel findByVehicleIdAndDriverIdAndDeviceIdAndWorkShiftId(Long vehicleId, Long driverId, Long deviceId,
			Long workShiftId);

	List<RegistrationModel> findAllByVehicleId(Long id);

	List<RegistrationModel> findAllByDriverId(Long id);

}
