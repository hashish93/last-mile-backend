package com.appzoneltd.lastmile.microservice.customer.dao.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.DeliveryRequestEntity;

/**
 * Repository : DeliveryRequest.
 */
public interface DeliveryRequestRepository extends PagingAndSortingRepository<DeliveryRequestEntity, Long> {

	List<DeliveryRequestEntity> findByRequesterId(Long requesterId);
}
