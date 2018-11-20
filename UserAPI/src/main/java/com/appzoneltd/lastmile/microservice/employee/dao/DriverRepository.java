package com.appzoneltd.lastmile.microservice.employee.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.employee.entity.DriverEntity;

/**
 * Repository : Driver.
 */
public interface DriverRepository extends PagingAndSortingRepository<DriverEntity, Long> {


}
