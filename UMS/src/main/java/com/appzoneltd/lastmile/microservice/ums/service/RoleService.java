package com.appzoneltd.lastmile.microservice.ums.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.ums.dao.RolesRepository;
import com.appzoneltd.lastmile.microservice.ums.dto.ActiveRole;
import com.appzoneltd.lastmile.microservice.ums.dto.RoleInfomation;
import com.appzoneltd.lastmile.microservice.ums.dto.RoleModel;
import com.appzoneltd.lastmile.microservice.ums.dto.RoleUser;
import com.appzoneltd.lastmile.microservice.ums.entity.BuildingEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.ModulePrivilegeEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.RoleEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.RolePrivilegeEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.UserEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.UserRoleEntity;
import com.appzoneltd.lastmile.microservice.ums.entity.UserRoleEntityKey;
import com.appzoneltd.lastmile.microservice.ums.transformer.RoleTransformer;
import com.appzoneltd.lastmile.microservice.utility.Utils;

@Service
@Qualifier("roleService")
public class RoleService {

	@Autowired
	private RolesRepository rolesRepository;

	@Autowired
	private ModulePrivilegeService modulePrivilegeService;

	@Autowired
	private RolePrivilegeService rolePrivilegeService;

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private UserService userService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private PrincipalService principalService;

	public RoleEntity getRoleById(Long roleId) {
		return rolesRepository.findOne(roleId);
	}

	public List<RoleEntity> getAllHubRoles(Long hubId) {
		return rolesRepository.findAllHubRoles(hubId);
	}

	public List<RoleEntity> getAllRoles() {
		return rolesRepository.findAllRoles();
	}

	public List<RoleEntity> getAllSharedRoles() {
		List<RoleEntity> sharedRoles = rolesRepository.findAllSharedRoles();
		List<RoleEntity> editedRoles = new ArrayList<>();
		for (RoleEntity roleEntity : sharedRoles) {
			if (roleEntity.getRoleId() != 1) {
				roleEntity.setEditable(false);
				editedRoles.add(roleEntity);
			}
		}
		return editedRoles;
	}

	public List<RoleInfomation> getAllHubRolesInformation(Principal principal) {
		List<RoleEntity> roleEntities = new ArrayList<>();
		if (principal != null) {
			if (principalService.isSuperAdmin(principal)) {
				roleEntities = getSuperAdminRoles();
			} else {
				roleEntities = getHubAdminRoles(principal);
			}
		}
		List<RoleInfomation> roleInfomations = new ArrayList<>();

		if (roleEntities != null && roleEntities.size() > 0) {
			roleInfomations = RoleTransformer.getRoleInformationFromEntities(roleEntities);
		}

		return roleInfomations;
	}

	private List<RoleEntity> getHubAdminRoles(Principal principal) {
		List<RoleEntity> roleEntities = new ArrayList<>();
		roleEntities.addAll(getAllSharedRoles());
		List<Long> hubIds = principalService.getHubs(principal);
		for (Long hubId : hubIds) {
			roleEntities.addAll(getAllHubRoles(hubId));
		}
		return roleEntities;
	}

	private List<RoleEntity> getSuperAdminRoles() {
		List<RoleEntity> roleEntities = new ArrayList<>();
		List<RoleEntity> superAdminRoles = new ArrayList<>();
		roleEntities = getAllRoles();
		for (RoleEntity roleEntity : roleEntities) {
			if (roleEntity.getRoleId() == 1) {
				roleEntity.setEditable(false);
			} else {
				roleEntity.setEditable(true);
			}
			superAdminRoles.add(roleEntity);
		}
		return superAdminRoles;
	}

	@Transactional
	public RoleEntity saveOrUpdate(RoleEntity roleEntity) {
		return rolesRepository.save(roleEntity);
	}

	public RoleInfomation activateOrDeActivate(Principal principal, Long roleId, boolean enabled) {
		RoleEntity roleEntity = null;
		if (roleId != 1) {
			if (principalService.isSuperAdmin(principal)) {
				roleEntity = rolesRepository.findOne(roleId);
			} else {
				roleEntity = rolesRepository.findRoleById(roleId);
			}
		}
		RoleInfomation roleInfomation = null;
		if (roleEntity != null) {
			roleEntity.setEnabled(enabled);
			saveOrUpdate(roleEntity);
			roleInfomation = RoleTransformer.getRoleInformationFromEntity(roleEntity);
		}
		return roleInfomation;
	}

