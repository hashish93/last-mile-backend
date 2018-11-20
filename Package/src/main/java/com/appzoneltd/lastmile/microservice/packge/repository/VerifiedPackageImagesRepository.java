package com.appzoneltd.lastmile.microservice.packge.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.packge.entity.VerifiedPackageImagesEntity;

/**
 * Repository : VerifiedPackageImages.
 */
public interface VerifiedPackageImagesRepository extends PagingAndSortingRepository<VerifiedPackageImagesEntity, Long> {

}
