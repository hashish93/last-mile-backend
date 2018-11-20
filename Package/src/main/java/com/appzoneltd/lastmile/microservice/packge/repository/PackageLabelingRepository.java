package com.appzoneltd.lastmile.microservice.packge.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.packge.entity.PackageLabelingEntity;

/**
 * Repository : PackageLabeling.
 */
public interface PackageLabelingRepository extends PagingAndSortingRepository<PackageLabelingEntity, Long> {

}
