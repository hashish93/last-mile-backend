package com.appzoneltd.lastmile.microservice.packge.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.packge.entity.PackageTypeEntity;

/**
 * Repository : PackageType.
 */
public interface PackageTypeRepository extends PagingAndSortingRepository<PackageTypeEntity, Long> {

}
