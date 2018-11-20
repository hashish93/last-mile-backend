package com.appzoneltd.lastmile.microservice.lookup.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.lookup.entity.PackageLabelEntity;

/**
 * Repository : PackageLabel.
 */
public interface PackageLabelRepository extends PagingAndSortingRepository<PackageLabelEntity, Long> {

}
