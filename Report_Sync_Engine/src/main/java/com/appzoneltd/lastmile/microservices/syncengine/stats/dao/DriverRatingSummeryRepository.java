package com.appzoneltd.lastmile.microservices.syncengine.stats.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.DriverRatingSummeryEntity;

/**
 * Repository : DriverRatingSummery.
 */
public interface DriverRatingSummeryRepository extends PagingAndSortingRepository<DriverRatingSummeryEntity, Long> {

}
