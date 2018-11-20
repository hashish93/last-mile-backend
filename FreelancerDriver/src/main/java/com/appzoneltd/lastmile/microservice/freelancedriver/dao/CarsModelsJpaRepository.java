package com.appzoneltd.lastmile.microservice.freelancedriver.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.appzoneltd.lastmile.microservice.freelancedriver.model.CarsModelsEntity;

import java.util.List;


/**
 * Repository : CarsModels.
 */
public interface CarsModelsJpaRepository extends PagingAndSortingRepository<CarsModelsEntity, Long> {
    List<CarsModelsEntity> findAllByBrandId(Long brandId);
}
