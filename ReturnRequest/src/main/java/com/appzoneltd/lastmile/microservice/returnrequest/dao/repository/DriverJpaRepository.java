package com.appzoneltd.lastmile.microservice.returnrequest.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.DriverEntity;

/**
 * Repository : Driver.
 */
public interface DriverJpaRepository extends PagingAndSortingRepository<DriverEntity, Long> {

}
