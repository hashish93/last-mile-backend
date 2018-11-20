package com.appzoneltd.lastmile.microservice.offloading.dao.repository;


import com.appzoneltd.lastmile.microservice.offloading.dao.entity.PackageEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Package.
 */
public interface PackageJpaRepository extends PagingAndSortingRepository<PackageEntity, Long> {

}
