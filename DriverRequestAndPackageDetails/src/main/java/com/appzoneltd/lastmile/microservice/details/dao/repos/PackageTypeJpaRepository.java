package com.appzoneltd.lastmile.microservice.details.dao.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.details.dao.entity.PackageTypeEntity;

/**
 * Repository : PackageType.
 */
public interface PackageTypeJpaRepository extends PagingAndSortingRepository<PackageTypeEntity, Long> {

}
