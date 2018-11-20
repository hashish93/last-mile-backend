package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.CancelationReasonEntity;

/**
 * Repository : CancelationReason.
 */
public interface CancelationReasonJpaRepository extends PagingAndSortingRepository<CancelationReasonEntity, Long> {

}
