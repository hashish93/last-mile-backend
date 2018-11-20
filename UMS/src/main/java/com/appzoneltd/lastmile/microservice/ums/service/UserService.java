package com.appzoneltd.lastmile.microservice.ums.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ums.dao.UsersRepository;
import com.appzoneltd.lastmile.microservice.ums.dto.HubDto;
import com.appzoneltd.lastmile.microservice.ums.dto.RoleDto;
import com.appzoneltd.lastmile.microservice.ums.dto.UserDto;
import com.appzoneltd.lastmile.microservice.ums.dto.UserModel;
import com.appzoneltd.lastmile.microservice.ums.dto.UserRoleAssignDTO;
import com.appzoneltd.lastmile.microservice.ums.dto.UserRoleBulkAssignDTO;
import com.appzoneltd.lastmile.microservice.ums.entity.BuildingEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.RoleEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.UserEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.UserHubEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.UserRoleEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.UserRoleEntityKey;
import com.appzoneltd.lastmile.microservice.ums.transformer.RoleTransformer;
import com.appzoneltd.lastmile.microservice.ums.transformer.UserTransformer;

@Service
@Qualifier("userService")
public class UserService {

	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private PrincipalService principalService;

	@Autowired
	private BuildingService buildingService;

	public UserEntity getUserById(Long userId) {
		return usersRepository.findActiveUserById(userId);
	}

	public List<UserEntity> getUserEntitiesFromUserModels(List<UserModel> userModels) {
		List<UserEntity> userEntities = new ArrayList<>();
		for (UserModel userModel : userModels) {
			if (userModel.getId() != null) {
				UserEntity userEntity = getUserById(userModel.getId());
				if (userEntity != null) {
					userEntities.add(userEntity);
				}
			}
		}
		return userEntities;
	}

	public boolean isUserRelatedToSameHub(Long userId, Long hubId) {
		UserEntity userEntity = getUserById(userId);
		if (userEntity != null) {
			for (UserHubEntity userHubEntity : userEntity.getUserHubs()) {
				if (hubId != null && userHubEntity.getBuilding().getBuildingId() != null) {
					if (userHubEntity.getBuilding().getBuildingId() == hubId) {
						return true;
					}
				}

			}
		}

		return false;
	}

	public List<UserDto> searchUserByName(String name, Long hubId,Principal principal) {
		List<UserEntity> userEntities= new ArrayList<>();
		List<Long> hubs = new ArrayList<>();
		if (principalService.isSuperAdmin(principal)) {
			if(hubId !=null){
				hubs.add(hubId);
			}else{
				List<BuildingEntity>buildingEntities = buildingService.getAllBuildings();
				for(BuildingEntity buildingEntity : buildingEntities){
					hubs.add(buildingEntity.getBuildingId());
				}
			}
			userEntities = usersRepository.searchUserByNameWithAdmin(name, hubs);	
		} else {
			if("SUPERVISOR".equalsIgnoreCase(principalService.getUserType(principal))){
				if(hubId !=null){
					hubs.add(hubId);
				}else{
					hubs.addAll(principalService.getHubs(principal));
				}
			}else{
				hubs.addAll(principalService.getHubs(principal));
			}
			userEntities = usersRepository.searchUserByNameWithUser(name, hubs);
		}
		
		return UserTransformer.getUsersDtoFromUserEntities(userEntities);
	}

	public UserDto getUserByIdentifier(String identifier) {
		UserEntity userEntity = usersRepository.findByUserIdentifier(identifier);
		return UserTransformer.getUserDtoFromUserEntity(userEntity);
	}

	public UserDto getUserDtoById(Long userId) {
		UserEntity userEntity = getUserById(userId);
		return UserTransformer.getUserDtoFromUserEntity(userEntity);
	}

	@Transactional
	public UserEntity assignUserForRoles(UserRoleAssignDTO userRoleAssignDTO) {
		return assignManyRolesForOneUser(userRoleAssignDTO.getUserId(), userRoleAssignDTO.getRoleDtos());
	}

	@Transactional
	public List<UserEntity> assignManyUserForManyRoles(UserRoleBulkAssignDTO userRoleBulkAssignDTO) {
		List<UserEntity> users = new ArrayList<>();
		for (UserDto userDto : userRoleBulkAssignDTO.getUsers()) {
			UserEntity userEntity = assignManyRolesForOneUser(userDto.getId(), userRoleBulkAssignDTO.getRoles());
			if (userEntity != null) {
				users.add(userEntity);
			}
		}
		return users;
	}

