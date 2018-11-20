package com.appzoneltd.lastmile.microservice.drivinglicensetype.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.drivinglicensetype.dao.DrivingLicenseType;
import com.appzoneltd.lastmile.microservice.drivinglicensetype.service.DrivingLicenseTypeService;

@RestController
public class DriverLicenseTypeController {

	private DrivingLicenseTypeService drivingLicenseTypeService;

	@Autowired
	public DriverLicenseTypeController(DrivingLicenseTypeService drivingLicenseTypeService) {
		this.drivingLicenseTypeService = drivingLicenseTypeService;
	}

	@RequestMapping(value = "/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DrivingLicenseType>> LicenseTypeFindAllAPI() {
		List<DrivingLicenseType> drivingLicenseType = drivingLicenseTypeService.drivingLicenseTypeFindAll();
		if (drivingLicenseType == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<List<DrivingLicenseType>>(drivingLicenseType, HttpStatus.OK);
	}

}
