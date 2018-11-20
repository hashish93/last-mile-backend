package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao;


import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.FreelancerDriverEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : FreelancerDriver.
 */
public interface FreelancerDriverJpaRepository extends PagingAndSortingRepository<FreelancerDriverEntity, Long> {

}
