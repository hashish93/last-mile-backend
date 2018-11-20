package com.appzoneltd.lastmile.microservice.ums.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.ums.dto.ActiveRole;
import com.appzoneltd.lastmile.microservice.ums.dto.RoleInfomation;
import com.appzoneltd.lastmile.microservice.ums.dto.RoleModel;
import com.appzoneltd.lastmile.microservice.ums.dto.RoleUser;
import com.appzoneltd.lastmile.microservice.ums.model.Parameter;
import com.appzoneltd.lastmile.microservice.ums.service.BuildingService;
import com.appzoneltd.lastmile.microservice.ums.service.PrincipalService;
import com.appzoneltd.lastmile.microservice.ums.service.RoleService;

@RestController
@RequestMapping("/role")
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private PrincipalService principalService;

	 @PreAuthorize("hasRole('ROLE_listroles')")
	@RequestMapping(value = "/findAllBaseInfo", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllRolesBaseInfo() {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		List<RoleInfomation> roleInfomations = new ArrayList<>();
		roleInfomations = roleService.getAllHubRolesInformation(principal);
		return new ResponseEntity<List<RoleInfomation>>(roleInfomations, HttpStatus.OK);
	}

	 @PreAuthorize("hasRole('ROLE_deleteroles')")
	@RequestMapping(value = "/activateOrDeactivate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> activateOrDeactivateRole(@RequestBody Parameter parameter) {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		RoleInfomation roleInfomation = roleService.activateOrDeActivate(principal, parameter.getRoleId(),
				parameter.isActive());
		if (roleInfomation == null) {
			Message message = new Message(MessageType.ERROR, "Role", "Role Not Found Or Not Editable");
			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, "Enable",
				messageSource.getMessage("Role Updated Successfully", null, "Update Successfully", Locale.ENGLISH)),
				HttpStatus.OK);
	}

	 @PreAuthorize("hasRole('ROLE_addeditroles')")
	@RequestMapping(value = "/saveOrUpdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveOrUpateModule(@Validated @RequestBody RoleModel roleModel, Errors errors) {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		List<Message> messages = roleService.checkRoleNameExist(roleModel);
		if (roleModel.getHubId() != null) {
			Message buildingMessage = buildingService.checkBuildingExist(roleModel.getHubId());
			if (buildingMessage != null) {
				messages.add(buildingMessage);
			}
		}

		if (errors.hasErrors()) {
			for (ObjectError objectError : errors.getAllErrors()) {
				Message message = new Message(MessageType.ERROR, "Role", objectError.getDefaultMessage());
				messages.add(message);
			}
			return new ResponseEntity<List<Message>>(messages, HttpStatus.BAD_REQUEST);
		} else {
			if (messages.size() > 0) {
				return new ResponseEntity<List<Message>>(messages, HttpStatus.BAD_REQUEST);
			} else {
				roleService.saveOrUpdateRole(roleModel,principalService.getUserType(principal));
				return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, "role saved successfully"),
						HttpStatus.OK);
			}
		}
	}

	@RequestMapping(value = "/findById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findRoleById(@RequestBody Parameter parameter) {
		if (parameter.getRoleId() != null) {
			Principal principal = SecurityContextHolder.getContext().getAuthentication();
			RoleModel roleModel = roleService.getRoleModelById(parameter.getRoleId() , principalService.isSuperAdmin(principal));
			if (roleModel != null) {
				return new ResponseEntity<RoleModel>(roleModel, HttpStatus.OK);
			}
			Message message = new Message(MessageType.ERROR, "Role", "Role Not Found");
			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		}
		Message message = new Message(MessageType.ERROR, "Role", "RoleId not found");
		return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/findByUserId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> gettingRoleByUser(@RequestBody Parameter parameter) {
		if (parameter.getUserId() != null) {
			RoleUser roleUser = roleService.getRoleUserByUserId(parameter.getUserId());
			if (roleUser != null) {
				return new ResponseEntity<RoleUser>(roleUser, HttpStatus.OK);
			}
			Message message = new Message(MessageType.ERROR, "User", "User Not Found");
			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		}
		Message message = new Message(MessageType.ERROR, "User", "UserId not found");
		return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getAllActiveRoles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> gettingAllActiveRoles(@RequestBody Parameter parameter) {
	 	Principal principal = SecurityContextHolder.getContext().getAuthentication();
		List<ActiveRole> activeRoles = roleService.getAllActiveRolesForHubs(parameter.getHubs(),principal);
		return new ResponseEntity<List<ActiveRole>>(activeRoles, HttpStatus.OK);
	}

}
