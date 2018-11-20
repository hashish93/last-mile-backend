package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import com.appzoneltd.lastmile.microservice.distributionplan.dao.PlanEntityTmp;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Created by hashish on 4/9/2017.
 */
public interface PlanTmpRepository extends PagingAndSortingRepository<PlanEntityTmp, Long> {

    @Query(value = "SELECT p FROM PlanEntityTmp p ORDER BY p.created DESC ")
    List<PlanEntityTmp> findLatest();

    @Query(value = "SELECT p.id FROM PlanEntityTmp p where Date(p.created)=Date(now())")
    Long findTodayPlan();
}
