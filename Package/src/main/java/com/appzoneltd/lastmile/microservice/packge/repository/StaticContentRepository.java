package com.appzoneltd.lastmile.microservice.packge.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.packge.entity.StaticContentEntity;

/**
 * Repository : PackageImages.
 */
public interface StaticContentRepository extends PagingAndSortingRepository<StaticContentEntity, Long> {

}
