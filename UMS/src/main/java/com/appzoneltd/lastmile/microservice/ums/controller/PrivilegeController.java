package com.appzoneltd.lastmile.microservice.ums.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.ums.dto.SystemModule;
import com.appzoneltd.lastmile.microservice.ums.service.ModulePrivilegeService;

@RestController
@RequestMapping("/privilege")
public class PrivilegeController {

	@Autowired
	private ModulePrivilegeService modulePrivilegeService;
	
	@RequestMapping(value = "/findAll", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<?> getAllSystemPrivileges() {
	        
			List<SystemModule> systemModules=modulePrivilegeService.getAllSystemModules();
			
	     return new ResponseEntity<List<SystemModule>>(systemModules, HttpStatus.OK);
	  }
	
}
