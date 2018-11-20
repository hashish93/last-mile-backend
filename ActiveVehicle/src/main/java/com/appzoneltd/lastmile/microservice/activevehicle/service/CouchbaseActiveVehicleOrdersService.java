package com.appzoneltd.lastmile.microservice.activevehicle.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.activevehicle.couchbasedao.JobOrder;
import com.appzoneltd.lastmile.microservice.activevehicle.couchbasedao.RegistrationModel;
import com.appzoneltd.lastmile.microservice.activevehicle.couchbasedao.RegistrationRepository;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;

@Service
public class CouchbaseActiveVehicleOrdersService {
	private final RegistrationRepository registrationRepository;

	@Autowired
	public CouchbaseActiveVehicleOrdersService(RegistrationRepository registrationRepository) {
		this.registrationRepository = registrationRepository;
	}

	public List<JobOrder> vehicleJobOrders(Long id) throws EntityNotFoundException {
		// ActiveVehicleEntity entity = activeVehicleJpaRepository.findOne(id);
		// RegistrationModel model = registrationRepository
		// .findByVehicleIdAndDriverIdAndDeviceIdAndWorkShiftId(entity.getVehicle().getVehicleId(),
		// entity.getDriver().getId(), entity.getDevices().getDeviceId(),
		// entity.getWorkShift().getId());
		RegistrationModel model = registrationRepository.findOne(id);
		if (model == null)
			throw new EntityNotFoundException("Check ID On Couchbase (Not Found ID)");

		return model.getJobOrders();
	}
}
