package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao;

import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.RequestCancelationEntity;

/**
 * Repository : CancelationReason.
 */
public interface RequestCancelationRepository extends CrudRepository<RequestCancelationEntity, Long> {

}
