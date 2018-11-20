package com.appzoneltd.lastmile.microservice.lookup.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.lookup.entity.DrivingLicenseTypeEntity;

/**
 * Repository : DrivingLicenseType.
 */
public interface DrivingLicenseTypeRepository extends PagingAndSortingRepository<DrivingLicenseTypeEntity, Long> {

}
