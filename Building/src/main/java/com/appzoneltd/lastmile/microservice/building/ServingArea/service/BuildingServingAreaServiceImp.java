package com.appzoneltd.lastmile.microservice.building.ServingArea.service;

import com.appzoneltd.lastmile.microservice.building.ServingArea.dao.*;
import com.appzoneltd.lastmile.microservice.building.ServingArea.model.BuildingPoint;
import com.appzoneltd.lastmile.microservice.building.ServingArea.model.BuildingPolygon;
import com.appzoneltd.lastmile.microservice.building.entity.BuildingEntity;
import com.appzoneltd.lastmile.microservice.building.entity.BuildingServingAreaEntity;
import com.appzoneltd.lastmile.microservice.building.model.Building;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service

public class BuildingServingAreaServiceImp implements BuildingServingAreaService {

	private final BuildingServingAreaFindAllRepositoryImp buildingServingAreaFindAllRepositoryImp;
	
	@Autowired
	private BuildingServingAreaFindAllExceptLocationRepositoryImp buildingServingAreaFindAllExceptLocationRepositoryImp ;

	@Autowired
	public BuildingServingAreaServiceImp(
			BuildingServingAreaFindAllRepositoryImp buildingServingAreaFindAllRepositoryImp) {

		this.buildingServingAreaFindAllRepositoryImp = buildingServingAreaFindAllRepositoryImp;
	}
	
	@Override
	public Collection<BuildingServingAreaDto> findAllBuildingServingArea() {
		List<BuildingServingAreaLocationHolder> buildingServingAreas = null;

		buildingServingAreas = buildingServingAreaFindAllRepositoryImp.query();

		Map<Long, BuildingServingAreaDto> map = new LinkedHashMap<>();

		for (BuildingServingAreaLocationHolder buildingInfo : buildingServingAreas) {
			if (map.containsKey(buildingInfo.getBuildingId())) {
				map.get(buildingInfo.getBuildingId()).getLocations()
						.add(new ServingArealocation(buildingInfo.getLatitude(), buildingInfo.getLongitude()));
			} else {
				List<ServingArealocation> locations = new ArrayList<>();
				ServingArealocation location = new ServingArealocation(buildingInfo.getLatitude(),
						buildingInfo.getLongitude());
				locations.add(location);
				map.put(buildingInfo.getBuildingId(),
						new BuildingServingAreaDto(buildingInfo.getBuildingId(), locations));
			}

		}

		return map.values();
	}

	
	
	@Override
	public Collection<BuildingServingAreaDto> findAllBuildingServingAreaExceptLocated(Long buildingId) {
		List<BuildingServingAreaLocationHolder> buildingServingAreas = null;

		buildingServingAreas = buildingServingAreaFindAllExceptLocationRepositoryImp.query(buildingId);

		Map<Long, BuildingServingAreaDto> map = new LinkedHashMap<>();

		for (BuildingServingAreaLocationHolder buildingInfo : buildingServingAreas) {
			if (map.containsKey(buildingInfo.getBuildingId())) {
				map.get(buildingInfo.getBuildingId()).getLocations()
						.add(new ServingArealocation(buildingInfo.getLatitude(), buildingInfo.getLongitude()));
			} else {
				List<ServingArealocation> locations = new ArrayList<>();
				ServingArealocation location = new ServingArealocation(buildingInfo.getLatitude(),
						buildingInfo.getLongitude());
				locations.add(location);
				map.put(buildingInfo.getBuildingId(),
						new BuildingServingAreaDto(buildingInfo.getBuildingId(), locations));
			}

		}

		return map.values();
		
	}
	
	
	
	/*
	 *  Check If Building Location With the Range Of Serving Area(non-Javadoc)
	 */
	
