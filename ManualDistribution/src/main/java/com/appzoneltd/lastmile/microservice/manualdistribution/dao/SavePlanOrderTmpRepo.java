package com.appzoneltd.lastmile.microservice.manualdistribution.dao;

import com.appzoneltd.lastmile.microservice.manualdistribution.entity.SavePlanOrderEntityWithStatus;
import org.springframework.data.couchbase.core.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SavePlanOrderTmpRepo extends CrudRepository<SavePlanOrderEntityWithStatus, Long> {

    @Query("SELECT p FROM SavePlanOrderEntityWithStatus p WHERE p.planId=:planId")
    List<SavePlanOrderEntityWithStatus> getPlanDetailsByPlanId(@Param("planId") Long planId);
}
