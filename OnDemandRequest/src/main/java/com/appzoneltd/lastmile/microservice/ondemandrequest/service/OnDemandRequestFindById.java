/**
 * AppZone LTD
 * Author: Amir Serry
 * Project Name OnDemandRequest
 * May 22, 2016 1:12:59 PM
 */
package com.appzoneltd.lastmile.microservice.ondemandrequest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.core.Service;

import dummies.OnDemandRequest;
import dummies.OnDemandRequestDao;
import dummies.ServiceParameters;

/**
 * @author Mahmoud Farahat
 *
 */
@RestController
public class OnDemandRequestFindById extends Service {

	@Autowired
	OnDemandRequestDao onDemandRequestDao;

	/**
	 * June 16, 2016
	 * 
	 * @param Id
	 * @param companyId
	 * @return ResponseEntity<?>
	 */
	@RequestMapping(method = RequestMethod.POST, value = "/getondemandrequestbyid")
	public ResponseEntity<?> getOnDemandRequestByID(@RequestBody ServiceParameters serviceParameters) {

		OnDemandRequest onDemandRequest = null;
		try {
			onDemandRequest = onDemandRequestDao.findOnDemandRequest(serviceParameters.getId(), serviceParameters.getCompanyId());
			if (onDemandRequest == null)
				return new ResponseEntity<OnDemandRequest>(onDemandRequest, HttpStatus.INTERNAL_SERVER_ERROR);
		} catch (Exception e) {
			return new ResponseEntity<OnDemandRequest>(onDemandRequest, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<>(onDemandRequest, HttpStatus.OK);

	}

}
