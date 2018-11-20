package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.DriverEntity;

/**
 * Repository : Driver.
 */
public interface DriverJpaRepository extends PagingAndSortingRepository<DriverEntity, Long> {

}
