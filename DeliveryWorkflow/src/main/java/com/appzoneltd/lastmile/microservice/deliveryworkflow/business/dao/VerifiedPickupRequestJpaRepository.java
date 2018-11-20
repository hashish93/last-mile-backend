package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.VerifiedPickupRequestEntity;

/**
 * Repository : VerifiedPickupRequest.
 */
public interface VerifiedPickupRequestJpaRepository
		extends PagingAndSortingRepository<VerifiedPickupRequestEntity, Long> {

}
