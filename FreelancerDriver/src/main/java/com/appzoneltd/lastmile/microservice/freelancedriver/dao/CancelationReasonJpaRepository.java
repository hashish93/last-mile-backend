package com.appzoneltd.lastmile.microservice.freelancedriver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.freelancedriver.model.CancelationReasonEntity;

/**
 * Repository : CancelationReason.
 */
public interface CancelationReasonJpaRepository extends PagingAndSortingRepository<CancelationReasonEntity, Long> {

}
