package com.appzoneltd.lastmile.microservice.details.controller;

import com.appzoneltd.lastmile.microservice.details.dao.entity.CarsBrandsEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.CarsModelsEntity;
import com.appzoneltd.lastmile.microservice.details.service.CarsLookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by alaa.nabil on 7/16/2017.
 */
@RestController
public class CarsLookupController {
    private final CarsLookupService carsLookupService;

    @Autowired
    public CarsLookupController(CarsLookupService carsLookupService) {
        this.carsLookupService = carsLookupService;
    }

    @RequestMapping(value = ("/carBrands"), method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarsBrandsEntity>> getCarsBrands() {
        return new ResponseEntity<>(carsLookupService.getCarsBrands(), HttpStatus.OK);
    }

    @RequestMapping(value = ("/carModels"), method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CarsModelsEntity>> getCarModels(@RequestBody Map<String, Long> params) {
        return new ResponseEntity<>(carsLookupService.getCarsModelsByBrand(params.get("id")), HttpStatus.OK);
    }
}
