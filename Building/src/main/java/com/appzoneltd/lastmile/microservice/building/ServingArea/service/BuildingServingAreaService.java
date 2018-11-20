package com.appzoneltd.lastmile.microservice.building.ServingArea.service;

import java.util.Collection;
import java.util.List;

import com.appzoneltd.lastmile.microservice.building.ServingArea.dao.BuildingServingAreaDto;
import com.appzoneltd.lastmile.microservice.building.ServingArea.dao.ServingArealocation;
import com.appzoneltd.lastmile.microservice.building.entity.BuildingEntity;
import com.appzoneltd.lastmile.microservice.building.model.Building;


public interface BuildingServingAreaService {

	Collection<BuildingServingAreaDto> findAllBuildingServingArea();
	
	Collection<BuildingServingAreaDto> findAllBuildingServingAreaExceptLocated(Long buildingId);
	
    boolean isIntersectedWithRegion(List<ServingArealocation> buildingServingAreaDto);
    
    boolean isBuildingLocationWithInServingArea(Building building , List<ServingArealocation> newLocations);
    
    boolean isPointLocationWithInBuildingsServingArea(ServingArealocation point,
			List<ServingArealocation> buildingLocations);
    
    public Long gettingPickRequestServingAreaHubBuilding(String latitude,String longitude);
    
    boolean isIntersectedWithRegionExceptDeterminedRegion(List<ServingArealocation> buildingServingAreaDto , Long buildingId) ;
	
}
