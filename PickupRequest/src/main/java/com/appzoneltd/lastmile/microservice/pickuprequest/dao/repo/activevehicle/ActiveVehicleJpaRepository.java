package com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.activevehicle;


import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.activevehicle.ActiveVehicleEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository : ActiveVehicle.
 */
@Repository
public interface ActiveVehicleJpaRepository extends PagingAndSortingRepository<ActiveVehicleEntity, Long> {

}
