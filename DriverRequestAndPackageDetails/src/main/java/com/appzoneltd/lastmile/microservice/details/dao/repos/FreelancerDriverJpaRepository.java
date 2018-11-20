package com.appzoneltd.lastmile.microservice.details.dao.repos;

import com.appzoneltd.lastmile.microservice.details.dao.entity.FreelancerDriverEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Repository : FreelancerDriver.
 */
public interface FreelancerDriverJpaRepository extends PagingAndSortingRepository<FreelancerDriverEntity, Long> {

}
