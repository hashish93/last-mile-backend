package com.appzoneltd.lastmile.privilege.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.privilege.dto.BulkUserRolesDTO;
import com.appzoneltd.lastmile.privilege.dto.PrivilegeResponseDTO;
import com.appzoneltd.lastmile.privilege.dto.RoleUserDTO;
import com.appzoneltd.lastmile.privilege.dto.SimpleRoleDTO;
import com.appzoneltd.lastmile.privilege.dto.UserRoleAssignDTO;
import com.appzoneltd.lastmile.privilege.holder.Parameters;
import com.appzoneltd.lastmile.privilege.model.Role;
import com.appzoneltd.lastmile.privilege.model.User;
import com.appzoneltd.lastmile.privilege.service.RoleService;
import com.appzoneltd.lastmile.privilege.service.UserService;
import com.appzoneltd.lastmile.privilege.transform.RoleTransformer;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleTransformer roleTransformer;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(value = "/user/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<User>> searchForUserByNamePattern(@RequestBody Parameters parameters) {
		// Init List of Users
		List<User> users = new ArrayList<User>();
		if (!parameters.getName().isEmpty()) {
			// Getting List of Roles
			users = userService.searchUserByName(parameters.getName());
		} // end If Condition
			// return results
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/findByIdentifier", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> gettingUserByIdentifer(@RequestParam("userName") String userName) {
		// Getting List of Roles
		User user = userService.findByIdentifier(userName);
		// return results
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/findById", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> findUserById(@RequestBody Parameters parameters) {
		// Getting List of Roles
		User user = userService.findById(parameters.getId());
		// return results
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/getAuthorities", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<String>> gettingUserAuthority(@RequestBody Parameters parameters) {
		// Getting List of Roles
		List<Role> userRoles = roleService.findByUser(parameters.getId());
		// RoleUserDTO
		RoleUserDTO roleUserDTO = roleTransformer.getRoleUserDTOFromRoles(userRoles);
		/// Authorities
		List<String> authorities = new ArrayList<String>();
		// Filling Result
		for (PrivilegeResponseDTO priviege : roleUserDTO.getPrivilegeResponseDTOs()) {
			authorities.add(priviege.getName());
		}
		// return results
		return new ResponseEntity<List<String>>(authorities, HttpStatus.OK);
	}

	///////////////////////////////////////////////////////////////
	/*************************************************************
	 * Assign User For Roles
	 * 
	 * @throws DuplicatedKeyException
	 *************************************************************/
	///////////////////////////////////////////////////////////
	@RequestMapping(value = "/user/assignRolesForUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> assignRolesForUser(@RequestBody UserRoleAssignDTO userRoleAssignDTO) {
		// Getting Role
		User user = userService.assignUserForRoles(userRoleAssignDTO);

		if (user != null) {
			return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, "User", messageSource
					.getMessage("Roles Assigned Successfully", null, "Assigned Successfully", Locale.ENGLISH)),
					HttpStatus.OK);
		} else {
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, "User",
							messageSource.getMessage("Roles Not Assigned", null, "Assigned Error", Locale.ENGLISH)),
					HttpStatus.OK);
		} // end If-Else Block
	}// End assignRoles

	@RequestMapping(value = "/user/assignBulkRolesForUser", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> assignBulkRolesForUsers(@RequestBody BulkUserRolesDTO bulkUserRolesDTO) {

		List<Integer> messages = userService.chkUserType(bulkUserRolesDTO);
		if (!messages.isEmpty()) {
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR,"UserType"," Can't Assiging Roles  To Drivers : " + messages.toString()),
					HttpStatus.CONFLICT);
		}

		// Getting Role
		List<User> users = userService.assignManyUserForManyRoles(bulkUserRolesDTO);

		if ((users.isEmpty()) && (!bulkUserRolesDTO.getUsers().isEmpty())) {
			return new ResponseEntity<Message>(
					new Message(MessageType.ERROR, "Users",
							messageSource.getMessage("Roles Not Assigned", null, "Assigned Error", Locale.ENGLISH)),
					HttpStatus.OK);
		} else {
			if (users.size() == bulkUserRolesDTO.getUsers().size()) {
				return new ResponseEntity<Message>(
						new Message(MessageType.SUCCESS, "Users", messageSource.getMessage(
								"Roles Assigned Successfully", null, "Assigned Successfully", Locale.ENGLISH)),
						HttpStatus.OK);
			} else {
				return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, "Users", messageSource.getMessage(
						"Some Roles Assigned Successfully", null, "Some Assigned Successfully", Locale.ENGLISH)),
						HttpStatus.OK);
			}
		} // end If-Else Block
	}// End assignRoles
		///////////////////////////////////////////////////////////////

	/*************************************************************
	 * Getting User Roles
	 *************************************************************/
	///////////////////////////////////////////////////////////
	@RequestMapping(value = "/user/getAssignedRoles", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SimpleRoleDTO>> gettingAllActiveRoles(@RequestBody Parameters parameters) {

		List<SimpleRoleDTO> activeRolesDTOs = userService.getAssignedRoleForUser(parameters.getId());
		// return result
		return new ResponseEntity<List<SimpleRoleDTO>>(activeRolesDTOs, HttpStatus.OK);
	}// end assignedRoles

}
