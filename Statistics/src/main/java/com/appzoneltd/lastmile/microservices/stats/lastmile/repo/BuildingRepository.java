package com.appzoneltd.lastmile.microservices.stats.lastmile.repo;

import com.appzoneltd.lastmile.microservices.stats.lastmile.entity.BuildingEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Repository : Customer.
 */
public interface BuildingRepository extends PagingAndSortingRepository<BuildingEntity, Long> {
    List<BuildingEntity> findAllByBuildingIdIn(List<Long> hubId);
}
