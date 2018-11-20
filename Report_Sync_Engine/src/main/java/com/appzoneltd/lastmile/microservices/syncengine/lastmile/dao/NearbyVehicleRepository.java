package com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.NearbyVehicleEntity;

/**
 * Repository : NearbyVehicle.
 */
public interface NearbyVehicleRepository extends PagingAndSortingRepository<NearbyVehicleEntity, Long> {
	
	@Query(value = "SELECT n  FROM NearbyVehicleEntity n ORDER BY n.updated DESC ")
	List<NearbyVehicleEntity> getAllNearByOrdered();
	
	@Query(value = "SELECT n  FROM NearbyVehicleEntity n WHERE n.updated > :updatedTime ORDER BY n.updated DESC ")
	List<NearbyVehicleEntity> getLastUpdatedNearByOrdered(@Param("updatedTime") Date updatedTime);

}
