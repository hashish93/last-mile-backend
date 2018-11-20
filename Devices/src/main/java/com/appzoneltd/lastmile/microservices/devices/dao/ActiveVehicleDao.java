package com.appzoneltd.lastmile.microservices.devices.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.devices.entity.ActiveVehicleEntity;

public interface ActiveVehicleDao extends CrudRepository<ActiveVehicleEntity, Long>{
	
	@Query(value="SELECT COUNT (id) FROM lastmile_core.active_vehicle v WHERE v.device_id=:deviceId",nativeQuery=true)
	public Long countAllActiveVehicleWithDevice(@Param("deviceId") Long deviceId);
	

}
