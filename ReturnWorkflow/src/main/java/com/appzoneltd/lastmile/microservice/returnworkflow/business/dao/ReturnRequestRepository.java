package com.appzoneltd.lastmile.microservice.returnworkflow.business.dao;


import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.entity.ReturnRequestEntity;

/**
 * Repository : ReturnRequest.
 */
public interface ReturnRequestRepository extends PagingAndSortingRepository<ReturnRequestEntity, Long> {

}
