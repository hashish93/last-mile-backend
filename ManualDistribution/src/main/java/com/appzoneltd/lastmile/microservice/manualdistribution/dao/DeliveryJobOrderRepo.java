package com.appzoneltd.lastmile.microservice.manualdistribution.dao;


import com.appzoneltd.lastmile.microservice.manualdistribution.entity.DeliveryJobOrder;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.JobOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface DeliveryJobOrderRepo extends CrudRepository<DeliveryJobOrder, Long> {

    //TODO this should be re-implemented to map both pickups and deliveries
    @Query(value = "SELECT j FROM DeliveryJobOrder j WHERE j.jobOrderId=:id")
    JobOrder jobOrderInfo(@Param("id") Long JobOrderId);

    //TODO this should be re-implemented to map both pickups and deliveries
    @Query(value = "SELECT jobOrder FROM DeliveryJobOrder jobOrder WHERE jobOrder.jobOrderId IN (:ids) ORDER BY jobOrder.timeFrom ")
    List<JobOrder> jobOrderAllInfo(@Param("ids") List<Long> jobOrderId);


    @Query(value = "SELECT jobOrder FROM DeliveryJobOrder jobOrder WHERE jobOrder.jobDate=:date AND jobOrder.hubId=:hubId AND ( jobOrder.jobStatus='SCHEDULED_FOR_DELIVERY'  OR jobOrder.jobStatus='RESCHEDULED_FOR_DELIVERY' ) ")
    List<JobOrder> jobOrderAllIForToday(@Param("date") Date jobDate, @Param("hubId") Long hubId);
}
