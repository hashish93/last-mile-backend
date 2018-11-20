package com.appzoneltd.lastmile.microservices.stats.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservices.stats.service.SystemGeneralInfo;
import com.appzoneltd.lastmile.microservices.stats.service.pojo.StatsResponse;

@RestController
public class SystemGeneralInfoController {

	@Autowired
	private SystemGeneralInfo systemGeneralInfo;

	@RequestMapping(value = "/systemGeneralInfo", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getsystemGeneralInfo() {

		List<StatsResponse> result = systemGeneralInfo.getSystemGeneralInfo();

		if (result != null)
			return new ResponseEntity<>(result, HttpStatus.OK);

		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

	}

}
