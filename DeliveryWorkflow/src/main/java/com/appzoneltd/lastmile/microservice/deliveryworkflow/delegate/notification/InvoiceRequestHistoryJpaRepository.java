package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.notification;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface InvoiceRequestHistoryJpaRepository extends PagingAndSortingRepository<InvoiceRequestHistoryEntity, Long> {

    @Query("SELECT r FROM RequestHistoryEntity r WHERE r.requestId=:id ORDER BY r.created DESC")
    List<InvoiceRequestHistoryEntity> findByRequestId(@Param("id") Long requestId);
    
    
    @Query("SELECT r FROM RequestHistoryEntity r WHERE r.requestId=:id and r.requestType=:orderType ORDER BY r.created DESC")
    List<InvoiceRequestHistoryEntity> findByRequestIdOfGivenType(@Param("id") Long requestId , @Param("orderType") String orderType);
}