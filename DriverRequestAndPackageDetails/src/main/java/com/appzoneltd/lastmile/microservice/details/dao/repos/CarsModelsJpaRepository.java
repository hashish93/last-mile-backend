package com.appzoneltd.lastmile.microservice.details.dao.repos;

import com.appzoneltd.lastmile.microservice.details.dao.entity.CarsModelsEntity;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


/**
 * Repository : CarsModels.
 */
public interface CarsModelsJpaRepository extends PagingAndSortingRepository<CarsModelsEntity, Long> {
    List<CarsModelsEntity> findAllByBrandId(Long brandId);
}
