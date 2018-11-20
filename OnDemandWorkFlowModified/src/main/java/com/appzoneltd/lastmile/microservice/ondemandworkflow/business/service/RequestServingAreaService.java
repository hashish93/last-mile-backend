package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dao.BuildingServingAreaJpaRepository;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dto.BuildingServingAreaDto;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.dto.BuildingServingAreaLocationHolder;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity.BuildingServingAreaEntity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.model.BuildingPoint;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.model.BuildingPolygon;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.business.model.ServingArealocation;

@Service
public class RequestServingAreaService {

	@Autowired
	private BuildingServingAreaJpaRepository servingAreaJpaRepository;

	public Long gettingPickRequestServingAreaHubBuilding(String latitude, String longitudee) {

		Long buildingId = null;

		Collection<BuildingServingAreaDto> buildingServingAreaDtos = findAllBuildingServingArea();

		BuildingPoint pickUpLocationPoint = new BuildingPoint();
		pickUpLocationPoint.setLatitude(Double.parseDouble(latitude));
		pickUpLocationPoint.setLongitude(Double.parseDouble(longitudee));

		for (BuildingServingAreaDto buildingServingAreaDto : buildingServingAreaDtos) {
			List<BuildingPoint> areaPoints = new ArrayList<BuildingPoint>();
			for (ServingArealocation servingArealocation : buildingServingAreaDto.getLocations()) {
				double latitide = Double.parseDouble(servingArealocation.getLatitude());
				double longitude = Double.parseDouble(servingArealocation.getLongitude());
				areaPoints.add(new BuildingPoint(latitide, longitude));
			}
			// Create Polygon from Points
			BuildingPolygon buildingPolygon = new BuildingPolygon(areaPoints);
			if (buildingPolygon.pointInside(pickUpLocationPoint)) {
				buildingId = buildingServingAreaDto.getBuildingId();
				break;
			}
		}

		return buildingId;
	}

	public Collection<BuildingServingAreaDto> findAllBuildingServingArea() {
		List<BuildingServingAreaLocationHolder> buildingServingAreas = null;

		buildingServingAreas = getAllActiveServingAreas();

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

	private List<BuildingServingAreaLocationHolder> getAllActiveServingAreas() {

		List<BuildingServingAreaEntity> buildingServingAreaEntities = (List<BuildingServingAreaEntity>) servingAreaJpaRepository
				.findAllActiveServingAreas();
		List<BuildingServingAreaLocationHolder> buildingServingAreaLocationHolders = new ArrayList<>();

		for (BuildingServingAreaEntity buildingServingAreaEntity : buildingServingAreaEntities) {
			BuildingServingAreaLocationHolder servingAreaLocationHolder = new BuildingServingAreaLocationHolder();
			servingAreaLocationHolder.setBuildingId(buildingServingAreaEntity.getBuilding().getBuildingId());
			servingAreaLocationHolder.setLatitude(buildingServingAreaEntity.getLatitude());
			servingAreaLocationHolder.setLongitude(buildingServingAreaEntity.getLongitude());
			buildingServingAreaLocationHolders.add(servingAreaLocationHolder);
		}
		return buildingServingAreaLocationHolders;
	}

}
