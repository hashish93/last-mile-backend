package com.appzoneltd.lastmile.microservice.delivery.dao.repository;

import com.appzoneltd.lastmile.microservice.delivery.dao.entity.PackageEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Package.
 */
public interface PackageRepository extends PagingAndSortingRepository<PackageEntity, Long> {

}
