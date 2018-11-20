package com.appzoneltd.lastmile.microservice.offloading.dao.repository;

import com.appzoneltd.lastmile.microservice.offloading.dao.entity.FreelancerDriverEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : FreelancerDriver.
 */
public interface FreelancerDriverJpaRepository extends PagingAndSortingRepository<FreelancerDriverEntity, Long> {

}
