package com.appzoneltd.lastmile.microservice.details.dao.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.details.dao.entity.PackageLabelEntity;

/**
 * Repository : PackageLabel.
 */
public interface PackageLabelJpaRepository extends PagingAndSortingRepository<PackageLabelEntity, Long> {

}
