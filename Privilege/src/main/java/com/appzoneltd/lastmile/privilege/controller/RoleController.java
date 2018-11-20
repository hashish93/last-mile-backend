package com.appzoneltd.lastmile.privilege.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.privilege.dao.RoleDao;
import com.appzoneltd.lastmile.privilege.dto.RoleBaseInfoDTO;
import com.appzoneltd.lastmile.privilege.dto.RoleResponseDTO;
import com.appzoneltd.lastmile.privilege.dto.RoleUserDTO;
import com.appzoneltd.lastmile.privilege.dto.SimpleRoleDTO;
import com.appzoneltd.lastmile.privilege.holder.Parameters;
import com.appzoneltd.lastmile.privilege.model.Role;
import com.appzoneltd.lastmile.privilege.service.RoleService;
import com.appzoneltd.lastmile.privilege.transform.RoleTransformer;

@RestController
public class RoleController {

	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleTransformer roleTransformer;

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private MessageSource messageSource;

	/******************************************************
	 * Service findAllBaseInfo for Role Menu
	 ******************************************************/
	@PreAuthorize("hasRole('ROLE_listroles')")
	@RequestMapping(value = "/role/findAllBaseInfo", consumes = MediaType.ALL_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RoleBaseInfoDTO>> getAllRolesBaseInfo() {
		// Getting List of Roles
		List<Role> roles = (List<Role>) roleService.findAll();
		List<RoleBaseInfoDTO> baseInfoDTOs = new ArrayList<RoleBaseInfoDTO>();
		// Filling RoleBaseInfoDTO
		for (Role role : roles) {
			RoleBaseInfoDTO baseInfoDTO = new RoleBaseInfoDTO();
			baseInfoDTO.setId(role.getId());
			baseInfoDTO.setName(role.getName());
			baseInfoDTO.setDescription(role.getDescription());
			baseInfoDTO.setEnabled(role.isEnabled());
			baseInfoDTO.setEditable(role.isEditable());

			// Adding to List
			baseInfoDTOs.add(baseInfoDTO);
		} // end for Each
			// return results
		return new ResponseEntity<List<RoleBaseInfoDTO>>(baseInfoDTOs, HttpStatus.OK);
	}

	/******************************************************
	 * Service : Activate Role
	 ******************************************************/

	@PreAuthorize("hasRole('ROLE_deleteroles')")
	@RequestMapping(value = "/role/activateOrDeactivate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> activateOrDeactivateRole(@RequestBody Parameters parameters) {
		// Getting Role By Id
		Role role = roleService.activateOrDeActivate(parameters.getId(), parameters.isValue());
		RoleBaseInfoDTO baseInfoDTO = new RoleBaseInfoDTO();
		// Filling
		if (role != null) {
			baseInfoDTO.setId(role.getId());
			baseInfoDTO.setName(role.getName());
			baseInfoDTO.setDescription(role.getDescription());
			baseInfoDTO.setEnabled(role.isEnabled());
		} else {
			// Error Message Return
			Message message = new Message(MessageType.ERROR, "Role", "Role Not Found Or Not Editable");
			// return Error Message
			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, "Enable",
				messageSource.getMessage("Role Updated Successfully", null, "Update Successfully", Locale.ENGLISH)),
				HttpStatus.OK);

		// return results
		// return new ResponseEntity<RoleBaseInfoDTO>(baseInfoDTO,
		// HttpStatus.OK);
	}

	/***********************************************************
	 ** Role AddEdit Service
	 ***********************************************************/

