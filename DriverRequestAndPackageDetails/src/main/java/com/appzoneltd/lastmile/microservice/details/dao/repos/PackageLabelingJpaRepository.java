package com.appzoneltd.lastmile.microservice.details.dao.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.details.dao.entity.PackageLabelingEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.PackageLabelingEntityKey;

/**
 * Repository : PackageLabeling.
 */
public interface PackageLabelingJpaRepository extends PagingAndSortingRepository<PackageLabelingEntity, PackageLabelingEntityKey> {

}
