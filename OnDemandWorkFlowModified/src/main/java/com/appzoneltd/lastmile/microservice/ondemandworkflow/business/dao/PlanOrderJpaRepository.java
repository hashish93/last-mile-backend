package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PlanOrderEntity;

/**
 * Repository : PlanOrder.
 */
public interface PlanOrderJpaRepository extends PagingAndSortingRepository<PlanOrderEntity, Long> {
	
	@Query(value = "SELECT DISTINCT pl FROM PlanOrderEntity pl inner join pl.plan p where pl.orderId = :requestId AND p.id=:planId")
	List<PlanOrderEntity> findPlanOrderByRequestIdAndPlanId(@Param("planId") Long planId , @Param("requestId") Long requestId);
	
	@Query("SELECT p FROM PlanOrderEntity p WHERE p.orderType='PICKUP' AND p.orderId=:id AND Date(p.created) = Date(now())")
	PlanOrderEntity findByPickupId(@Param("id") Long deliveryId);

	
}
