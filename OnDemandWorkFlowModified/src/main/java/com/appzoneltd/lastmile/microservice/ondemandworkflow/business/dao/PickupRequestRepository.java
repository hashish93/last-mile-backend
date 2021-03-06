package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PickupRequestEntity;

/**
 * Repository : PickupRequest.
 */
public interface PickupRequestRepository extends PagingAndSortingRepository<PickupRequestEntity, Long> {

	PickupRequestEntity findByPickupRequestId(Long requestId);
	
}
