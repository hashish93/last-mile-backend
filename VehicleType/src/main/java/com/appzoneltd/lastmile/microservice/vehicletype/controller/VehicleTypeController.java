/**
 * Jul 20, 20161:17:34 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.vehicletype.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.vehicletype.dao.VehicleType;
import com.appzoneltd.lastmile.microservice.vehicletype.service.VehicleTypeService;

/**
 * @author alaa.nabil
 *
 */
@RestController
public class VehicleTypeController {

	private final VehicleTypeService vehicleTypeService;

	@Autowired
	public VehicleTypeController(VehicleTypeService vehiclTypeService) {
		this.vehicleTypeService = vehiclTypeService;
	}

	/**
	 * Method to controll httprequests to perform find all vehicle types
	 * 
	 * @param companyId
	 * @return {@link ResponseEntity} with {@link HttpStatus} of a
	 *         {@link List<VehicleType>}
	 */
	@RequestMapping(value = "/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<VehicleType>> vehicleTypeFindAllAPI() {
		List<VehicleType> vehicleTypes = vehicleTypeService.vehicleTypeFindAll();
		if (vehicleTypes == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<List<VehicleType>>(vehicleTypes, HttpStatus.OK);
	}
}
