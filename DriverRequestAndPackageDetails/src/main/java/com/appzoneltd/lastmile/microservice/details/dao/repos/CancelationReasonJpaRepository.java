package com.appzoneltd.lastmile.microservice.details.dao.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.details.dao.entity.CancelationReasonEntity;

/**
 * Repository : CancelationReason.
 */
public interface CancelationReasonJpaRepository extends PagingAndSortingRepository<CancelationReasonEntity, Long> {

}
