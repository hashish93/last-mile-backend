package com.appzoneltd.lastmile.microservice.details.dao.repos;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.details.dao.entity.ActiveVehicleEntity;

/**
 * Repository : ActiveVehicle.
 */
@Repository
public interface ActiveVehicleJpaRepository extends PagingAndSortingRepository<ActiveVehicleEntity, Long> {

}
