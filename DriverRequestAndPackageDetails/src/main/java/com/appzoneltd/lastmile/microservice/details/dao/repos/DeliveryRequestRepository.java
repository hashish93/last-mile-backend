package com.appzoneltd.lastmile.microservice.details.dao.repos;


import com.appzoneltd.lastmile.microservice.details.dao.entity.DeliveryRequestEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : DeliveryRequest.
 */
public interface DeliveryRequestRepository extends PagingAndSortingRepository<DeliveryRequestEntity, Long> {

}
