package com.appzoneltd.lastmile.microservice.manualdistribution.dao;

import com.appzoneltd.lastmile.microservice.manualdistribution.entity.PlanEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlanRepo extends CrudRepository<PlanEntity, Long> {
    @Query(value = "SELECT p FROM PlanEntity p WHERE p.buildingId=:buildingId ORDER BY p.created DESC ")
    List<PlanEntity> findLatest(@Param("buildingId") Long buildingId);

}
