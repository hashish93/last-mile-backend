package com.appzoneltd.lastmile.microservice.packagetype.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.packagetype.dao.PackageType;
import com.appzoneltd.lastmile.microservice.packagetype.service.PackageTypeService;

/**
 * @author alaa.nabil
 *
 */
@RestController
public class PackageTypeController {
	private final static Logger LOGGER = LoggerFactory.getLogger(PackageTypeController.class);
	private final PackageTypeService packageTypeService;

	@Autowired
	public PackageTypeController(PackageTypeService packageTypeService) {
		this.packageTypeService = packageTypeService;
	}

	/**
	 * Method packageTypesFindAll to return list of all Package types in a JSON
	 * file request method POST
	 * 
	 * @param companyid
	 *            and it is required parameter, page "by default its -1 to
	 *            return all data", maxresult "per page and it is required
	 *            parameter", ordertype " by default its ASCENDING"
	 * 
	 * 
	 **/
	@RequestMapping(value = "/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PackageType>> packageTypeFindAll() {
		List<PackageType> packageTypes = null;
		try {
			packageTypes = packageTypeService.findAllPackageTypes();
			if (packageTypes == null)
				return new ResponseEntity<List<PackageType>>(packageTypes, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return new ResponseEntity<List<PackageType>>(packageTypes, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<List<PackageType>>(packageTypes, HttpStatus.OK);
	}
}