	public RoleEntity findRoleByName(String roleName) {
		return rolesRepository.findByRolename(roleName);
	}

	public List<Message> checkRoleNameExist(RoleModel roleModel) {
		List<Message> messages = new ArrayList<>();
		RoleEntity roleEntity = findRoleByName(roleModel.getName());
		if (roleModel.getId() == null) {
			if (roleEntity != null) {
				Message message = new Message(MessageType.ERROR, "name", "Role Name Already Exist");
				messages.add(message);
			}
		} else {
			if (roleEntity != null) {
				if (roleEntity.getRoleId().doubleValue() != roleModel.getId().doubleValue()) {
					Message message = new Message(MessageType.ERROR, "name", "Role Name Already Exist");
					messages.add(message);
				}
			}
		}
		return messages;
	}

	public RoleEntity findFullRole(Long roleId) {
		return rolesRepository.findRoleById(roleId);
	}

	private RoleEntity saveRole(RoleModel roleModel, String userType) {
		RoleEntity roleEntity = new RoleEntity();
		roleEntity.setRolename(roleModel.getName());
		roleEntity.setDescription(roleModel.getDescription());
		roleEntity.setCreated(new Date());
		roleEntity.setVersion(0L);

		roleEntity.setEnabled(roleModel.isEnabled());
		roleEntity.setRoleId(Utils.generateUUID());
		// SAVE BUILDING
		if ("SUPER_ADMIN".equalsIgnoreCase(userType)) {
			if (roleModel.getHubId() != null) {
				roleEntity.setEditable(true);
			} else {
				roleEntity.setEditable(false);
			}
		} else {
			roleEntity.setEditable(true);
		}

		if (roleModel.getHubId() != null) {
			BuildingEntity buildingEntity = buildingService.getBuildingById(roleModel.getHubId());
			roleEntity.setBuilding(buildingEntity);
		}

		RoleEntity saveRoleEntity = saveOrUpdate(roleEntity);

		List<ModulePrivilegeEntity> modulePrivilegeEntities = modulePrivilegeService
				.transformPriviligesModelsToEntities(roleModel.getPrivilegeModels());
		for (ModulePrivilegeEntity modulePrivilegeEntity : modulePrivilegeEntities) {
			RolePrivilegeEntity rolePrivilegeEntity = new RolePrivilegeEntity();
			rolePrivilegeEntity.setId(Utils.generateBigUUID());
			rolePrivilegeEntity.setEnabled(true);
			rolePrivilegeEntity.setModulePrivilege(modulePrivilegeEntity);
			rolePrivilegeEntity.setRoles(saveRoleEntity);
			rolePrivilegeEntity.setVersion(0L);
			rolePrivilegeService.save(rolePrivilegeEntity);
		}

		// SETTING USERS
		List<UserEntity> userEntities = userService.getUserEntitiesFromUserModels(roleModel.getUserModels());
		for (UserEntity user : userEntities) {
			if(saveRoleEntity.getBuilding() !=null){
				if (userService.isUserRelatedToSameHub(user.getUserId(), saveRoleEntity.getBuilding().getBuildingId())) {
					saveUserRoleEntity(saveRoleEntity , user);
				}
			}else{
				saveUserRoleEntity(saveRoleEntity , user);
			}
			
		}
		return saveRoleEntity;
	}

