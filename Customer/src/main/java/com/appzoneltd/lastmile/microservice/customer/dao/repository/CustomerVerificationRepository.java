package com.appzoneltd.lastmile.microservice.customer.dao.repository;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.CustomerVerificationEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : CustomerVerification.
 */
public interface CustomerVerificationRepository extends PagingAndSortingRepository<CustomerVerificationEntity, Long> {

}
