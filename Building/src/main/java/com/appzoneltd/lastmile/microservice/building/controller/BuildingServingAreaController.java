package com.appzoneltd.lastmile.microservice.building.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.building.ServingArea.dao.BuildingServingAreaDto;
import com.appzoneltd.lastmile.microservice.building.ServingArea.dao.ServingArealocation;
import com.appzoneltd.lastmile.microservice.building.ServingArea.service.BuildingServingAreaService;



@RestController
public class BuildingServingAreaController {

	private final BuildingServingAreaService buildingServingAreaService ;
	
	@Autowired
	public BuildingServingAreaController(BuildingServingAreaService buildingServingAreaService) {
		this.buildingServingAreaService = buildingServingAreaService;
		
	}

	
	@RequestMapping(value = "/findallServingArea", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<BuildingServingAreaDto>> viewAllBuildingServingArea() {
		Collection<BuildingServingAreaDto> buildingServingAreas = null;
		buildingServingAreas = buildingServingAreaService.findAllBuildingServingArea();
		if (buildingServingAreas == null)
			return new ResponseEntity<>(buildingServingAreas, HttpStatus.INTERNAL_SERVER_ERROR);

		return new ResponseEntity<>(buildingServingAreas, HttpStatus.OK);
	}
	

	@RequestMapping(value = "/findPickUpServingHub", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findPickUpServingHub(@RequestBody ServingArealocation pickUpLocation) {
		
		Long buildingId=buildingServingAreaService.gettingPickRequestServingAreaHubBuilding(pickUpLocation.getLatitude(),pickUpLocation.getLongitude());
		
		return new ResponseEntity<>("{\"id\":"+buildingId+"}", HttpStatus.OK);
	}

	
	

	
	
}
