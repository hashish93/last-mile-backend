package com.appzoneltd.lastmile.microservice.lookup.controller;

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

import com.appzoneltd.lastmile.microservice.lookup.dto.ShipmentServiceDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.ShipmentServiceTypeDto;
import com.appzoneltd.lastmile.microservice.lookup.service.ShipmentService;

@RestController
@RequestMapping("/shipmentservice")
public class ShipmentServiceController {

	@Autowired
	private ShipmentService shipmentService;

	@RequestMapping(value = "/findallshipmentservice", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> shipmentServiceFindAll() {
		List<ShipmentServiceDto> shipmentServices = shipmentService.getAllShipmentServices();
		return new ResponseEntity<>(shipmentServices, HttpStatus.OK);
	}

	@RequestMapping(value = "/findalltypes", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> serviceTypesFindAll(@RequestBody Map<String, Long> params) {
		List<ShipmentServiceTypeDto> shipmentServiceTypes = shipmentService
				.getShipmentServiceTypeByServiceId(params.get("id"));
		return new ResponseEntity<>(shipmentServiceTypes, HttpStatus.OK);

	}

}
