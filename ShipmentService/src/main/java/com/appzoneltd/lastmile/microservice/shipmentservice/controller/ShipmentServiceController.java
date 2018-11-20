/**
 * Jul 20, 20161:17:34 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.shipmentservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.shipmentservice.dao.ShipmentServiceEntity;
import com.appzoneltd.lastmile.microservice.shipmentservice.dao.ShipmentServiceTypeEntity;
import com.appzoneltd.lastmile.microservice.shipmentservice.service.ShipmentServiceTypeService;

/**
 * @author alaa.nabil
 *
 */
@RestController
public class ShipmentServiceController {

	private final ShipmentServiceTypeService typeService;

	@Autowired
	public ShipmentServiceController(ShipmentServiceTypeService typeService) {
		this.typeService = typeService;
	}

	/**
	 * Method to controller httprequests to perform find all shipment Services
	 */
	@RequestMapping(value = "/findallshipmentservice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShipmentServiceEntity>> shipmentServiceFindAll() {
		List<ShipmentServiceEntity> shipmentServices = typeService.shipmentServiceFindAll();
		if (shipmentServices == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<List<ShipmentServiceEntity>>(shipmentServices, HttpStatus.OK);

	}

	/**
	 * Method to controll httprequests to perform find all service types by
	 * shipment service id
	 */
	@RequestMapping(value = "/findalltypes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ShipmentServiceTypeEntity>> serviceTypesFindAllAPI(
			@RequestBody Map<String, Long> params) {
		List<ShipmentServiceTypeEntity> serviceTypes = typeService.serviceTypesFindAllByServiceId(params.get("id"));
		if (serviceTypes == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<List<ShipmentServiceTypeEntity>>(serviceTypes, HttpStatus.OK);

	}
}
