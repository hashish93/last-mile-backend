package com.appzoneltd.lastmile.microservice.pickuprequest.dao;

import org.springframework.data.repository.PagingAndSortingRepository;


/**
 * Repository : VerifiedPickupRequest.
 */
public interface VerifiedPickupRequestJpaRepository
		extends PagingAndSortingRepository<VerifiedPickupRequestEntity, Long> {

}
