package com.appzoneltd.lastmile.microservice.manualdistribution.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.manualdistribution.entity.SystemConfig;

@Repository
public interface SystemConfigRepo extends CrudRepository<SystemConfig, Long> {
	@Query(value="SELECT rpt FROM SystemConfig rpt WHERE rpt.id=12")
	public SystemConfig getRequestProcessingTime();
	
	
	@Query(value="SELECT rpt FROM SystemConfig rpt WHERE rpt.id=13")
	public SystemConfig getGracePeriodInMinutes();
	
	
	@Query(value="SELECT rpt FROM SystemConfig rpt WHERE rpt.id=11")
	public SystemConfig getStartTimeIncrementInMinutes();
	
	//get the emeregency capacity of vehicles
	@Query(value="SELECT rpt FROM SystemConfig rpt WHERE rpt.id=8")
	public SystemConfig getVehiclesEmergencyCapacity();
	
	
}
