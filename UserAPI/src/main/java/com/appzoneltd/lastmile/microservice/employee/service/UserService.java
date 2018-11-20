package com.appzoneltd.lastmile.microservice.employee.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.employee.dao.DriverRepository;
import com.appzoneltd.lastmile.microservice.employee.dao.UserHubRepository;
import com.appzoneltd.lastmile.microservice.employee.dao.UserRepository;
import com.appzoneltd.lastmile.microservice.employee.entity.BuildingEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.DriverEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.RoleEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.UserEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.UserHubEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.UserRoleEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.UserRoleEntityKey;
import com.appzoneltd.lastmile.microservice.employee.enums.UserEntityStatus;
import com.appzoneltd.lastmile.microservice.employee.model.Driver;
import com.appzoneltd.lastmile.microservice.employee.model.Employee;
import com.appzoneltd.lastmile.microservice.employee.model.Parameter;
import com.appzoneltd.lastmile.microservice.employee.transformer.UserTransformer;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.utility.Utils;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserRoleService userRoleService;

	@Autowired
	private PrincipalService principalService;

	@Autowired
	private UserHubRepository userHubRepository;

	@Autowired
	private BuildingService buildingService;

	@Autowired
	private DriverRepository driverRepository;

	public UserEntity getUserById(Long userId) {
		return userRepository.findOne(userId);
//		return userRepository.findActiveUserById(userId);
	}

	public Employee getEmployeeById(Long userId) {
		UserEntity userEntity = getUserById(userId);
		if (userEntity != null) {
			return UserTransformer.getEmployeeFromUserEntity(userEntity);
		}
		return null;
	}

	public Driver getDriverById(Long userId) {
		UserEntity userEntity = getUserById(userId);
		if (userEntity != null) {
			Driver driver = (Driver) UserTransformer.getEmployeeFromUserEntity(userEntity);
			DriverEntity driverEntity = driverRepository.findOne(driver.getUserId());
			if (driverEntity != null) {
				driver.setDrivingLicenseId(driverEntity.getDrivingLicenseId());
				if (driverEntity.getDrivingLicenseType() != null) {
					driver.setDrivingLicenseTypeId(driverEntity.getDrivingLicenseType().getDrivingLicenseTypeId());
				}
				driver.setDrivingLicenseExpDate(driverEntity.getDrivingLicenseExpDate());
			}
			return driver;
		}
		return null;
	}
	
	public List<Message> deleteUser(Long userId,String status){
		UserEntity userEntity = getUserById(userId);
		if(userEntity!=null){
			if("ACTIVE".equalsIgnoreCase(status)){
				userEntity.setStatus(UserEntityStatus.ACTIVE.getValue());
				userEntity.setEnabled(true);
			}else{
				userEntity.setStatus(UserEntityStatus.INACTIVE.getValue());
				userEntity.setEnabled(false);
			}
			
			userRepository.save(userEntity);
			return null;
		}
		List<Message> messages = new ArrayList<>();
		Message message = new Message(MessageType.ERROR, "userId", "userId not found");
		messages.add(message);
		return messages;
	}

	public List<Employee> getEmployeesByKey(Parameter parameter, Principal principal) {
		String key = parameter.getKey();
		List<UserEntity> userEntities = new ArrayList<>();
		if (key == null) {
			key = "";
		}
		PageRequest pageRequest = null;
		if (parameter.getPage() != 0) {
			pageRequest = new PageRequest(parameter.getPage() - 1, parameter.getMaxResult(),
					Sort.Direction.fromString(parameter.getOrderBy().getOrderBy()), "created");
		}

		if (principalService.isSuperAdmin(principal)) {
			userEntities = getUsersForAdminUser(key, pageRequest);
		} else {
			List<Long> hubs = principalService.getHubs(principal);
			userEntities = getUsersForNormalUser(key, pageRequest,hubs);
		}
		return UserTransformer.getEmployeesFromUserEntities(userEntities);

	}

	public List<UserEntity> getUsersForAdminUser(String key, PageRequest pageRequest) {
		return userRepository.findByKeyForAdmin("%" + key + "%", pageRequest);
	}

	public List<UserEntity> getUsersForNormalUser(String key, PageRequest pageRequest,List<Long> hubs) {
		
		return userRepository.findByKeyForUser("%" + key + "%" , hubs, pageRequest);
	}

	public boolean isUserRelatedToSameHub(Long userId, Long hubId) {
		UserEntity userEntity = getUserById(userId);
		if (userEntity != null && userEntity.getUserHubs() != null) {
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

	public boolean isRoleRelatedToSameHub(Long roleId, Long hubId) {
		RoleEntity roleEntity = roleService.getRoleById(roleId);
		if (roleEntity != null) {
			if (roleEntity.getBuilding() == null) {
				return true;
			}
			if (hubId != null && roleEntity.getBuilding().getBuildingId() != null) {
				if (roleEntity.getBuilding().getBuildingId() == hubId) {
					return true;
				}
			}
		}

		return false;
	}

	public UserEntity assignRolesForUser(UserEntity userEntity, List<Long> roles) {
		if (userEntity != null) {
			if (userEntity.getUsersRoles() != null) {
				userRoleService.deleteBulk(userEntity.getUsersRoles());
				userEntity.setUsersRoles(null);
			}
			for (Long roleId : roles) {
				userEntity = assignRoleForUser(userEntity, roleId);
			}
		}
		return userEntity;
	}

	public UserEntity assignRoleForUser(UserEntity userEntity, Long roleId) {
		RoleEntity roleEntity = roleService.getRoleById(roleId);
		if (roleEntity != null) {
			if (roleEntity.getBuilding() != null && userEntity.getUserHubs() != null) {
				for (UserHubEntity userHubEntity : userEntity.getUserHubs()) {
					if (isRoleRelatedToSameHub(roleId, userHubEntity.getBuilding().getBuildingId())) {
						userEntity = fillUserRoleEntity(userEntity, roleEntity);
					}
				}

			} else {
				userEntity = fillUserRoleEntity(userEntity, roleEntity);
			}

		}
		return userEntity;
	}

	public UserEntity fillUserRoleEntity(UserEntity userEntity, RoleEntity roleEntity) {
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

	public UserEntity assignHubsForUser(UserEntity userEntity, List<Long> hubs) {
		if (userEntity != null) {
			if (userEntity.getUserHubs() != null) {
				buildingService.deleteBulk(userEntity.getUserHubs());
				userEntity.setUserHubs(null);
			}
			for (Long hubId : hubs) {
				userEntity = assignHubForUser(userEntity, hubId);
			}
		}
		return userEntity;
	}

	public UserEntity assignHubForUser(UserEntity userEntity, Long hubId) {
		BuildingEntity buildingEntity = buildingService.getBuildingById(hubId);
		if (buildingEntity != null) {
			UserHubEntity userHubEntity = new UserHubEntity();
			userHubEntity.setId(Utils.generateUUID());
			userHubEntity.setBuilding(buildingEntity);
			userHubEntity.setUser(userEntity);

			userHubRepository.save(userHubEntity);
			List<UserHubEntity> userHubEntities = userEntity.getUserHubs();
			if (userHubEntities == null) {
				userHubEntities = new ArrayList<>();
			}
			userHubEntities.add(userHubEntity);
			userEntity.setUserHubs(userHubEntities);
			return userEntity;
		}
		return userEntity;
	}
}
