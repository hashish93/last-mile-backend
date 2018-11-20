package com.appzoneltd.lastmile.microservice.manualdistribution.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.manualdistribution.entity.Location;


public interface HubLocationRepo extends CrudRepository<Location, Long>{
	
	@Query(value="SELECT l FROM Location l  WHERE l.hubId=:id")
	public Location getHubLocation(@Param("id") Long hubId);
	
}
