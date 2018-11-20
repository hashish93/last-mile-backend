package com.appzoneltd.lastmile.privilege.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.privilege.dto.ModuleDto;
import com.appzoneltd.lastmile.privilege.model.ModulePrivilege;
import com.appzoneltd.lastmile.privilege.service.ModulePrivilegeService;
import com.appzoneltd.lastmile.privilege.transform.ModulePrivilegeTransformer;


@RestController
public class PrivilegeController {

	@Autowired
	private ModulePrivilegeService modulePrivilegeService;
	
	  @RequestMapping(value = "/privilege/findAll", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	  public ResponseEntity<List<ModuleDto>> getAllPrivileges() {
	         // Getting List of Roles 
	         List<ModulePrivilege> modulePrivileges= (List<ModulePrivilege>) modulePrivilegeService.findAll();
	         
	         // Init the ModuleDTOS
	         List<ModuleDto> moduleDtos=ModulePrivilegeTransformer.getDTOsFromEntities(modulePrivileges);
	    	// return results
	     return new ResponseEntity<List<ModuleDto>>(moduleDtos, HttpStatus.OK);
	  }
	
}
