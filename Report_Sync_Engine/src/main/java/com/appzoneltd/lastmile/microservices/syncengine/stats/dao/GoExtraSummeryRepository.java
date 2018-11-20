package com.appzoneltd.lastmile.microservices.syncengine.stats.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.GoExtraSummeryEntity;

/**
 * Repository : GoExtraSummery.
 */
public interface GoExtraSummeryRepository extends PagingAndSortingRepository<GoExtraSummeryEntity, Long> {

}
