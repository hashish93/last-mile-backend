package com.appzoneltd.lastmile.microservice.manualdistribution.entity;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository : ActiveVehicle.
 */
@Repository
public interface ActiveVehicleJpaRepository extends PagingAndSortingRepository<ActiveVehicleEntity, Long> {

}
