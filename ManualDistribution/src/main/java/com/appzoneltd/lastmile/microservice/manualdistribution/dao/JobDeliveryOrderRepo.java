package com.appzoneltd.lastmile.microservice.manualdistribution.dao;

import com.appzoneltd.lastmile.microservice.manualdistribution.entity.DeliveryJobOrder;
import com.appzoneltd.lastmile.microservice.manualdistribution.entity.JobOrder;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;


/**
 * Created by hashish on 3/20/2017.
 */
public interface JobDeliveryOrderRepo extends CrudRepository<DeliveryJobOrder, Long> {
    @Query(value = "SELECT jobDeliveryOrder FROM DeliveryJobOrder jobDeliveryOrder WHERE jobDeliveryOrder.jobDate=:date AND jobDeliveryOrder.jobStatus<>'CANCELED' And jobDeliveryOrder.jobStatus<>'ACTION_NEEDED'" )
    List<JobOrder> jobDeliveryOrderAllIForToday(@Param("date") Date jobDate);
}
