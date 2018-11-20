package com.appzoneltd.lastmile.microservice.offloading.dao.repository;

import com.appzoneltd.lastmile.microservice.offloading.dao.entity.VehicleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : Vehicle.
 */
public interface VehicleJpaRepository extends PagingAndSortingRepository<VehicleEntity, Long> {

}
