package com.appzoneltd.lastmile.microservice.details.dao.repos;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.details.dao.entity.PickupRequestTypeEntity;

/**
 * Repository : PickupRequestType.
 */
public interface PickupRequestTypeJpaRepository extends PagingAndSortingRepository<PickupRequestTypeEntity, Long> {

}
