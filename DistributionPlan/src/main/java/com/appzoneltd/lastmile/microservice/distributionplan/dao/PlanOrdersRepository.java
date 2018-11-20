package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanOrdersRepository extends PagingAndSortingRepository<PlanOrderEntity, Long> {

//	@Query(value = "SELECT o FROM PlanOrderEntity o WHERE o.plan.id=:id ORDER BY o.id ASC ")
//	List<PlanOrderEntity> findByPlanIdGroupByActiveVehicle(@Param("id") long planId);
	
	@Query(value = "SELECT * FROM lastmile_plan.plan_order o  INNER JOIN  lastmile_plan.plan p  ON o.plan_id=p.id  WHERE o.plan_id=:id ORDER BY o.id ASC" , nativeQuery=true)
	List<PlanOrderEntity> findByPlanIdGroupByActiveVehicle(@Param("id") long planId);
	

	@Query(value = "SELECT o FROM PlanOrderEntity o WHERE o.activeVehicle.id IN (:ids) ")
	List<PlanOrderEntity> findByActiveVehicleIdsGroupByActiveVehicles(@Param("ids") List<Long> activeVehicleIds);
	
	
	@Query(value = "SELECT o FROM PlanOrderEntity o inner join o.plan p   WHERE o.activeVehicle.id IN (:ids) AND p.buildingId IN (:hubs) ")
	List<PlanOrderEntity> findByActiveVehicleIdsGroupByActiveVehiclesRelatedToHub(@Param("ids") List<Long> activeVehicleIds , @Param ("hubs") List<Long> hubs);

	@Query(value = "SELECT DISTINCT o.activeVehicle FROM PlanOrderEntity o WHERE o.plan.id=:id")
	List<ActiveVehicleEntity> getAllActiveVehicleInfo(@Param("id") Long Id);
	
	
	@Query(value = "SELECT DISTINCT o.activeVehicle FROM PlanOrderEntity o inner join o.plan p WHERE p.id=:id AND p.buildingId IN (:hubs) ")
	List<ActiveVehicleEntity> getAllActiveVehicleInfoRelatedToHub(@Param("id") Long Id , @Param ("hubs") List<Long> hubs ) ;
	
	
	@Query(value = "SELECT DISTINCT o FROM PlanOrderEntity o WHERE o.activeVehicle.id=:id AND Date(o.created) = Date(now())")
	List<PlanOrderEntity> getActiveVehicleInfo(@Param("id") Long Id);
	
	@Query(value = "SELECT p FROM PlanOrderEntity p WHERE p.planId=:planId")
	List<PlanOrderEntity> getPlanDetails(@Param("planId") Long planId);
	
	@Query(value = "SELECT po FROM PlanOrderEntity po inner join po.plan p   WHERE po.planId=:planId AND p.buildingId IN (:hubs) ")
	List<PlanOrderEntity> getPlanDetailsToHub(@Param("planId") Long planId , @Param ("hubs") List<Long> hubs);
	
	
	@Query(value = "SELECT  o.orderId FROM PlanOrderEntity o inner join o.plan p WHERE o.activeVehicle.id=:activeVehicleId AND p.id=:planId")
	List<Long> getOrderAssignedToActiveVehicle(@Param("activeVehicleId") Long activeVehicleId ,@Param("planId") Long planId  );
	
	

}
