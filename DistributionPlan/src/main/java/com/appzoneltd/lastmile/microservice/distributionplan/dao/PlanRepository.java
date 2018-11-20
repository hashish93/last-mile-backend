package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface PlanRepository extends PagingAndSortingRepository<PlanEntity, Long> {

    @Query(value = "SELECT p FROM PlanEntity p ORDER BY p.created DESC ")
    List<PlanEntity> findLatest();
    
    
    @Query(value = "SELECT p FROM PlanEntity p WHERE p.buildingId IN (:hubs) ORDER BY p.created DESC ")
    List<PlanEntity> findLatestToHub(@Param("hubs") List<Long> hubs);
    
    @Query(value = "SELECT p.id FROM PlanEntity p where Date(p.created)=Date(now())")
    Long findTodayPlan();
    
    @Query(value = "SELECT p.id FROM PlanEntity p where Date(p.created)=Date(now()) AND p.buildingId =:hub ")
    Long findTodayPlanToHub(@Param("hub") Long hub);
    
    
    @Query(value = "SELECT p FROM PlanEntity p WHERE p.buildingId IN (:hubs) ORDER BY p.created DESC ")
    List<PlanEntity> findAllPlanByHub(@Param("hubs") List<Long> hubs);
    
    
}
