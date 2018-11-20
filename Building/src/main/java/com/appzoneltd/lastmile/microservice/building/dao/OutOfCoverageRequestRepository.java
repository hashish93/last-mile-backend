package com.appzoneltd.lastmile.microservice.building.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.building.entity.OutOfCoverageRequestEntity;

/**
 * Repository : OutOfCoverageRequest.
 */
public interface OutOfCoverageRequestRepository extends PagingAndSortingRepository<OutOfCoverageRequestEntity, Long> {

}