	@Transactional
	private UserRoleEntity saveUserRoleEntity(RoleEntity roleEntity , UserEntity userEntity){
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
		return userRoleEntity;
	}
	private RoleEntity updateRole(RoleEntity roleEntity, RoleModel roleModel) {
		roleEntity.setRolename(roleModel.getName());
		roleEntity.setDescription(roleModel.getDescription());
		rolePrivilegeService.deleteBulk(roleEntity.getRolePrivileges());
		roleEntity.setRolePrivileges(null);
		if (roleModel.getHubId() != null) {
			BuildingEntity buildingEntity = buildingService.getBuildingById(roleModel.getHubId());
			roleEntity.setBuilding(buildingEntity);
		}
		RoleEntity saveRoleEntity = saveOrUpdate(roleEntity);
		// save new ModulePrivilegeEntities
		List<ModulePrivilegeEntity> modulePrivilegeEntities = modulePrivilegeService
				.transformPriviligesModelsToEntities(roleModel.getPrivilegeModels());
		for (ModulePrivilegeEntity modulePrivilegeEntity : modulePrivilegeEntities) {
			RolePrivilegeEntity rolePrivilegeEntity = new RolePrivilegeEntity();
			rolePrivilegeEntity.setId(Utils.generateBigUUID());
			rolePrivilegeEntity.setEnabled(true);
			rolePrivilegeEntity.setModulePrivilege(modulePrivilegeEntity);
			rolePrivilegeEntity.setRoles(saveRoleEntity);
			
			if(rolePrivilegeEntity.getModulePrivilege() !=null){
				System.out.println("id "+rolePrivilegeEntity.getId()+" module privilige id "+rolePrivilegeEntity.getModulePrivilege().getId()+" Role id "+saveRoleEntity.getRoleId());
			}
			rolePrivilegeEntity.setVersion(0L);
			rolePrivilegeService.save(rolePrivilegeEntity);
		}
		userRoleService.deleteBulk(saveRoleEntity.getUserRoles());
		saveRoleEntity.setUserRoles(null);
		// SETTING USERS
		List<UserEntity> userEntities = userService.getUserEntitiesFromUserModels(roleModel.getUserModels());
		for (UserEntity user : userEntities) {
			if(saveRoleEntity.getBuilding() !=null){
				if (userService.isUserRelatedToSameHub(user.getUserId(), saveRoleEntity.getBuilding().getBuildingId())) {
					setUserRoleEntity(user, saveRoleEntity);
				}
			}
			else{
				setUserRoleEntity(user, saveRoleEntity);
			}
		}
		return saveRoleEntity;
	}

	@Transactional
	private void setUserRoleEntity(UserEntity userEntity , RoleEntity roleEntity){
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
	}
	@Transactional
	public RoleEntity saveOrUpdateRole(RoleModel roleModel, String userType) {
		RoleEntity roleEntity = findFullRole(roleModel.getId());
		if (roleEntity == null) {
			saveRole(roleModel, userType);
		} else {
			updateRole(roleEntity, roleModel);
		}
		return roleEntity;
	}

	public RoleModel getRoleModelById(Long roleId, boolean isSuperAdmin) {
		RoleEntity roleEntity = null;
		if (isSuperAdmin) {
			roleEntity = rolesRepository.findOne(roleId);
		} else {
			roleEntity = findFullRole(roleId);
		}

		if (roleEntity != null) {
			return RoleTransformer.getRoleModelFromEntity(roleEntity);
		}
		return null;
	}

	public RoleUser getRoleUserByUserId(Long userId) {
		UserEntity userEntity = userService.getUserById(userId);
		if (userEntity != null) {
			return RoleTransformer.getRolesOfUserFromUserEntity(userEntity);
		}
		return null;
	}

	public List<ActiveRole> getAllActiveRolesForHubs(List<Long> hubs,Principal principal) {

		List<RoleEntity> roleEntities = new ArrayList<>();
		roleEntities.addAll(getAllSharedRoles());
		Set<Long> allHubs = new HashSet<>();
		if (hubs != null && hubs.size()>0) {
			allHubs.addAll(hubs);
		}else{
			List<Long> userHubs =  principalService.getHubs(principal);
			allHubs.addAll(userHubs);
		}
		for (Long hubId : allHubs) {
			List<RoleEntity> hubRoleEntities = rolesRepository.findAllActiveRoles(hubId);
			roleEntities.addAll(hubRoleEntities);
		}

		if (roleEntities.size() > 0) {
			return RoleTransformer.getActiveRolesFromRoleEntities(roleEntities);
		}
		return new ArrayList<>();
	}

}
