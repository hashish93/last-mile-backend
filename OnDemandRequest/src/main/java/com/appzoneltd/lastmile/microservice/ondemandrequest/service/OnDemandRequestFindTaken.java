package com.appzoneltd.lastmile.microservice.ondemandrequest.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

@RestController
public class OnDemandRequestFindTaken extends Service {

	@Autowired
	OnDemandRequestDao onDemandRequestDao;

//	@RequestMapping(method = RequestMethod.POST, value = "/getalltakenondemand", produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<?> getAllTakenOnDemand(@RequestBody ServiceParameters serviceParameters) {
//
//		ResponseEntity<List<OnDemandRequest>> response = null;
//
//		List<OnDemandRequest> onDemandRequests = null;
//		try {
//			if ("ASC".equalsIgnoreCase(serviceParameters.getOrderTypeStr())) {
//				onDemandRequests = onDemandRequestDao.findTakenOnDemand(serviceParameters.getCompanyId(), OrderBy.ASC);
//			}
//			if ("DESC".equalsIgnoreCase(serviceParameters.getOrderTypeStr())) {
//				onDemandRequests = onDemandRequestDao.findTakenOnDemand(serviceParameters.getCompanyId(), OrderBy.DESC);
//			}
//			response = new ResponseEntity<>(onDemandRequests, HttpStatus.OK);
//		} catch (Exception e) {
//
//		}
//
//		return response;
//	}
}
