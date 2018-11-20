/**
 * Jul 20, 20161:17:34 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.packagelabel.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.packagelabel.dao.PackageLabel;
import com.appzoneltd.lastmile.microservice.packagelabel.service.PackageLabelService;

/**
 * @author alaa.nabil
 *
 */
@RestController
public class PackageLabelController {

	private final PackageLabelService packageLabelService;

	@Autowired
	public PackageLabelController(PackageLabelService packageLabelService) {
		this.packageLabelService = packageLabelService;
	}

	/**
	 * Method to controll httprequests to perform find all package label
	 * 
	 * 
	 * @return {@link ResponseEntity} with {@link HttpStatus} of a
	 *         {@link List<PackageLabel>}
	 */
	@RequestMapping(value = "/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PackageLabel>> packageLabelFindAllAPI() {
		List<PackageLabel> packageLabels = packageLabelService.packageLabelFindAll();
		if (packageLabels == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<List<PackageLabel>>(packageLabels, HttpStatus.OK);
	}
}
