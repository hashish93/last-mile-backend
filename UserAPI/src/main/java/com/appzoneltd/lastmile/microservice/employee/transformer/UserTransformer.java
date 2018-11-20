package com.appzoneltd.lastmile.microservice.employee.transformer;
import com.google.common.collect.Lists;
import java.util.Date;

import java.util.ArrayList;
import java.util.List;

import com.appzoneltd.lastmile.microservice.employee.dto.UserProfileDTO;
import com.appzoneltd.lastmile.microservice.employee.entity.UserEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.UserHubEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.UserRoleEntity;
import com.appzoneltd.lastmile.microservice.employee.enums.UserEntityStatus;
import com.appzoneltd.lastmile.microservice.employee.model.ActiveHub;
import com.appzoneltd.lastmile.microservice.employee.model.ActiveRole;
import com.appzoneltd.lastmile.microservice.employee.model.Driver;
import com.appzoneltd.lastmile.microservice.employee.model.Employee;

public class UserTransformer {

	public static List<Employee> getEmployeesFromUserEntities(List<UserEntity> userEntities) {
		if (!userEntities.isEmpty()) {
			List<Employee> employees = new ArrayList<>();
			for (UserEntity userEntity : userEntities) {
				employees.add(getEmployeeFromUserEntity(userEntity));
			}
			return employees;
		}
		return null;
	}

	public static Employee getEmployeeFromUserEntity(UserEntity userEntity) {
		Employee employee = new Driver();
		employee.setUserId(userEntity.getUserId());
		employee.setFirstName(userEntity.getFirstname());
		employee.setLastName(userEntity.getLastname());
		employee.setNationalId(userEntity.getNationalId());
		if(userEntity.getCountryCodes() !=null){
			employee.setCountryCodeId(userEntity.getCountryCodes().getId());	
		}
		employee.setPersonalPhotoId(userEntity.getPersonalPhotoId());
		employee.setUsername(userEntity.getUsername());
		employee.setCreated(userEntity.getCreated());
		employee.setEmail(userEntity.getEmail());
		employee.setPhone(userEntity.getPhone());
		if (userEntity.getUserType() != null) {
			employee.setUserTypeId(userEntity.getUserType().getId());
			employee.setUserType(userEntity.getUserType().getName());
		}
		
		if(userEntity.getUserHubs()!=null){
			List<Long> hubs=new ArrayList<>();
			List<ActiveHub> activeHubs=new ArrayList<>();
			List<String> hubNames = new ArrayList<>();
			for(UserHubEntity userHubEntity:userEntity.getUserHubs()){
				if(userHubEntity.getBuilding()!=null){
					hubs.add(userHubEntity.getBuilding().getBuildingId());
					hubNames.add(userHubEntity.getBuilding().getBuildingname());
					ActiveHub activeHub = new ActiveHub();
					activeHub.setId(userHubEntity.getBuilding().getBuildingId());
					activeHub.setName(userHubEntity.getBuilding().getBuildingname());
					activeHubs.add(activeHub);
				}				
			}	
			employee.setHubs(hubs);
			employee.setHubNames(hubNames.toString().replace("[", "").replace("]", "").trim());
			employee.setActiveHubs(activeHubs);
		}
		if(userEntity.getUsersRoles() !=null){
			List<Long> roles = new ArrayList<>();
			List<ActiveRole> activeRoles = new ArrayList<>();
			for(UserRoleEntity userRoleEntity:userEntity.getUsersRoles()){
				if(userRoleEntity.getRoles()!=null){
					if(!userRoleEntity.getRoles().isSystemRole() && userRoleEntity.getRoles().isEnabled()){
						roles.add(userRoleEntity.getRoles().getRoleId());
						ActiveRole activeRole = new ActiveRole();
						activeRole.setId(userRoleEntity.getRoles().getRoleId());
						activeRole.setName(userRoleEntity.getRoles().getRolename());
						activeRoles.add(activeRole);
					}
				}				
			}	
			employee.setRoles(roles);
			employee.setActiveRoles(activeRoles);
		}
		employee.setCreated(userEntity.getCreated());
		employee.setUserEntityStatus(UserEntityStatus.valueOf(userEntity.getStatus()));
		employee.setEnabled(userEntity.getEnabled());
		employee.setDescription(userEntity.getDescription());
		return employee;
	}

	public static Employee getEmployeeFromUserProfileDTO(UserProfileDTO userProfileDTO){
		if(userProfileDTO !=null){
			Employee employee= new Employee();
			employee.setUserId(userProfileDTO.getUserId());
			employee.setUsername(userProfileDTO.getUserName());
			employee.setPassword(null);
			employee.setFirstName(userProfileDTO.getFirstName());
			employee.setLastName(userProfileDTO.getLastName());
			employee.setCountryCodeId(userProfileDTO.getCountryCodeId());
			employee.setPhone(userProfileDTO.getPhoneNumber());
			employee.setEmail(userProfileDTO.getEmail());
			employee.setUserEntityStatus(null);
			employee.setUserTypeId(null);
			employee.setUserType(null);
			employee.setEnabled(null);
			employee.setPersonalPhotoId(null);
			employee.setNationalId(userProfileDTO.getNationalId());
			employee.setCreated(null);
			employee.setDescription(null);
			employee.setVersion(null);
			employee.setRoles(Lists.newArrayList());
			employee.setActiveRoles(Lists.newArrayList());
			employee.setHubs(Lists.newArrayList());
			employee.setActiveHubs(Lists.newArrayList());
			employee.setHubNames(null);
			return employee;
		}
		return null;
	}
	
	

}
