package com.appzoneltd.lastmile.microservice.details.dao.repos;

import com.appzoneltd.lastmile.microservice.details.dao.entity.PlanEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository : Plan.
 */
public interface PlanJpaRepository extends PagingAndSortingRepository<PlanEntity, Long> {

    @Query("SELECT p FROM PlanEntity p WHERE p.buildingId=:buildingId ORDER BY p.created DESC ")
    List<PlanEntity> findAllPlansByCreatedDesc(@Param("buildingId") Long buildingId);


}
