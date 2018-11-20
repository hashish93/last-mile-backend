/**
 * AppZone LTD
 * Author: Amir Serry
 * Project Name OnDemandRequest
 * May 22, 2016 1:12:42 PM
 */
package com.appzoneltd.lastmile.microservice.ondemandrequest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.core.Service;
import com.appzoneltd.lastmile.enums.OrderBy;

import dummies.OnDemandRequest;
import dummies.OnDemandRequestDao;
import dummies.ServiceParameters;

/**
 * @author Mahmoud Farahat
 *
 */
@RestController
public class OnDemandRequestFindAll extends Service {
	@Autowired
	OnDemandRequestDao onDemandRequestDao;


	/**
	 * June 16, 2016
	 * 
	 * @param companyId
	 * @param orderType
	 * @return ResponseEntity<?>
	 */

	@PreAuthorize("hasRole('ROLE_listondemand')")
	@RequestMapping(method = RequestMethod.POST, value = "/getallondemandrequest")
	public ResponseEntity<?> getAllOnDemandRequest(@RequestBody ServiceParameters serviceParameters) {

		ResponseEntity<List<OnDemandRequest>> response = null;
		
		List<OnDemandRequest> onDemandRequests = null;
		if ("ASC".equalsIgnoreCase(serviceParameters.getOrderTypeStr())) {
			onDemandRequests = onDemandRequestDao.findAllOnDemandRequest(serviceParameters.getCompanyId(), OrderBy.ASC);
		}
		if ("DESC".equalsIgnoreCase(serviceParameters.getOrderTypeStr())) {
			onDemandRequests = onDemandRequestDao.findAllOnDemandRequest(serviceParameters.getCompanyId(), OrderBy.DESC);
		}
		response = new ResponseEntity<>(onDemandRequests, HttpStatus.OK);

		return response;
	}

	/**
	 * June 16, 2016
	 * 
	 * @param companyId
	 * @return ResponseEntity<?>
	 */
//	@RequestMapping(method = RequestMethod.POST, value = "/countallondemandrequest")
//	public ResponseEntity<?> countAllOnDemandRequest(@RequestBody ServiceParameters serviceParameters) {
//
//		int count = onDemandRequestDao.countAllOnDemandRequest(serviceParameters.getCompanyId());
//
//		ResponseEntity<Integer> response = new ResponseEntity<>(count, HttpStatus.OK);
//
//		return response;
//
//	}

}
