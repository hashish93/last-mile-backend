package com.appzoneltd.lastmile.microservices.syncengine.stats.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.RequestTypeSummeryEntity;

/**
 * Repository : RequestTypeSummery.
 */
public interface RequestTypeSummeryRepository extends PagingAndSortingRepository<RequestTypeSummeryEntity, Long> {

}
