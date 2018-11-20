package com.appzoneltd.lastmile.microservice.packge.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.packge.entity.PackageImagesEntity;

/**
 * Repository : PackageImages.
 */
public interface PackageImagesRepository extends PagingAndSortingRepository<PackageImagesEntity, Long> {

}
