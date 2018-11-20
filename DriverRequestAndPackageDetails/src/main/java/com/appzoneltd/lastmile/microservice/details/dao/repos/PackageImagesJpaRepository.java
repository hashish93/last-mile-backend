package com.appzoneltd.lastmile.microservice.details.dao.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.details.dao.entity.PackageImagesEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.PackageImagesEntityKey;

/**
 * Repository : PackageImages.
 */
public interface PackageImagesJpaRepository extends PagingAndSortingRepository<PackageImagesEntity, PackageImagesEntityKey> {

}
