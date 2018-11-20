package com.appzoneltd.lastmile.microservices.syncengine.stats.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservices.syncengine.stats.entity.CustomerAgeSummeryEntity;

/**
 * Repository : CustomerAgeSummery.
 */
public interface CustomerAgeSummeryRepository extends PagingAndSortingRepository<CustomerAgeSummeryEntity, Long> {

}
