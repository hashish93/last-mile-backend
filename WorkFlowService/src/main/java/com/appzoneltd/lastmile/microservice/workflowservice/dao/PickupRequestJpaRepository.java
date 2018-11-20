package com.appzoneltd.lastmile.microservice.workflowservice.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : PickupRequest.
 */
public interface PickupRequestJpaRepository extends PagingAndSortingRepository<PickupRequestEntity, Long> {

	PickupRequestEntity findByPickupRequestId(Long requestId);
}
