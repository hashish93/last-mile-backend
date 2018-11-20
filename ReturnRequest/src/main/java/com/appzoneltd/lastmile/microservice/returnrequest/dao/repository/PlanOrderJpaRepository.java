package com.appzoneltd.lastmile.microservice.returnrequest.dao.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.returnrequest.dao.entity.PlanOrderEntity;

/**
 * Repository : PlanOrder.
 */
public interface PlanOrderJpaRepository extends CrudRepository<PlanOrderEntity, Long> {

    PlanOrderEntity findByOrderId(Long orderId);
    
    List<PlanOrderEntity> findByOrderTypeAndCreatedBetween(String orderType,Date start, Date end);
    
    @Query("SELECT p FROM PlanOrderEntity p WHERE p.orderType='RETURN' AND p.orderId=:returnId AND Date(p.created) = Date(now())")
	PlanOrderEntity findByReturnId(@Param("returnId") Long returnId);

}
