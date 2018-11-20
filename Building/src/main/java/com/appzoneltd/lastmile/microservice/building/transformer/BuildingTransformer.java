package com.appzoneltd.lastmile.microservice.building.transformer;

import java.util.ArrayList;
import java.util.List;

import com.appzoneltd.lastmile.microservice.building.ServingArea.dao.ServingArealocation;
import com.appzoneltd.lastmile.microservice.building.entity.BuildingEntity;
import com.appzoneltd.lastmile.microservice.building.entity.BuildingServingAreaEntity;
import com.appzoneltd.lastmile.microservice.building.model.BuildingInfo;
import com.appzoneltd.lastmile.microservice.enums.EntityStatus;

public class BuildingTransformer {

	public static List<BuildingInfo> getBuildingInfosFromEntities(List<BuildingEntity> buildingEntities){
		List<BuildingInfo> buildingInfos = new ArrayList<>();
		for(BuildingEntity buildingEntity : buildingEntities){
			BuildingInfo buildingInfo = getBuildingInfoFromEntity(buildingEntity);
			buildingInfos.add(buildingInfo);
		}
		return buildingInfos;
	}
	
	public static BuildingInfo getBuildingInfoFromEntity(BuildingEntity buildingEntity){
		if(buildingEntity ==null)
			return null;
		BuildingInfo buildingInfo = new BuildingInfo();
		buildingInfo.setArea(buildingEntity.getArea());
		buildingInfo.setBuildingId(buildingEntity.getBuildingId());
		buildingInfo.setBuildingname(buildingEntity.getBuildingname());
		buildingInfo.setBuildingnumber(buildingEntity.getBuildingnumber());
		List<ServingArealocation> servingArealocations = new ArrayList<>();
		for(BuildingServingAreaEntity buildingServingAreaEntity : buildingEntity.getListOfBuildingServingArea()){
			ServingArealocation servingArealocation = new ServingArealocation();
			servingArealocation.setLatitude(buildingServingAreaEntity.getLatitude());
			servingArealocation.setLongitude(buildingServingAreaEntity.getLongitude());
			servingArealocations.add(servingArealocation);
		}
		buildingInfo.setBuildingServingArea(servingArealocations);
		if(buildingEntity.getBuildingType() !=null){
			buildingInfo.setBuildingTypeId(buildingEntity.getBuildingType().getBuildingTypeId());
			buildingInfo.setType(buildingEntity.getBuildingType().getType());
		}
		if(buildingEntity.getCity() !=null){
			buildingInfo.setCityId(buildingEntity.getCity().getCityId());
			buildingInfo.setCityNameAr(buildingEntity.getCity().getNameAr());
			buildingInfo.setCityNameEn(buildingEntity.getCity().getNameEn());
		}
		if(buildingEntity.getCountry() !=null){
			buildingInfo.setCountryId(buildingEntity.getCountry().getCountryId());
			buildingInfo.setCountryNameAr(buildingEntity.getCountry().getNameAr());
			buildingInfo.setCountryNameEn(buildingEntity.getCountry().getNameEn());
		}
		if(buildingEntity.getCountryCodes() !=null){
			buildingInfo.setCountryCode(buildingEntity.getCountryCodes().getIso3());
			buildingInfo.setCountryCodeId(buildingEntity.getCountryCodes().getId());
		}
		
		
	
		buildingInfo.setCreated(buildingEntity.getCreated());
		buildingInfo.setDescription(buildingEntity.getDescription());
		buildingInfo.setLatitude(buildingEntity.getLatitude());
		buildingInfo.setLongitude(buildingEntity.getLongitude());
		buildingInfo.setPhoneNumber(buildingEntity.getPhoneNumber());
		buildingInfo.setStatus(EntityStatus.valueOf(buildingEntity.getStatus()));
		buildingInfo.setStreet(buildingEntity.getStreet());
		
		buildingInfo.setVersion(buildingEntity.getVersion());
		buildingInfo.setWaselcode(buildingEntity.getWaselcode());
		return buildingInfo;
	}
}
