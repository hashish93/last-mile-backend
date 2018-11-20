package com.appzoneltd.lastmile.microservice.details.service;

import java.security.Principal;

import com.appzoneltd.lastmile.microservice.details.dao.entity.UsersEntity;
import com.appzoneltd.lastmile.microservice.details.dto.ActivateAccountModel;
import com.appzoneltd.lastmile.microservice.details.dto.FreelancerDriver;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;

public interface FreelanceDriverService {

	Object saveFreelancerDriver(FreelancerDriver freelancerDriver);
	
	String activateFreelancerDriver(ActivateAccountModel tokenVerification) throws Exception;

	void toggleFreelancerOnOff(Principal driverPrincipal, Boolean boolean1);
	
	public String callLogin(UsersEntity usersEntity) throws Exception ;
}
