package com.appzoneltd.lastmile.microservice.employee.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.employee.entity.DrivingLicenseTypeEntity;

/**
 * Repository : DrivingLicenseType.
 */
public interface DrivingLicenseTypeRepository extends PagingAndSortingRepository<DrivingLicenseTypeEntity, Long> {

}
