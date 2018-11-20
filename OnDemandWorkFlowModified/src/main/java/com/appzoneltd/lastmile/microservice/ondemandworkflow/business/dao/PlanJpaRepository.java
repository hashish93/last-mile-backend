package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.PlanEntity;

/**
 * Repository : Plan.
 */
public interface PlanJpaRepository extends PagingAndSortingRepository<PlanEntity, Long> {

	@Query(value = "SELECT p FROM PlanEntity p where Date(p.created)=Date(now()) AND p.buildingId =:hub ")
	PlanEntity findTodayPlanToHub(@Param("hub") Long hub);
}
