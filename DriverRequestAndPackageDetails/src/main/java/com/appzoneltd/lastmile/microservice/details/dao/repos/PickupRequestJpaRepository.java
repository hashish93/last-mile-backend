package com.appzoneltd.lastmile.microservice.details.dao.repos;

import com.appzoneltd.lastmile.microservice.details.dao.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.PickupRequestTypeEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Repository : PickupRequest.
 */
public interface PickupRequestJpaRepository extends PagingAndSortingRepository<PickupRequestEntity, Long> {
    PickupRequestEntity findByPickupRequestIdAndPickupRequestTypeAndRequestStatus(Long pickupRequestId, PickupRequestTypeEntity pickupRequestTypeEntity, String requestStatus);

    List<PickupRequestEntity> findByPickupRequestTypeAndRequestStatus(PickupRequestTypeEntity pickupRequestTypeEntity, String requestStatus);
}
