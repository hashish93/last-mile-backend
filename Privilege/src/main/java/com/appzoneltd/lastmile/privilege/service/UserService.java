package com.appzoneltd.lastmile.privilege.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.privilege.dao.UserDao;
import com.appzoneltd.lastmile.privilege.dto.SimpleRoleDTO;
import com.appzoneltd.lastmile.privilege.dto.SimpleUserDTO;
import com.appzoneltd.lastmile.privilege.dto.AuthorityDTO;
import com.appzoneltd.lastmile.privilege.dto.BulkUserRolesDTO;
import com.appzoneltd.lastmile.privilege.dto.UserRoleAssignDTO;
import com.appzoneltd.lastmile.privilege.model.ModulePrivilege;
import com.appzoneltd.lastmile.privilege.model.Role;
import com.appzoneltd.lastmile.privilege.model.RolePrivilege;
import com.appzoneltd.lastmile.privilege.model.User;

@Service
@Qualifier("userService")
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private RoleService roleService;

	public User findById(Integer id) {
		return userDao.findByUserId(id);
	}

	public User findByIdentifier(String identifier) {
		return userDao.findByIdentifier(identifier);
	}

	public List<User> searchUserByName(String identifier) {
		// getting List of Users
		List<User> users = userDao.searchUserByName(identifier);
		List<User> newUsers = new ArrayList<User>();
		//// Checking Users
		for (User user : users) {
			boolean isSuper = false;
			for (Role role : user.getRoles()) {
				if (role.isEditable()) {
					isSuper = true;
				} // end If
			} // end inner For-Each
			if (!isSuper) {
				newUsers.add(user);
			} // end if
		}
		/// return result
		return newUsers;
	}

	public Set<AuthorityDTO> getUserAuthorities(Set<Role> roles) {
		// Set Of Authorities
		Set<AuthorityDTO> authorities = new HashSet<AuthorityDTO>();
		// Loop
		for (Role role : roles) {
			for (RolePrivilege rolePrivilege : role.getRolePrivileges()) {
				// Getting Module Privilege
				if (rolePrivilege.isEnabled()) {
					ModulePrivilege modulePrivilege = rolePrivilege.getModulePrivilege();
					// Init Object
					AuthorityDTO authorityDTO = new AuthorityDTO();
					authorityDTO.setId(modulePrivilege.getId());
					authorityDTO.setAuthority(modulePrivilege.getIdentifierName());
					/// Adding it ro Set
					authorities.add(authorityDTO);
				} // end if
			} // end Inner Loop
		} // end for Each
			// return result
		return authorities;
	}

	public List<SimpleRoleDTO> getAssignedRoleForUser(Integer userId) {
		// Getting User
		User user = findById(userId);
		// Init List of Users Related to User
		List<SimpleRoleDTO> simpleRoleDTOs = new ArrayList<SimpleRoleDTO>();
		if (user != null) {
			for (Role role : user.getRoles()) {
				if ((role.getId() != 0) &&(role.isEnabled())){
					SimpleRoleDTO simpleRoleDTO = new SimpleRoleDTO();
					simpleRoleDTO.setId(role.getId());
					simpleRoleDTO.setName(role.getName());
					// add to List
					simpleRoleDTOs.add(simpleRoleDTO);
				} // end if Conditioon
			} // end for Loop
		} // end if Condition
			// return result
		return simpleRoleDTOs;
	}

	@Transactional
	public User assignUserForRoles(UserRoleAssignDTO assignmentRole) {
		return assignManyRolesForOneUser(assignmentRole.getUserId(), assignmentRole.getSimpleRoleDTOs(), false);
	}

	@Transactional
	public List<User> assignManyUserForManyRoles(BulkUserRolesDTO bulkUserRolesDTO) {
		// Init List of Users
		List<User> users = new ArrayList<User>();
		// Loop for List of Roles
		for (SimpleUserDTO simpleUserDto : bulkUserRolesDTO.getUsers()) {
			/// Assign Bulk Roles for Current User
			User user = assignManyRolesForOneUser(simpleUserDto.getId(), bulkUserRolesDTO.getRoles(), true);
			if (user != null) {
				// Add User To UserList
				users.add(user);
			} // end if Condition

		} // end for Loop
			// return List of Users
		return users;
	}// end assignManyUserForManyRoles

	public User assignManyRolesForOneUser(Integer userId, List<SimpleRoleDTO> activeRolesDTOs, boolean bulk) {
		// Getting User
		User user = findById(userId);

		if (user != null) {
			Set<Role> roles = new HashSet<Role>();
			if (activeRolesDTOs.isEmpty()) {
				Role emptyRole = roleService.findById(0);
				roles.add(emptyRole);
			}
			for (SimpleRoleDTO activeRolesDTO : activeRolesDTOs) {

				Role role = roleService.findById(activeRolesDTO.getId());
				if (role != null) {
					roles.add(role);
				}
				// end inner if Condition
			} // end for Loop
				/// Assign Roles To User
			if (bulk) {
				user.getRoles().addAll(roles);
			} else {
				user.setRoles(null);
				user.setRoles(roles);
			} // end else
				// Save User
			userDao.save(user);
		} // end If

		return user;
	}// end

	public List<Integer> chkUserType(BulkUserRolesDTO bulkUserRolesDTO) {

		List<Integer> messages = new ArrayList<Integer>();
		for (SimpleUserDTO simpleUserDto : bulkUserRolesDTO.getUsers()) {
			User chkUserType = findById(simpleUserDto.getId());
			if (chkUserType.getUserType().equals("DRIVER")) {
				messages.add(simpleUserDto.getId());
			}

		}

		return messages;
	}

}
