package com.appzoneltd.lastmile.microservice.customer.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.PackageEntity;

/**
 * Repository : Package.
 */
public interface PackageRepository extends PagingAndSortingRepository<PackageEntity, Long> {

}
