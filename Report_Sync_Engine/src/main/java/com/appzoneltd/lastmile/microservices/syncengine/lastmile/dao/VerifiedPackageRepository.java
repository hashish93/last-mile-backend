package com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.VerifiedPackageEntity;

/**
 * Repository : VerifiedPackage.
 */
public interface VerifiedPackageRepository extends PagingAndSortingRepository<VerifiedPackageEntity, Long> {

}
