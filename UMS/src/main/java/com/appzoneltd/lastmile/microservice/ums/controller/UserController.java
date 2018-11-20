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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.ums.dto.HubDto;
import com.appzoneltd.lastmile.microservice.ums.dto.RoleDto;
import com.appzoneltd.lastmile.microservice.ums.dto.UserDto;
import com.appzoneltd.lastmile.microservice.ums.dto.UserRoleAssignDTO;
import com.appzoneltd.lastmile.microservice.ums.dto.UserRoleBulkAssignDTO;
import com.appzoneltd.lastmile.microservice.ums.entity.UserEntity;
import com.appzoneltd.lastmile.microservice.ums.model.Parameter;
import com.appzoneltd.lastmile.microservice.ums.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> searchForUserByNamePattern(@RequestBody Parameter parameter) {
		List<UserDto> users = new ArrayList<>();
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		if (!parameter.getName().isEmpty()) {
			users = userService.searchUserByName(parameter.getName(), parameter.getHubId(),principal);
		}
		return new ResponseEntity<List<UserDto>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/findByIdentifier", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> gettingUserByIdentifer(@RequestBody Parameter parameter) {

		// String
		// principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
		// System.out.println("MY PRINCIPAL "+principal);

		if (!parameter.getName().isEmpty()) {
			UserDto user = userService.getUserByIdentifier(parameter.getName());
			if (user != null) {
				return new ResponseEntity<UserDto>(user, HttpStatus.OK);
			}
			Message message = new Message(MessageType.ERROR, "User", "User not found");
			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		}
		Message message = new Message(MessageType.ERROR, "User", "UserName not found");
		return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/findById", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findUserById(@RequestBody Parameter parameter) {
		if (parameter.getUserId() != null) {
			UserDto userDto = userService.getUserDtoById(parameter.getUserId());
			if (userDto != null) {
				return new ResponseEntity<UserDto>(userDto, HttpStatus.OK);
			}
			Message message = new Message(MessageType.ERROR, "User", "User Not Found");
			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		}
		Message message = new Message(MessageType.ERROR, "User", "UserId not found");
		return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/assignRolesForUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> assignRolesForUser(@RequestBody UserRoleAssignDTO userRoleAssignDTO) {
		UserEntity userEntity = userService.assignUserForRoles(userRoleAssignDTO);
		if (userEntity != null) {
			return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, "User", messageSource
					.getMessage("Roles Assigned Successfully", null, "Assigned Successfully", Locale.ENGLISH)),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, "User",
							messageSource.getMessage("Roles Not Assigned", null, "Assigned Error", Locale.ENGLISH)),
					HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/assignBulkRolesForUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> assignBulkRolesForUsers(@RequestBody UserRoleBulkAssignDTO userRoleBulkAssignDTO) {

		List<Long> messages = userService.chkUserType(userRoleBulkAssignDTO);
		if (!messages.isEmpty()) {
			return new ResponseEntity<Message>(new Message(MessageType.ERROR, "UserType",
					" Can't Assiging Roles  To Drivers : " + messages.toString()), HttpStatus.CONFLICT);
		}

		List<UserEntity> users = userService.assignManyUserForManyRoles(userRoleBulkAssignDTO);
		if ((users.isEmpty()) && (!userRoleBulkAssignDTO.getUsers().isEmpty())) {
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, "Users",
							messageSource.getMessage("Roles Not Assigned", null, "Assigned Error", Locale.ENGLISH)),
					HttpStatus.OK);
		} else {
			if (users.size() == userRoleBulkAssignDTO.getUsers().size()) {
				return new ResponseEntity<Message>(
						new Message(MessageType.SUCCESS, "Users", messageSource.getMessage(
								"Roles Assigned Successfully", null, "Assigned Successfully", Locale.ENGLISH)),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, "Users", messageSource.getMessage(
						"Some Roles Assigned Successfully", null, "Some Assigned Successfully", Locale.ENGLISH)),
						HttpStatus.OK);
			}
		}
	}

	@RequestMapping(value = "/getAssignedRoles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> gettingAllActiveRoles(@RequestBody Parameter parameter) {
		if (parameter.getUserId() != null) {
			List<RoleDto> roleDtos = userService.getAssignedRoleForUser(parameter.getUserId());
			if (roleDtos != null) {
				return new ResponseEntity<List<RoleDto>>(roleDtos, HttpStatus.OK);
			}
			Message message = new Message(MessageType.ERROR, "User", "User Not Found");
			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		}
		Message message = new Message(MessageType.ERROR, "User", "UserId not found");
		return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
	}

	@RequestMapping(value = "/getUserHubs")
	public ResponseEntity<?> gettingAllActiveRoles() {
		Principal principal = SecurityContextHolder.getContext().getAuthentication();
		List<HubDto> hubDtos = userService.getUserHubs(principal);
		return new ResponseEntity<>(hubDtos, HttpStatus.OK);
	}
}
