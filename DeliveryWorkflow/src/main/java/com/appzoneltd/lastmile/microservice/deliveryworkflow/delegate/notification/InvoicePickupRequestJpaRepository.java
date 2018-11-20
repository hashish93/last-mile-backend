package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.notification;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : PickupRequest.
 */
public interface InvoicePickupRequestJpaRepository extends PagingAndSortingRepository<InvoicePickupRequestEntity, Long> {
	
	InvoicePickupRequestEntity findByPickupRequestIdAndPickupRequestTypeAndRequestStatus(Long pickupRequestId, PickupRequestTypeEntity pickupRequestTypeEntity, String requestStatus);

    List<InvoicePickupRequestEntity> findByPickupRequestTypeAndRequestStatus(PickupRequestTypeEntity pickupRequestTypeEntity, String requestStatus);
}