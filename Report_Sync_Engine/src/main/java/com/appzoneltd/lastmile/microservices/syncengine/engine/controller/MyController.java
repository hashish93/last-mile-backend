package com.appzoneltd.lastmile.microservices.syncengine.engine.controller;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservices.syncengine.engine.service.SyncReportingService;



@RestController
public class MyController {

	@Autowired
	private SyncReportingService syncReportingService;
	
	@RequestMapping(value = "/start", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> start() throws SchedulerException {
		syncReportingService.startEngine();
		return new ResponseEntity<>("START", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stop", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> stop() throws SchedulerException {
		syncReportingService.stopEngine();
		return new ResponseEntity<>("STOP", HttpStatus.OK);
	}
	
	@RequestMapping(value = "/restart", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> restart() throws SchedulerException {
		syncReportingService.restartEngine();
		return new ResponseEntity<>("RESTART", HttpStatus.OK);
	}

	
}
