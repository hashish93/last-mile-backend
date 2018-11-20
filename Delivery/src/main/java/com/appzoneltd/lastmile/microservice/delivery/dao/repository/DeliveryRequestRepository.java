package com.appzoneltd.lastmile.microservice.delivery.dao.repository;

import com.appzoneltd.lastmile.microservice.delivery.dao.entity.CustomerEntity;
import com.appzoneltd.lastmile.microservice.delivery.dao.entity.DeliveryRequestEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository : DeliveryRequest.
 */
public interface DeliveryRequestRepository extends PagingAndSortingRepository<DeliveryRequestEntity, Long> {

    List<DeliveryRequestEntity> findByRecipient(CustomerEntity recipient);

//    @Query(value = "SELECT distinct d.* FROM lastmile_request.delivery_request AS d, lastmile_request.request_history AS history , lastmile_core.package AS p ,lastmile_core.package_type pt WHERE d.delivery_request_id = history.request_id AND history.package_id=p.package_id AND p.package_type_id = pt.package_type_id AND (CAST(d.delivery_request_id AS varchar(100)) LIKE :requestId) AND (d.recipientformatedaddress LIKE :recipientAddress) AND (d.deliverydate BETWEEN  :deliveryDateFrom   AND  :deliveryDateTo)  AND (d.time_from LIKE :timeFrom) AND (d.time_to LIKE :timeTo) AND (pt.packagetype LIKE :packageType)  AND (:requestStatus ='' OR d.request_status = :requestStatus)  ",nativeQuery = true)
//    List<DeliveryRequestEntity>test(@Param("requestId") String requestId ,@Param("recipientAddress") String recipientAddress , @Param("deliveryDateFrom") Date deliveryDateFrom , @Param("deliveryDateTo") Date deliveryDateTo , @Param("timeFrom") String timeFrom , @Param("timeTo") String timeTo ,@Param("packageType") String packageType ,  @Param("requestStatus") String requestStatus);


    @Query(value = "SELECT d.* FROM lastmile_request.delivery_request AS d "
            + "WHERE d.request_status ='OUT_FOR_DELIVERY' AND d.delivery_request_id=:requestId"
            , nativeQuery = true)
    List<DeliveryRequestEntity> trackedDeliveryRequest(@Param("requestId") Long requestId);

    List<DeliveryRequestEntity> findAllByHubIdInAndRequestStatusIn(List<Long> hubId, List<String> requestStatus, org.springframework.data.domain.Pageable pageable);

    List<DeliveryRequestEntity> findAllByHubIdInAndRequestStatusIn(List<Long> hubId, List<String> requestStatus, Sort sort);

    long countAllByHubIdInAndRequestStatusIn(List<Long> hubId, List<String> requestStatus);

}
