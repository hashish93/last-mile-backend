package com.appzoneltd.lastmile.microservice.customer.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.ReturnRequestEntity;

/**
 * Repository : ReturnRequest.
 */
public interface ReturnRequestRepository extends PagingAndSortingRepository<ReturnRequestEntity, Long> {

	List<ReturnRequestEntity> findByRequesterId(Long requesterId);
}
