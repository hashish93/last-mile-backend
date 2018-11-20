package com.appzoneltd.lastmile.microservices.devices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservices.devices.entity.BrandEntity;
import com.appzoneltd.lastmile.microservices.devices.services.BrandService;

@RestController
public class BrandController {

	@Autowired
	private BrandService brandService;
	
	
	
	@RequestMapping(value = "/findallbrand", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BrandEntity>> getAllBrands() {

		List<BrandEntity> brands = (List<BrandEntity>) brandService.findAllBrand();

		return new ResponseEntity<List<BrandEntity>>(brands, HttpStatus.OK);
	}

}
