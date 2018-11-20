package com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.PackageTypeEntity;

/**
 * Repository : PackageType.
 */
public interface PackageTypeJpaRepository extends PagingAndSortingRepository<PackageTypeEntity, Long> {

}
