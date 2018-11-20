package com.appzoneltd.lastmile.microservice.lookup.controller;

import com.appzoneltd.lastmile.microservice.lookup.dto.PackageLabelDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.PackageTypeDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.PickupTimeDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.VehicleTypeDto;
import com.appzoneltd.lastmile.microservice.lookup.entity.BuildingTypeEntity;
import com.appzoneltd.lastmile.microservice.lookup.entity.DrivingLicenseTypeEntity;
import com.appzoneltd.lastmile.microservice.lookup.service.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
public class LookupController {

	@Autowired
	private LookupService lookupService;
	
	
	
	@RequestMapping(value = "/driverlicensetype/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> LicenseTypeFindAllAPI() {
		List<DrivingLicenseTypeEntity> drivingLicenseTypeEntities = lookupService.getDrivingLicenseTypes();
		if(drivingLicenseTypeEntities !=null){
			return new ResponseEntity<>(drivingLicenseTypeEntities, HttpStatus.OK);
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/buildingtype/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> buildingTypeList() {
		List<BuildingTypeEntity> buildingTypeEntities = lookupService.getBuildingTypes();
		if(buildingTypeEntities !=null){
			return new ResponseEntity<>(buildingTypeEntities, HttpStatus.OK);
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/vehicletype/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> vehicleTypeFindAllAPI() {
		List<VehicleTypeDto> vehicleTypes = lookupService.getVehicleTypes(LocaleContextHolder.getLocale());
		return new ResponseEntity<>(vehicleTypes, HttpStatus.OK);
	}
	
	
	@RequestMapping(value = "/packagetype/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> packagetype() {
		List<PackageTypeDto> packageTypes = lookupService.getPackageTypes();
		return new ResponseEntity<>(packageTypes, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/packagelabel/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> packageLabels() {
		List<PackageLabelDto> packageLabels= lookupService.getPackageLabels();
		return new ResponseEntity<>(packageLabels, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/pickuptime/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> pickupTimes() {
		List<PickupTimeDto> pickupTimes= lookupService.getPickupTimes(LocaleContextHolder.getLocale());
		return new ResponseEntity<>(pickupTimes, HttpStatus.OK);
	}
	
}
