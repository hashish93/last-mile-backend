package com.appzoneltd.lastmile.microservice.details.service;

import com.appzoneltd.lastmile.microservice.details.dao.entity.CarsBrandsEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.CarsModelsEntity;
import com.appzoneltd.lastmile.microservice.details.dao.repos.CarsBrandsJpaRepository;
import com.appzoneltd.lastmile.microservice.details.dao.repos.CarsModelsJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by alaa.nabil on 7/16/2017.
 */
@Service
public class CarsLookupService {
    private final CarsBrandsJpaRepository carsBrandsRepository;
    private final CarsModelsJpaRepository carsModelsRepository;

    @Autowired
    public CarsLookupService(CarsBrandsJpaRepository carsBrandsRepository, CarsModelsJpaRepository carsModelsRepository) {
        this.carsBrandsRepository = carsBrandsRepository;
        this.carsModelsRepository = carsModelsRepository;
    }

    public List<CarsBrandsEntity> getCarsBrands() {
        return (List<CarsBrandsEntity>) carsBrandsRepository.findAll();
    }

    public List<CarsModelsEntity> getCarsModelsByBrand(Long brandId) {
        return carsModelsRepository.findAllByBrandId(brandId);
    }
}
