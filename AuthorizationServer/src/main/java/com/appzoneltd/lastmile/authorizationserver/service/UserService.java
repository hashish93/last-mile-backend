package com.appzoneltd.lastmile.authorizationserver.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.authorizationserver.dao.BuildingDao;
import com.appzoneltd.lastmile.authorizationserver.dao.UserDao;
import com.appzoneltd.lastmile.authorizationserver.dto.AuthorityDTO;
import com.appzoneltd.lastmile.authorizationserver.entity.BuildingEntity;
import com.appzoneltd.lastmile.authorizationserver.entity.ModulePrivilegeEntity;
import com.appzoneltd.lastmile.authorizationserver.entity.RoleEntity;
import com.appzoneltd.lastmile.authorizationserver.entity.RolePrivilegeEntity;
import com.appzoneltd.lastmile.authorizationserver.entity.UserEntity;
import com.appzoneltd.lastmile.authorizationserver.entity.UserHubEntity;
import com.appzoneltd.lastmile.authorizationserver.entity.UserRoleEntity;

@Service
@Qualifier("userService")
public class UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private BuildingDao buildingDao;

	public UserEntity findByUserId(Long userId) {
		return userDao.findByUserId(userId);
	}

	public UserEntity findByIdentifier(String identifier) {
		return userDao.findByIdentifier(identifier);
	}

	public Set<AuthorityDTO> getUserAuthorities(Long userId) {
		Set<AuthorityDTO> authorities = new HashSet<AuthorityDTO>();
		UserEntity userEntity = findByUserId(userId);
		if (userEntity.getUsersRoles() != null) {
			for (UserRoleEntity userRoleEntity : userEntity.getUsersRoles()) {
				if (userRoleEntity.getRoles().isEnabled()) {
					authorities.addAll(getRoleAuthorities(userRoleEntity.getRoles()));
				}
			}
		}
		return authorities;
	}

	private Set<AuthorityDTO> getRoleAuthorities(RoleEntity roleEntity) {
		Set<AuthorityDTO> authorities = new HashSet<AuthorityDTO>();
		for (RolePrivilegeEntity rolePrivilegeEntity : roleEntity.getRolePrivileges()) {
			ModulePrivilegeEntity modulePrivilege = rolePrivilegeEntity.getModulePrivilege();
			if (modulePrivilege != null) {
				AuthorityDTO authorityDTO = new AuthorityDTO();
				authorityDTO.setId(modulePrivilege.getId());
				authorityDTO.setAuthority(modulePrivilege.getIdentifierName());
				authorities.add(authorityDTO);
			}
		}
		return authorities;
	}

	public Long getUserHub(UserEntity userEntity) {
		if (userEntity.getUserHubs() != null && userEntity.getUserHubs().size() > 0) {
			return userEntity.getUserHubs().get(0).getBuilding().getBuildingId();
		}
		return null;
	}

	public List<Long> getUserHubList(UserEntity userEntity) {

		List<Long> hubs = new ArrayList<>();
		if (userEntity.getUserHubs() != null && userEntity.getUserHubs().size() > 0) {
			for (UserHubEntity userHubEntity : userEntity.getUserHubs()) {
				hubs.add(userHubEntity.getBuilding().getBuildingId());
			}
		}
		return hubs;
	}

	public List<Long> getAllHubList() {
		List<BuildingEntity> buildingEntities = buildingDao.getAllBuilding();
		List<Long> hubs = new ArrayList<>();
		for (BuildingEntity buildingEntity : buildingEntities) {
			hubs.add(buildingEntity.getBuildingId());
		}
		return hubs;
	}

}
