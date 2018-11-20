package com.appzoneltd.lastmile.microservices.syncengine.lastmile.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservices.syncengine.lastmile.entity.PickupRequestTypeEntity;

/**
 * Repository : PickupRequestType.
 */
public interface PickupRequestTypeRepository extends PagingAndSortingRepository<PickupRequestTypeEntity, Long> {

}
