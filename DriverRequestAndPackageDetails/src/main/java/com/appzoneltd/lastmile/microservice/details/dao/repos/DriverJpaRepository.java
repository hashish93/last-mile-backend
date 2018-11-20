package com.appzoneltd.lastmile.microservice.details.dao.repos;

import com.appzoneltd.lastmile.microservice.details.dao.entity.DriverEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DriverJpaRepository extends PagingAndSortingRepository<DriverEntity, Long> {
}
