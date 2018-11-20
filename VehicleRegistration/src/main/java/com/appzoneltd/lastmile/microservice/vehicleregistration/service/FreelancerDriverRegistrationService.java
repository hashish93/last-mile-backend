package com.appzoneltd.lastmile.microservice.vehicleregistration.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.vehicleregistration.dao.RegistrationRepository;
import com.appzoneltd.lastmile.microservice.vehicleregistration.dao.UsersJpaRepository;

@Service
@ConditionalOnProperty(value = "features.FREELANCER_DRIVER.enabled", havingValue = "true")
public class FreelancerDriverRegistrationService extends RegistrationService {

	public FreelancerDriverRegistrationService(RegistrationRepository registrationRepository,
			UsersJpaRepository usersRepository, CouchbaseTemplate couchbaseTemplate) {
		super(registrationRepository, usersRepository, couchbaseTemplate);
	}

}
