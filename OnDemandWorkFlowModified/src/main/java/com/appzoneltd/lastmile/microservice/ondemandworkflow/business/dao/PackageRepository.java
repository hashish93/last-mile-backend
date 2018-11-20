package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PackageEntity;

/**
 * Repository : Package.
 */
public interface PackageRepository extends PagingAndSortingRepository<PackageEntity, Long> {

}
