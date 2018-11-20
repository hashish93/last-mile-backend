package com.appzoneltd.lastmile.microservice.details.dao.repos;

import com.appzoneltd.lastmile.microservice.details.dao.entity.VehicleTypeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : VehicleType.
 */
public interface VehicleTypeJpaRepository extends PagingAndSortingRepository<VehicleTypeEntity, Long> {

}
