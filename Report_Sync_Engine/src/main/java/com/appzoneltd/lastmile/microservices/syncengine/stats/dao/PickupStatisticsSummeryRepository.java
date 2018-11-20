package com.appzoneltd.lastmile.microservices.syncengine.stats.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.PickupStatisticsSummeryEntity;

/**
 * Repository : PickupStatisticsSummery.
 */
public interface PickupStatisticsSummeryRepository extends PagingAndSortingRepository<PickupStatisticsSummeryEntity, Long> {

}
