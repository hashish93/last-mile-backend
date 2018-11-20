package com.appzoneltd.lastmile.microservice.vehicleregistration.dao;

import org.springframework.data.repository.CrudRepository;

/**
 * @author alaa.nabil
 *
 */
//@ViewIndexed(designDoc = "registrationrepository")
public interface RegistrationRepository extends CrudRepository<RegistrationModel, Long> {
	RegistrationModel findByDriverId(Long id);

	RegistrationModel findByVehicleId(Long id);

}
