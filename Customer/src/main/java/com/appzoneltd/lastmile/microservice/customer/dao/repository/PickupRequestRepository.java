package com.appzoneltd.lastmile.microservice.customer.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.PickupRequestEntity;

/**
 * Repository : PickupRequest.
 */
public interface PickupRequestRepository extends PagingAndSortingRepository<PickupRequestEntity, Long> {

	PickupRequestEntity findByPickupRequestId(Long requestId);
	
	List<PickupRequestEntity> findByRequesterId(Long requesterId);
	
}
