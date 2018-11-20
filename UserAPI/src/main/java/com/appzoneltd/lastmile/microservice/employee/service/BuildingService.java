package com.appzoneltd.lastmile.microservice.employee.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.employee.dao.BuildingRepository;
import com.appzoneltd.lastmile.microservice.employee.dao.UserHubRepository;
import com.appzoneltd.lastmile.microservice.employee.dao.UserRepository;
import com.appzoneltd.lastmile.microservice.employee.dto.HubDto;
import com.appzoneltd.lastmile.microservice.employee.entity.BuildingEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.UserEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.UserHubEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.UserTypeEntity;

@Service
public class BuildingService {

	@Autowired
	private UserHubRepository userHubRepository;

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private UserTypeService userTypeService;
	
	@Autowired
	private PrincipalService principalService;
	
	@Autowired
	private UserRepository userRepository;

	public void deleteBulk(List<UserHubEntity> userHubEntities) {
		for (UserHubEntity userHubEntity : userHubEntities) {
			userHubRepository.delete(userHubEntity);
		}
	}

	public BuildingEntity getBuildingById(Long hubId) {
		return buildingRepository.getBuildingById(hubId);
	}

	public List<BuildingEntity> getAllBuilding() {
		return buildingRepository.getAllBuilding();
	}

	public List<BuildingEntity> getAllUnAssignedBuilding() {
		List<BuildingEntity> buildingEntities = getAllBuilding();
		List<BuildingEntity> unAssignedHubs = new ArrayList<>();
		for (BuildingEntity buildingEntity : buildingEntities) {
			if (!isHubAssignedToAdmin(buildingEntity)) {
				unAssignedHubs.add(buildingEntity);
			}
		}
		return unAssignedHubs;
	}

	private boolean isHubAssignedToAdmin(BuildingEntity buildingEntity) {
		if (buildingEntity.getListOfUserHub() != null) {
			for (UserHubEntity userHubEntity : buildingEntity.getListOfUserHub()) {
				UserTypeEntity userTypeEntity = userHubEntity.getUser().getUserType();
				if ((userTypeEntity != null) && (userHubEntity.getUser() !=null) && ("HUB_ADMIN".equals(userTypeEntity.getName())) && userHubEntity.getUser().getEnabled()) {
					return true;
				}
			}
		}
		return false;
	}

	public List<HubDto> getAllBuildings(Long userTypeId, Long userId ,Principal principal) {
		UserTypeEntity userTypeEntity = userTypeService.getUserTypeEntity(userTypeId);
		List<BuildingEntity> buildingEntities = new ArrayList<>();
		if (userTypeEntity != null) {
			if ("HUB_ADMIN".equalsIgnoreCase(userTypeEntity.getName())) {
				buildingEntities = getAllUnAssignedBuilding();
				if(userId !=null){
					UserEntity userEntity = userRepository.findActiveUserById(userId);
					if(userEntity.getUserHubs() != null && userEntity.getUserHubs().size()>0){
						buildingEntities.add(userEntity.getUserHubs().get(0).getBuilding());
					}
				}
				
			} else {
				if(principalService.isSuperAdmin(principal)){
					buildingEntities = getAllBuilding();
				}else{
					buildingEntities = getBuildingEntitiesFromIds(principalService.getHubs(principal));
				}
				
			}
			return getHubDtosFromEntities(buildingEntities);
		}
		return null;
	}

	public List<BuildingEntity> getBuildingEntitiesFromIds(List<Long> hubIds){
		List<BuildingEntity> buildingEntities = new ArrayList<>();
		if(hubIds !=null){
			for(long hubId : hubIds){
				BuildingEntity buildingEntity = getBuildingById(hubId);
				if(buildingEntity !=null){
					buildingEntities.add(buildingEntity);
				}
			}
		}
		return buildingEntities;
	}
	
	
	public List<HubDto> getHubDtosFromEntities(List<BuildingEntity> buildingEntities) {
		List<HubDto> hubDtos = new ArrayList<>();
		for (BuildingEntity buildingEntity : buildingEntities) {
			HubDto hubDto = new HubDto();
			hubDto.setId(buildingEntity.getBuildingId());
			hubDto.setName(buildingEntity.getBuildingname());
			hubDtos.add(hubDto);
		}
		return hubDtos;
	}

}
