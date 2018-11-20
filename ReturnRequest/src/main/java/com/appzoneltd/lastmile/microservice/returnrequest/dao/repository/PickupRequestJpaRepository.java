package com.appzoneltd.lastmile.microservice.returnrequest.dao.repository;

import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.PickupRequestEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : PickupRequest.
 */
public interface PickupRequestJpaRepository extends PagingAndSortingRepository<PickupRequestEntity, Long> {

}
