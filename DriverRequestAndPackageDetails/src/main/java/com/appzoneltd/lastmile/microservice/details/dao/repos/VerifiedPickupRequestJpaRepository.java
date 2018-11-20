package com.appzoneltd.lastmile.microservice.details.dao.repos;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.appzoneltd.lastmile.microservice.details.dao.entity.VerifiedPickupRequestEntity;

/**
 * Repository : VerifiedPickupRequest.
 */
public interface VerifiedPickupRequestJpaRepository
		extends PagingAndSortingRepository<VerifiedPickupRequestEntity, Long> {

}
