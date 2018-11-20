package com.appzoneltd.lastmile.microservices.stats.lastmile.repo;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservices.stats.lastmile.entity.PackageEntity;

/**
 * Repository : Package.
 */
public interface PackageRepository extends PagingAndSortingRepository<PackageEntity, Long> {

}
