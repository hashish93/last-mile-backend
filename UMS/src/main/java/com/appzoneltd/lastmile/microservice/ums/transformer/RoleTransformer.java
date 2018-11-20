package com.appzoneltd.lastmile.microservice.ums.transformer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.appzoneltd.lastmile.microservice.ums.dto.ActiveRole;
import com.appzoneltd.lastmile.microservice.ums.dto.PrivilegeModel;
import com.appzoneltd.lastmile.microservice.ums.dto.RoleDto;
import com.appzoneltd.lastmile.microservice.ums.dto.RoleInfomation;
import com.appzoneltd.lastmile.microservice.ums.dto.RoleModel;
import com.appzoneltd.lastmile.microservice.ums.dto.RoleUser;
import com.appzoneltd.lastmile.microservice.ums.dto.UserModel;
import com.appzoneltd.lastmile.microservice.ums.entity.RoleEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.RolePrivilegeEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.UserEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.UserRoleEntity;

public class RoleTransformer {

	public static List<RoleInfomation> getRoleInformationFromEntities(List<RoleEntity> roleEntities) {

		List<RoleInfomation> roleInfomations = new ArrayList<>();

		for (RoleEntity roleEntity : roleEntities) {
			RoleInfomation roleInfomation = new RoleInfomation();
			roleInfomation.setId(roleEntity.getRoleId());
			roleInfomation.setName(roleEntity.getRolename());
			roleInfomation.setDescription(roleEntity.getDescription());
			roleInfomation.setEnabled(roleEntity.isEnabled());
			roleInfomation.setEditable(roleEntity.isEditable());
			if (roleEntity.getBuilding() != null) {
				roleInfomation.setHubName(roleEntity.getBuilding().getBuildingname());
			}
			roleInfomations.add(roleInfomation);
		}
		return roleInfomations;
	}

	public static RoleInfomation getRoleInformationFromEntity(RoleEntity roleEntity) {
		RoleInfomation roleInfomation = new RoleInfomation();
		roleInfomation.setId(roleEntity.getRoleId());
		roleInfomation.setName(roleEntity.getRolename());
		roleInfomation.setDescription(roleEntity.getDescription());
		roleInfomation.setEnabled(roleEntity.isEnabled());
		roleInfomation.setEditable(roleEntity.isEditable());
		return roleInfomation;
	}

	public static RoleModel getRoleModelFromEntity(RoleEntity roleEntity) {
		RoleModel roleModel = new RoleModel();
		roleModel.setId(roleEntity.getRoleId());

		roleModel.setName(roleEntity.getRolename());
		roleModel.setDescription(roleEntity.getDescription());
		roleModel.setEnabled(roleEntity.isEnabled());
		if (roleEntity.getBuilding() != null) {
			roleModel.setHubId(roleEntity.getBuilding().getBuildingId());
		}
		List<PrivilegeModel> privilegeModels = new ArrayList<>();

		for (RolePrivilegeEntity rolePrivilegeEntity : roleEntity.getRolePrivileges()) {
			PrivilegeModel privilegeModel = new PrivilegeModel();
			privilegeModel.setId(rolePrivilegeEntity.getModulePrivilege().getId());
			privilegeModel.setName(rolePrivilegeEntity.getModulePrivilege().getIdentifierName());
			privilegeModel.setDisplayName(rolePrivilegeEntity.getModulePrivilege().getPrivilege().getDisplayName());
			privilegeModel.setValue(rolePrivilegeEntity.getEnabled());
			privilegeModels.add(privilegeModel);
		}
		roleModel.setPrivilegeModels(privilegeModels);
		List<UserModel> userModels = new ArrayList<>();
		for (UserRoleEntity userRole : roleEntity.getUserRoles()) {
			UserModel userModel = new UserModel();
			userModel.setId(userRole.getUsers().getUserId());
			userModel.setUsername(userRole.getUsers().getUsername());
			userModel.setEmail(userRole.getUsers().getEmail());
			userModel.setPhone(userRole.getUsers().getPhone());
			userModels.add(userModel);
		}
		roleModel.setUserModels(userModels);
		return roleModel;
	}

	public static RoleUser getRolesOfUserFromUserEntity(UserEntity userEntity) {
		RoleUser roleUser = new RoleUser();
		Set<PrivilegeModel> privilegeModels = new HashSet<>();
		for (UserRoleEntity userRoleEntity : userEntity.getUsersRoles()) {
			privilegeModels.addAll(getPrivilegesModelFromRole(userRoleEntity.getRoles()));
		}
		roleUser.setPrivilegeModels(privilegeModels);
		return roleUser;
	}

	private static List<PrivilegeModel> getPrivilegesModelFromRole(RoleEntity roleEntity) {
		List<PrivilegeModel> privilegeModels = new ArrayList<>();
		for (RolePrivilegeEntity rolePrivilegeEntity : roleEntity.getRolePrivileges()) {
			PrivilegeModel privilegeModel = new PrivilegeModel();
			privilegeModel.setId(rolePrivilegeEntity.getModulePrivilege().getId());
			privilegeModel.setName(rolePrivilegeEntity.getModulePrivilege().getIdentifierName());
			privilegeModel.setDisplayName(rolePrivilegeEntity.getModulePrivilege().getPrivilege().getDisplayName());
			privilegeModel.setValue(rolePrivilegeEntity.getEnabled());
			privilegeModels.add(privilegeModel);
		}
		return privilegeModels;
	}

	public static List<ActiveRole> getActiveRolesFromRoleEntities(List<RoleEntity> roleEntities) {
		List<ActiveRole> activeRoles = new ArrayList<>();
		for (RoleEntity roleEntity : roleEntities) {
			ActiveRole activeRole = new ActiveRole();
			activeRole.setId(roleEntity.getRoleId());
			activeRole.setName(roleEntity.getRolename());
			activeRoles.add(activeRole);
		}
		return activeRoles;
	}

	public static List<RoleDto> getRolesDtofromUserRoleEntities(List<UserRoleEntity> userRoleEntities) {
		List<RoleDto> roleDtos = new ArrayList<>();
		for (UserRoleEntity userRoleEntity : userRoleEntities) {
			RoleDto roleDto = new RoleDto();
			RoleEntity roleEntity = userRoleEntity.getRoles();
			roleDto.setId(roleEntity.getRoleId());
			roleDto.setName(roleEntity.getRolename());
			roleDtos.add(roleDto);
		}
		return roleDtos;
	}

}
