package com.appzoneltd.lastmile.microservice.employee.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.employee.dao.ActiveVehicleJpaRepository;
import com.appzoneltd.lastmile.microservice.employee.entity.ActiveVehicleEntity;

@Service
public class ActiveVehicleService {

	@Autowired
	private ActiveVehicleJpaRepository activeVehicleRepository;
	
	public boolean isDriverRelatedToActiveVehicle(Long userId){
		 
		ActiveVehicleEntity activeVehicleEntity = activeVehicleRepository.getActiveVehicleByDriverId(userId);
		if(activeVehicleEntity !=null)
			return true;
		return false;
	}

}
