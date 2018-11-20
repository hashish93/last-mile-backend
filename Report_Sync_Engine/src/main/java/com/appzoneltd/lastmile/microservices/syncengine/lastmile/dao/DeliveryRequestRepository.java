package com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.DeliveryRequestEntity;

/**
 * Repository : DeliveryRequest.
 */
public interface DeliveryRequestRepository extends PagingAndSortingRepository<DeliveryRequestEntity, Long> {
	
	@Query(value = "SELECT d FROM DeliveryRequestEntity d ORDER BY d.updated DESC")
	List<DeliveryRequestEntity> getAllDeliveryOrdered();

	@Query(value = "SELECT d  FROM DeliveryRequestEntity d WHERE d.updated > :updatedTime ORDER BY d.updated DESC")
	List<DeliveryRequestEntity> getLastUpdatedAllDeliveryOrdered(@Param("updatedTime") Date updatedTime);
}
