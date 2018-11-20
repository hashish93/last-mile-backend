/**
 * Jul 20, 20161:17:34 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.buildingtype.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.buildingtype.dao.BuildingTypeEntity;
import com.appzoneltd.lastmile.microservice.buildingtype.service.BuildingTypeServiceImpl;

/**
 * @author alaa.nabil
 *
 */
@RestController
public class BuildingTypeController {
	/** Autowiring Building Type service annotated class */
	@Autowired
	private BuildingTypeServiceImpl buildingTypeServiceImpl;

	/**
	 * Method to control httprequests to perform find all Building Type
	 * 
	 * @param companyId
	 * @return {@link ResponseEntity} with {@link HttpStatus} of a
	 *         {@link List<BuildingType>}
	 */
	@RequestMapping(value = "/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<BuildingTypeEntity>> buildingTypeFindAllAPI() {
		return new ResponseEntity<List<BuildingTypeEntity>>(buildingTypeServiceImpl.getAllBuildingtypes(), HttpStatus.OK);
	}
}
