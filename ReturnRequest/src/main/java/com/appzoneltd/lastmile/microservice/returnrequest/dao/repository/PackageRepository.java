package com.appzoneltd.lastmile.microservice.returnrequest.dao.repository;

import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.PackageEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Package.
 */
public interface PackageRepository extends PagingAndSortingRepository<PackageEntity, Long> {

}
