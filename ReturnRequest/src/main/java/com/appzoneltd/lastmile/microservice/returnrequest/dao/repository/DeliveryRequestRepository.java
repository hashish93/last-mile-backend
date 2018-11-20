package com.appzoneltd.lastmile.microservice.returnrequest.dao.repository;

import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.DeliveryRequestEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : DeliveryRequest.
 */
public interface DeliveryRequestRepository extends PagingAndSortingRepository<DeliveryRequestEntity, Long> {

}
