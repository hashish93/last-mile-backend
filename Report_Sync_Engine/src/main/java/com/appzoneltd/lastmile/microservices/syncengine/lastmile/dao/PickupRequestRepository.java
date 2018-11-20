package com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.PickupRequestEntity;

/**
 * Repository : PickupRequest.
 */
public interface PickupRequestRepository extends CrudRepository<PickupRequestEntity, Long> {

	@Query(value = "SELECT p.hubId  FROM PickupRequestEntity p WHERE p.pickupRequestId=:requestId ")
	Long getHubIdFromRequestId(@Param("requestId") Long requestId);
	
	@Query(value = "SELECT p  FROM PickupRequestEntity p ORDER BY p.updated DESC")
	List<PickupRequestEntity> getAllPickupOrdered();
	
	@Query(value = "SELECT p  FROM PickupRequestEntity p WHERE p.updated > :updatedTime ORDER BY p.updated DESC")
	List<PickupRequestEntity> getLastUpdatedAllPickupOrdered(@Param("updatedTime") Date updatedTime);
	
	@Query(value = "SELECT p  FROM PickupRequestEntity p WHERE p.requestStatus = 'CANCELED' ORDER BY p.updated DESC")
	List<PickupRequestEntity> getAllCanceledOrdered();
	
	@Query(value = "SELECT p  FROM PickupRequestEntity p WHERE p.requestStatus = 'CANCELED' AND p.updated > :updatedTime ORDER BY p.updated DESC ")
	List<PickupRequestEntity> getLastUpdatedCanceledOrdered(@Param("updatedTime") Date updatedTime);
	
	@Query(value = "SELECT p  FROM PickupRequestEntity p WHERE p.requestStatus = 'CANCELED' OR p.requestStatus = 'PICKEDUP' ORDER BY p.updated DESC")
	List<PickupRequestEntity> getAllPickedupAndCanceledOrdered();
	
	@Query(value = "SELECT p  FROM PickupRequestEntity p WHERE (p.requestStatus = 'CANCELED' OR p.requestStatus = 'PICKEDUP') AND p.updated > :updatedTime ORDER BY p.updated DESC ")
	List<PickupRequestEntity> getLastUpdatedPickedupAndCanceledOrdered(@Param("updatedTime") Date updatedTime);
}