	public UserEntity assignManyRolesForOneUser(Long userId, List<RoleDto> rolesDTOs) {
		UserEntity userEntity = getUserById(userId);
		if (userEntity != null) {
			userRoleService.deleteBulk(userEntity.getUsersRoles());
			userEntity.setUsersRoles(null);
			for (RoleDto roleDto : rolesDTOs) {
				userEntity = assignRoleForUser(userEntity, roleDto.getId());
			}
		}
		return userEntity;
	}

	public UserEntity assignRoleForUser(UserEntity userEntity, Long roleId) {
		RoleEntity roleEntity = roleService.getRoleById(roleId);
		if (roleEntity != null && roleEntity.getBuilding() != null) {
			if (isUserRelatedToSameHub(userEntity.getUserId(), roleEntity.getBuilding().getBuildingId())) {
				List<UserRoleEntity> userRoleEntities = new ArrayList<>();
				UserRoleEntity userRoleEntity = new UserRoleEntity();
				UserRoleEntityKey userRoleEntityKey = new UserRoleEntityKey();
				userRoleEntityKey.setUserId(userEntity.getUserId());
				userRoleEntityKey.setRoleId(roleEntity.getRoleId());
				userRoleEntity.setCompositePrimaryKey(userRoleEntityKey);
				userRoleEntity.setUsers(userEntity);
				userRoleEntity.setRoles(roleEntity);
				userRoleEntity.setCreated(new Date());
				userRoleEntity.setVersion(0L);
				userRoleService.saveOrUpdate(userRoleEntity);
				userRoleEntities.add(userRoleEntity);
				userEntity.setUsersRoles(userRoleEntities);
				return userEntity;
			}
		}
		return userEntity;
	}

	public List<Long> chkUserType(UserRoleBulkAssignDTO userRoleBulkAssignDTO) {
		List<Long> messages = new ArrayList<>();
		for (UserDto userDto : userRoleBulkAssignDTO.getUsers()) {
			UserEntity chkUserType = getUserById(userDto.getId());
			if ("DRIVER".equals(chkUserType.getUserType().getName())) {
				messages.add(userDto.getId());
			}
		}
		return messages;
	}

	public List<RoleDto> getAssignedRoleForUser(Long userId) {
		UserEntity userEntity = usersRepository.findUserWithActiveRolesById(userId);
		if (userEntity != null) {
			return RoleTransformer.getRolesDtofromUserRoleEntities(userEntity.getUsersRoles());
		}
		return null;
	}

	public List<HubDto> getUserHubs(Principal principal) {
		List<HubDto> hubDtos = new ArrayList<>();
		if (principalService.isSuperAdmin(principal)) {
			List<BuildingEntity> buildingEntities = buildingService.getAllBuildings();
			hubDtos = getHubDtosFromEntities(buildingEntities);
		} else {
			List<Long> hubs = principalService.getHubs(principal);
			hubDtos = getHubDtosFromاHubIds(hubs);
		}
		return hubDtos;
	}

	private List<HubDto> getHubDtosFromEntities(List<BuildingEntity> buildingEntities) {
		List<HubDto> hubDtos = new ArrayList<>();
		for (BuildingEntity buildingEntity : buildingEntities) {
			HubDto hubDto = new HubDto();
			hubDto.setId(buildingEntity.getBuildingId());
			hubDto.setName(buildingEntity.getBuildingname());
			hubDto.setArea(buildingEntity.getArea());
			hubDto.setStreet(buildingEntity.getStreet());
			hubDto.setBuildingNumber(buildingEntity.getBuildingnumber());
			hubDto.setPhone(buildingEntity.getPhoneNumber());
			hubDto.setLatitude(buildingEntity.getLatitude());
			hubDto.setLongitude(buildingEntity.getLongitude());
			hubDtos.add(hubDto);
		}
		return hubDtos;
	}

	private List<HubDto> getHubDtosFromاHubIds(List<Long> hubs) {
		List<HubDto> hubDtos = new ArrayList<>();
		for (Long hubId : hubs) {
			BuildingEntity buildingEntity = buildingService.getBuildingById(hubId);
			if (buildingEntity != null) {
				HubDto hubDto = new HubDto();
				hubDto.setId(hubId);
				hubDto.setName(buildingEntity.getBuildingname());
				hubDto.setArea(buildingEntity.getArea());
				hubDto.setStreet(buildingEntity.getStreet());
				hubDto.setBuildingNumber(buildingEntity.getBuildingnumber());
				hubDto.setPhone(buildingEntity.getPhoneNumber());
				hubDto.setLatitude(buildingEntity.getLatitude());
				hubDto.setLongitude(buildingEntity.getLongitude());
				hubDtos.add(hubDto);
			}
		}
		return hubDtos;
	}
}
