package com.appzoneltd.lastmile.microservice.lookup.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.lookup.entity.PackageTypeEntity;

/**
 * Repository : PackageType.
 */
public interface PackageTypeRepository extends PagingAndSortingRepository<PackageTypeEntity, Long> {

}
