package com.appzoneltd.lastmile.microservice.packge.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.packge.entity.PackageLabelEntity;

/**
 * Repository : PackageLabel.
 */
public interface PackageLabelRepository extends PagingAndSortingRepository<PackageLabelEntity, Long> {

}