	@PreAuthorize("hasRole('ROLE_addeditroles')")
	@RequestMapping(value = "/role/saveOrUpdate", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> saveOrUpateModule(@Validated @RequestBody RoleResponseDTO roleResponseDTO, Errors errors) {
		// Define the RoleTransformer

		List<Message> messages = new ArrayList<Message>();
		Role chkRoleName = roleDao.chkExistingRoleName(roleResponseDTO.getName().toLowerCase());

		// In Save Role
		if (roleResponseDTO.getId() == null) {
			if (chkRoleName != null) {
				Message message = new Message(MessageType.ERROR, "name", "Role Name Already Exist");
				messages.add(message);
				// return Error Message
				return new ResponseEntity<List<Message>>(messages, HttpStatus.BAD_REQUEST);
			} // end if
		}
		// In Update Role
		else {
			if (chkRoleName != null) {

				if (chkRoleName.getId().intValue() != roleResponseDTO.getId().intValue()) {
					Message message = new Message(MessageType.ERROR, "name", "Role Name Already Exist");
					messages.add(message);
					// return Error Message
					return new ResponseEntity<List<Message>>(messages, HttpStatus.BAD_REQUEST);
				}
			} // end if
		} // end else Condition

		if (errors.hasErrors()) {
			/// Init List of Errors

			// Adding List
			for (ObjectError objectError : errors.getAllErrors()) {
				Message message = new Message(MessageType.ERROR, "Role", objectError.getDefaultMessage());
				// Add Error to List
				messages.add(message);
			}
			// return Error Message
			return new ResponseEntity<List<Message>>(messages, HttpStatus.BAD_REQUEST);
		}

		else {
			// RoleTransformer Object
			RoleTransformer roleTransformer = new RoleTransformer();
			// Getting Role
			Role role = roleService.saveOrUpdateFromResponseDTO(roleResponseDTO);
			if (role != null) {
				RoleResponseDTO responseDTO = roleTransformer.getDTOsFromEntities(role);
				// return result
//				return new ResponseEntity<RoleResponseDTO>(responseDTO, HttpStatus.OK);
				return new ResponseEntity<Message>(new Message(MessageType.SUCCESS, "" ),
						HttpStatus.OK);
			} else {
				// Error Message Return
				Message message = new Message(MessageType.ERROR, "Role", "Role Not Found Or Not Editable");
				// return Error Message
				return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
			} // end Inner If-Else
		} // end Outer If-Else

	}
	////////////////////////////////////////////////////////////////////////////////////
	/************************************************************
	 * Getting ROLE & ROLE LIST PART
	 ************************************************************/
	//////////////////////////////////////////////////////////////////////////////////

	/***********************************************************
	 * Find Role By RoleID
	 ***********************************************************/
	@RequestMapping(value = "/role/findById", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findRoleById(@RequestBody Parameters parameters) {
		// Getting Role

		// RoleDTO roleDTO=roleService.getFullRoleDto(roleId);
		Role role = roleService.findFullRole(parameters.getId());
		RoleResponseDTO roleDTO = new RoleResponseDTO();
		if (role != null) {
			roleDTO = roleTransformer.getDTOsFromEntities(role);
			return new ResponseEntity<RoleResponseDTO>(roleDTO, HttpStatus.OK);
		} else {
			// Error Message Return
			Message message = new Message(MessageType.ERROR, "Role", "Role Not Found");

			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		} // end if-ERoleUserDTOlse Block

	}

	/***********************************************************
	 * Find Role By UserId
	 ***********************************************************/
	@RequestMapping(value = "/role/findByUserId", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> gettingRoleByUser(@RequestBody Parameters parameters) {
		// Getting Role
		List<Role> userRoles = roleService.findByUser(parameters.getId());
		// RoleUserDTO
		RoleUserDTO roleUserDTO = roleTransformer.getRoleUserDTOFromRoles(userRoles);
		if (roleUserDTO != null) {
			return new ResponseEntity<RoleUserDTO>(roleUserDTO, HttpStatus.OK);
		} else {
			// Error Message Return
			Message message = new Message(MessageType.ERROR, "Role", "Role Not Found");

			return new ResponseEntity<Message>(message, HttpStatus.BAD_REQUEST);
		} // end if-Else Block
	}

	///////////////////////////////////////////////////////////////
	/*************************************************************
	 * Getting All the Active and available Roles
	 *************************************************************/
	///////////////////////////////////////////////////////////
	@RequestMapping(value = "/role/getAllActiveRoles", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> gettingAllActiveRoles() {
		// Getting Role
		List<SimpleRoleDTO> activeRoles = roleService.gettingAllActiveRolesDTOS();
		// return result
		return new ResponseEntity<List<SimpleRoleDTO>>(activeRoles, HttpStatus.OK);
	}// gettingAllActiveRoles

}
