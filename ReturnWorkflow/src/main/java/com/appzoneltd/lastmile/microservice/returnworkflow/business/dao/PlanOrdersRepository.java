package com.appzoneltd.lastmile.microservice.returnworkflow.business.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


import com.appzoneltd.lastmile.microservice.returnworkflow.business.entity.PlanOrderEntity;

public interface PlanOrdersRepository extends PagingAndSortingRepository<PlanOrderEntity, Long> {

//	@Query(value = "SELECT o FROM PlanOrderEntity o WHERE o.plan.id=:id ORDER BY o.id ASC ")
//	List<PlanOrderEntity> findByPlanIdGroupByActiveVehicle(@Param("id") long planId);
	
	@Query(value = "SELECT * FROM lastmile_plan.plan_order o  INNER JOIN  lastmile_plan.plan p  ON o.plan_id=p.id  WHERE o.plan_id=:id ORDER BY o.id ASC" , nativeQuery=true)
	List<PlanOrderEntity> findByPlanIdGroupByActiveVehicle(@Param("id") long planId);

	@Query(value = "SELECT o FROM PlanOrderEntity o WHERE o.activeVehicle.id IN (:ids) ")
	List<PlanOrderEntity> findByActiveVehicleIdsGroupByActiveVehicles(@Param("ids") List<Long> activeVehilcesIds);

	@Query(value = "SELECT DISTINCT o FROM PlanOrderEntity o WHERE o.activeVehicle.id=:id AND Date(o.created) = Date(now())")
	List<PlanOrderEntity> getActiveVehicleInfo(@Param("id") Long Id);
	
	@Query(value = "SELECT p FROM PlanOrderEntity p WHERE p.planId=:planId")
	List<PlanOrderEntity> getPlanDetails(@Param("planId") Long planId);
	
	
	@Query(value = "SELECT  o.orderId FROM PlanOrderEntity o WHERE o.activeVehicle.id=:activeVehicleId AND o.plan.id=:planId")
	List<Long> getOrderAssignedToActiveVehicle(@Param("activeVehicleId") Long activeVehicleId ,@Param("planId") Long planId  );

	@Query("SELECT p FROM PlanOrderEntity p WHERE p.orderType='RETURN' AND p.orderId=:id AND Date(p.created) = Date(now())")
	PlanOrderEntity findByReturnId(@Param("id") Long returnId);
	
	

}
