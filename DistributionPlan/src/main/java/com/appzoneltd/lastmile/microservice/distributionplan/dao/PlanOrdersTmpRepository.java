package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Created by hashish on 4/9/2017.
 */
public interface PlanOrdersTmpRepository extends CrudRepository<PlanOrderTmpEntity, Long> {

//	@Query(value = "SELECT o FROM PlanOrderEntity o WHERE o.plan.id=:id ORDER BY o.id ASC ")
//	List<PlanOrderEntity> findByPlanIdGroupByActiveVehicle(@Param("id") long planId);

    @Query(value = "SELECT * FROM lastmile_plan.plan_order o  INNER JOIN  lastmile_plan.plan p  ON o.plan_id=p.id  WHERE o.plan_id=:id ORDER BY o.id ASC" , nativeQuery=true)
    List<PlanOrderTmpEntity> findByPlanIdGroupByActiveVehicle(@Param("id") long planId);

    @Query(value = "SELECT o FROM PlanOrderTmpEntity o WHERE o.activeVehicle.id IN (:ids) ")
    List<PlanOrderTmpEntity> findByActiveVehicleIdsGroupByActiveVehicles(@Param("ids") List<Long> activeVehilcesIds);

    @Query(value = "SELECT DISTINCT o.activeVehicle FROM PlanOrderTmpEntity o WHERE o.plan.id=:id")
    List<ActiveVehicleEntity> getAllActiveVehicleInfo(@Param("id") Long Id);

    @Query(value = "SELECT DISTINCT o FROM PlanOrderTmpEntity o WHERE o.activeVehicle.id=:id AND Date(o.created) = Date(now())")
    List<PlanOrderTmpEntity> getActiveVehicleInfo(@Param("id") Long Id);

    @Query(value = "SELECT p FROM PlanOrderTmpEntity p WHERE p.planId=:planId")
    List<PlanOrderTmpEntity> getPlanDetailsTmp(@Param("planId") Long planId);


    @Query(value = "SELECT  o.orderId FROM PlanOrderTmpEntity o WHERE o.activeVehicle.id=:activeVehicleId AND o.plan.id=:planId")
    List<Long> getOrderAssignedToActiveVehicle(@Param("activeVehicleId") Long activeVehicleId ,@Param("planId") Long planId  );



}
