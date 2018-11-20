package com.appzoneltd.lastmile.microservice.packge.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.packge.entity.PackageEntity;

/**
 * Repository : PackageDTO.
 */
public interface PackageRepository extends PagingAndSortingRepository<PackageEntity, Long> {

}
