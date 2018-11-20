package com.appzoneltd.lastmile.microservice.customer.dao.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.PackageTypeEntity;

/**
 * Repository : PackageType.
 */
public interface PackageTypeRepository extends PagingAndSortingRepository<PackageTypeEntity, Long> {

}
