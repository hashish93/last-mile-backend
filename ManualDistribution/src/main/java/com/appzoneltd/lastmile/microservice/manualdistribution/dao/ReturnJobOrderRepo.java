package com.appzoneltd.lastmile.microservice.manualdistribution.dao;


import com.appzoneltd.lastmile.microservice.manualdistribution.entity.JobOrder;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.ReturnJobOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ReturnJobOrderRepo extends CrudRepository<ReturnJobOrder, Long> {

    //TODO this should be re-implemented to map both pickups and deliveries
    @Query(value = "SELECT j FROM ReturnJobOrder j WHERE j.jobOrderId=:id")
    JobOrder jobOrderInfo(@Param("id") Long JobOrderId);

    //TODO this should be re-implemented to map both pickups and deliveries
    @Query(value = "SELECT jobOrder FROM ReturnJobOrder jobOrder WHERE jobOrder.jobOrderId IN (:ids) ORDER BY jobOrder.timeFrom ")
    List<JobOrder> jobOrderAllInfo(@Param("ids") List<Long> jobOrderId);


    @Query(value = "SELECT jobOrder FROM ReturnJobOrder jobOrder WHERE jobOrder.jobDate=:date AND jobOrder.hubId=:hubId AND (jobOrder.jobStatus='SCHEDULED_FOR_RETURN'  OR jobOrder.jobStatus='RESCHEDULED_FOR_RETURN')")
    List<JobOrder> jobOrderAllIForToday(@Param("date") Date jobDate, @Param("hubId") Long hubId);
}
