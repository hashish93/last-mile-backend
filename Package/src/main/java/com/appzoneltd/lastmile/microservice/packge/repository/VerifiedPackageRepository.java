package com.appzoneltd.lastmile.microservice.packge.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.packge.entity.VerifiedPackageEntity;

/**
 * Repository : VerifiedPackage.
 */
public interface VerifiedPackageRepository extends PagingAndSortingRepository<VerifiedPackageEntity, Long> {

}
