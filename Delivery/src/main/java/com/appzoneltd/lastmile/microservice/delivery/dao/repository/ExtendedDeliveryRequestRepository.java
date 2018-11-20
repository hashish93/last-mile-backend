package com.appzoneltd.lastmile.microservice.delivery.dao.repository;

import com.appzoneltd.lastmile.microservice.delivery.dao.entity.CustomerEntity;
import com.appzoneltd.lastmile.microservice.delivery.dao.entity.DeliveryRequestEntity;
import com.appzoneltd.lastmile.microservice.delivery.dao.entity.ExtendedDeliveryRequestEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

/**
 * Repository : DeliveryRequest.
 */
public interface ExtendedDeliveryRequestRepository extends PagingAndSortingRepository<ExtendedDeliveryRequestEntity, Long> {

//    List<DeliveryRequestEntity> findByRecipient(CustomerEntity recipient);


    @Query(value = "SELECT distinct d.* "
    		+ ", in_plan.active_vehicle_id "
    		+ "FROM lastmile_request.delivery_request AS d "
    		+ "left outer join (select plan_order.order_id as order_id , plan_order.active_vehicle_id  from lastmile_plan.plan_order plan_order , lastmile_plan.plan "
    		+ "where plan_order.plan_id = lastmile_plan.plan.id and DATE(lastmile_plan.plan.created) = CURRENT_DATE ) in_plan on d.delivery_request_id = in_plan.order_id "
    		+ ", lastmile_request.request_history AS history , lastmile_core.package AS p ,lastmile_core.package_type pt "
    		+ "WHERE d.delivery_request_id = history.request_id AND history.package_id=p.package_id "
    		+ "AND p.package_type_id = pt.package_type_id AND (CAST(d.delivery_request_id AS varchar(100)) LIKE :requestId) "
    		+ "AND (LOWER(d.recipientformatedaddress) LIKE :recipientAddress) AND (d.deliverydate BETWEEN  :deliveryDateFrom   AND  :deliveryDateTo)  "
    		+ "AND (d.time_from LIKE :timeFrom) AND (d.time_to LIKE :timeTo) AND (pt.packagetype LIKE :packageType)  "
    		+ "AND (:requestStatus ='' OR d.request_status = :requestStatus) AND (d.request_status <> 'ENDED') "
    		+ "AND (d.hub_id IN (:hubs)) /*#pageable}*/ ", 
    		nativeQuery = true)
    List<ExtendedDeliveryRequestEntity> getDeliveryRequestsPagedFiltered(@Param("requestId") String requestId,
																		 @Param("recipientAddress") String recipientAddress, @Param("deliveryDateFrom") Date deliveryDateFrom,
																		 @Param("deliveryDateTo") Date deliveryDateTo, @Param("timeFrom") String timeFrom, @Param("timeTo") String timeTo,
																		 @Param("packageType") String packageType, @Param("requestStatus") String requestStatus,
																		 @Param("hubs") List<Long> hubs ,
																		 Pageable pageable);

    @Query(value = "SELECT distinct d.* "
    		+ ", in_plan.active_vehicle_id "
    		+ "FROM lastmile_request.delivery_request AS d "
    		+ "left outer join (select plan_order.order_id as order_id , plan_order.active_vehicle_id  from lastmile_plan.plan_order plan_order , lastmile_plan.plan " 
    		+ "where plan_order.plan_id = lastmile_plan.plan.id and DATE(lastmile_plan.plan.created) = CURRENT_DATE ) in_plan on d.delivery_request_id = in_plan.order_id "
    		+ ", lastmile_request.request_history AS history , lastmile_core.package AS p ,lastmile_core.package_type pt "
    		+ "WHERE d.delivery_request_id = history.request_id AND history.package_id=p.package_id AND p.package_type_id = pt.package_type_id "
    		+ "AND (CAST(d.delivery_request_id AS varchar(100)) LIKE :requestId) AND (LOWER(d.recipientformatedaddress) LIKE :recipientAddress) "
    		+ "AND (d.deliverydate BETWEEN  :deliveryDateFrom   AND  :deliveryDateTo)  AND (d.time_from LIKE :timeFrom) "
    		+ "AND (d.time_to LIKE :timeTo) AND (pt.packagetype LIKE :packageType)  "
    		+ "AND (:requestStatus ='' OR d.request_status = :requestStatus) "
    		+ "AND (d.request_status <> 'ENDED') "
    		+ "AND (d.hub_id IN (:hubs)) "
    		+ "order by d.created  ", nativeQuery = true)
    List<ExtendedDeliveryRequestEntity> getDeliveryRequestsFiltered(@Param("requestId") String requestId,
    		@Param("recipientAddress") String recipientAddress, @Param("deliveryDateFrom") Date deliveryDateFrom, 
    		@Param("deliveryDateTo") Date deliveryDateTo, @Param("timeFrom") String timeFrom, @Param("timeTo") String timeTo, 
    		@Param("packageType") String packageType, @Param("requestStatus") String requestStatus
    		,@Param("hubs") List<Long> hubs);
}
