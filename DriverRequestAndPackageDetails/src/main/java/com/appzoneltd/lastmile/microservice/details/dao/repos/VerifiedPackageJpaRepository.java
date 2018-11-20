package com.appzoneltd.lastmile.microservice.details.dao.repos;

import org.springframework.data.repository.PagingAndSortingRepository;
import com.appzoneltd.lastmile.microservice.details.dao.entity.VerifiedPackageEntity;

/**
 * Repository : VerifiedPackage.
 */
public interface VerifiedPackageJpaRepository extends PagingAndSortingRepository<VerifiedPackageEntity, Long> {

}
