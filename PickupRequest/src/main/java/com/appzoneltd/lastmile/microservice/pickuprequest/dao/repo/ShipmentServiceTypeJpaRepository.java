package com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo;


import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.ShipmentServiceTypeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : ShipmentServiceType.
 */
public interface ShipmentServiceTypeJpaRepository extends PagingAndSortingRepository<ShipmentServiceTypeEntity, Long> {

}
