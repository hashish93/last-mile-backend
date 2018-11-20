package com.appzoneltd.lastmile.microservice.details.dao.repos;

import com.appzoneltd.lastmile.microservice.details.dao.entity.PlanOrderEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository : PlanOrder.
 */
public interface PlanOrderJpaRepository extends CrudRepository<PlanOrderEntity, Long> {

    PlanOrderEntity findByOrderId(Long orderId);

    @Query("SELECT p FROM PlanOrderEntity p WHERE p.driverResponse='ACCEPT' AND p.plan.id=:planId AND p.activeVehicleId=:activeVehicleId ORDER BY p.priority ASC ")
    List<PlanOrderEntity> findAllAcceptedOrders(@Param("activeVehicleId") Long activeVehicleId, @Param("planId") Long planId);

    @Query("SELECT p FROM PlanOrderEntity p WHERE p.plan.id=:planId AND p.activeVehicleId=:activeVehicleId ORDER BY p.priority ASC ")
    List<PlanOrderEntity> findAllLoadingOrders(@Param("activeVehicleId") Long activeVehicleId, @Param("planId") Long planId);

    @Query("SELECT p FROM PlanOrderEntity p WHERE p.driverResponse='ACCEPT' AND p.plan.id=:planId AND p.activeVehicleId=:activeVehicleId ORDER BY p.priority ASC ")
    List<PlanOrderEntity> findAllAcceptedJobOrders(@Param("activeVehicleId") Long activeVehicleId, @Param("planId") Long planId);

}
