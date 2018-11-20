package com.appzoneltd.lastmile.microservice.ums.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.ums.dao.BuildingRepository;
import com.appzoneltd.lastmile.microservice.ums.entity.BuildingEntity;

@Service
@Qualifier("buildingService")
public class BuildingService {
	
	@Autowired 
	private BuildingRepository buildingRepository;
	
	public BuildingEntity getBuildingById(Long hubId){
		return buildingRepository.getBuildingById(hubId);
	}
	public List<BuildingEntity> getAllBuildings(){
		return buildingRepository.getAllBuilding();
	}
	
	public Message checkBuildingExist(Long hubId) {
		BuildingEntity buildingEntity = getBuildingById(hubId);
		if (buildingEntity == null) {
				return new Message(MessageType.ERROR, "name", "building not Exist");
				
		}
		return null;
	}
}