	@Override
	public boolean isBuildingLocationWithInServingArea(Building building, List<ServingArealocation> newLocations) {
		boolean Inside = false;

		// Define Building Location
		BuildingPoint locationPoints = new BuildingPoint();
		locationPoints.setLatitude(Double.parseDouble(building.getLatitude()));
		locationPoints.setLongitude(Double.parseDouble(building.getLongitude()));

		// Define Serving Area Points
		List<BuildingPoint> areaPoints = new ArrayList<BuildingPoint>();

		for (ServingArealocation servingArealocation : newLocations) {
			double latitide = Double.parseDouble(servingArealocation.getLatitude());
			double longitude = Double.parseDouble(servingArealocation.getLongitude());
			areaPoints.add(new BuildingPoint(latitide, longitude));
		} // end for Loop

		BuildingPolygon ServingAreaPolygon = new BuildingPolygon(areaPoints);

		if (ServingAreaPolygon.pointInside(locationPoints)) {

			Inside = true;
		}

		return Inside;
	}

	
	@Override
	public Long gettingPickRequestServingAreaHubBuilding(String latitude,String longitudee) {
		
		Long buildingId = null;
		
		Collection<BuildingServingAreaDto> buildingServingAreaDtos=findAllBuildingServingArea();
		
		// Generate 
		BuildingPoint pickUpLocationPoint= new BuildingPoint();
		pickUpLocationPoint.setLatitude(Double.parseDouble(latitude));
		pickUpLocationPoint.setLongitude(Double.parseDouble(longitudee));
		
		for(BuildingServingAreaDto buildingServingAreaDto:buildingServingAreaDtos){
			// Init List Of Points 
			List<BuildingPoint> areaPoints = new ArrayList<BuildingPoint>();
			/// Adding Points 
			for (ServingArealocation servingArealocation : buildingServingAreaDto.getLocations()) {
				double latitide = Double.parseDouble(servingArealocation.getLatitude());
				double longitude = Double.parseDouble(servingArealocation.getLongitude());
				areaPoints.add(new BuildingPoint(latitide, longitude));			
			} // end for Loop
			//Create Polygon from Points 
			BuildingPolygon buildingPolygon = new BuildingPolygon(areaPoints);
			if (buildingPolygon.pointInside(pickUpLocationPoint)) {
				buildingId = buildingServingAreaDto.getBuildingId();
				break;
			}//end if Condition 
		}//end Outer Loop 
		
		return buildingId;
	}
	
	
	
	
	@Override
	public boolean isIntersectedWithRegion(List<ServingArealocation> buildingServingAreaDto) {
		// Check if Is Found
		boolean isIntercepted = false;

		List<BuildingServingAreaDto> currentBuildingServingAreas = new ArrayList<BuildingServingAreaDto>(
				findAllBuildingServingArea());

		if (!currentBuildingServingAreas.isEmpty()) {
			for (BuildingServingAreaDto areaDto : currentBuildingServingAreas) {
				List<ServingArealocation> currentLocations = areaDto.getLocations();
				if (isIntersectedLocations(currentLocations, buildingServingAreaDto)) {
					isIntercepted = true;
					break;
				} // end
			} // end for Loop
		} // end if
			// return result
		return isIntercepted;
	}
	
	
	
	
	
	@Override
	public boolean isIntersectedWithRegionExceptDeterminedRegion(List<ServingArealocation> buildingServingAreaDto , Long buildingId) {
		// Check if Is Found
		boolean isIntercepted = false;

		List<BuildingServingAreaDto> currentBuildingServingAreas = new ArrayList<BuildingServingAreaDto>(findAllBuildingServingAreaExceptLocated(buildingId));

		if (!currentBuildingServingAreas.isEmpty()) {
			for (BuildingServingAreaDto areaDto : currentBuildingServingAreas) {
				List<ServingArealocation> currentLocations = areaDto.getLocations();
				if (isIntersectedLocations(currentLocations, buildingServingAreaDto)) {
					isIntercepted = true;
					break;
				} // end
			} // end for Loop
		} // end if
			// return result
		return isIntercepted;
	}
	
	
	
	
	

	private boolean isIntersectedLocations(List<ServingArealocation> currentLocations,
			List<ServingArealocation> newLocations) {
		// boolean to Return
		boolean isIntersected = false;
		// ponit of poly1
		List<BuildingPoint> currentPoints = new ArrayList<BuildingPoint>();
		List<BuildingPoint> newPoints = new ArrayList<BuildingPoint>();

		for (ServingArealocation servingArealocation : currentLocations) {
			double latitide = Double.parseDouble(servingArealocation.getLatitude());
			double longitude = Double.parseDouble(servingArealocation.getLongitude());
			currentPoints.add(new BuildingPoint(latitide, longitude));
		} // end for Loop

		for (ServingArealocation servingArealocation : newLocations) {
			double latitide = Double.parseDouble(servingArealocation.getLatitude());
			double longitude = Double.parseDouble(servingArealocation.getLongitude());
			newPoints.add(new BuildingPoint(latitide, longitude));
		} // end for Loop

		// define Polygn 1
		BuildingPolygon CurrentPolygon = new BuildingPolygon(currentPoints);
		BuildingPolygon newPolygon = new BuildingPolygon(newPoints);
		// ponit of poly2

		if (CurrentPolygon.isPoylgensItersected(newPolygon)) {
			isIntersected = true;
		}

		// return result
		return isIntersected;
	}

	@Override
	public boolean isPointLocationWithInBuildingsServingArea(ServingArealocation point,
			List<ServingArealocation> buildingLocations) {
		boolean Inside = false;

		// Define Building Location
		BuildingPoint locationPoints = new BuildingPoint();
		locationPoints.setLatitude(Double.parseDouble(point.getLatitude()));
		locationPoints.setLongitude(Double.parseDouble(point.getLongitude()));

		// Define Serving Area Points
		List<BuildingPoint> areaPoints = new ArrayList<BuildingPoint>();

		for (ServingArealocation servingArealocation : buildingLocations) {
			double latitide = Double.parseDouble(servingArealocation.getLatitude());
			double longitude = Double.parseDouble(servingArealocation.getLongitude());
			areaPoints.add(new BuildingPoint(latitide, longitude));
		} // end for Loop

		BuildingPolygon ServingAreaPolygon = new BuildingPolygon(areaPoints);

		if (ServingAreaPolygon.pointInside(locationPoints)) {

			Inside = true;
		}

		return Inside;

	
	}




	




}
