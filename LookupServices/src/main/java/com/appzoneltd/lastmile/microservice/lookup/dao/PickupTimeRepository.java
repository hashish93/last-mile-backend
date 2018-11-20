package com.appzoneltd.lastmile.microservice.lookup.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.lookup.entity.PickupTimeEntity;

/**
 * Repository : PickupTime.
 */
public interface PickupTimeRepository extends PagingAndSortingRepository<PickupTimeEntity, Long> {

}
