package com.appzoneltd.lastmile.microservice.building.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.building.ServingArea.dao.ServingArealocation;
import com.appzoneltd.lastmile.microservice.building.ServingArea.service.BuildingServingAreaService;
import com.appzoneltd.lastmile.microservice.building.dao.CalendarDao;
import com.appzoneltd.lastmile.microservice.building.dao.HubCalendarDao;
import com.appzoneltd.lastmile.microservice.building.dao.OutOfCoverageRequestRepository;
import com.appzoneltd.lastmile.microservice.building.dto.CalendarDto;
import com.appzoneltd.lastmile.microservice.building.entity.BuildingEntity;
import com.appzoneltd.lastmile.microservice.building.entity.BuildingServingAreaEntity;
import com.appzoneltd.lastmile.microservice.building.entity.BuildingTypeEntity;
import com.appzoneltd.lastmile.microservice.building.entity.CalendarEntity;
import com.appzoneltd.lastmile.microservice.building.entity.CityEntity;
import com.appzoneltd.lastmile.microservice.building.entity.CountryCodesEntity;
import com.appzoneltd.lastmile.microservice.building.entity.CountryEntity;
import com.appzoneltd.lastmile.microservice.building.entity.HubCalendarEntity;
import com.appzoneltd.lastmile.microservice.building.entity.HubConfigurationEntity;
import com.appzoneltd.lastmile.microservice.building.entity.OutOfCoverageRequestEntity;
import com.appzoneltd.lastmile.microservice.building.entity.SystemConfigEntity;
import com.appzoneltd.lastmile.microservice.building.entity.UserHubEntity;
import com.appzoneltd.lastmile.microservice.building.entity.VehicleEntity;
import com.appzoneltd.lastmile.microservice.building.model.Building;
import com.appzoneltd.lastmile.microservice.building.model.BuildingInfo;
import com.appzoneltd.lastmile.microservice.building.repo.BuildingRepository;
import com.appzoneltd.lastmile.microservice.building.repo.BuildingServingAreaRepository;
import com.appzoneltd.lastmile.microservice.building.repo.BuildingTypeRepository;
import com.appzoneltd.lastmile.microservice.building.repo.CityRepository;
import com.appzoneltd.lastmile.microservice.building.repo.CountryCodesRepository;
import com.appzoneltd.lastmile.microservice.building.repo.CountryRepository;
import com.appzoneltd.lastmile.microservice.building.repo.HubConfigurationRepository;
import com.appzoneltd.lastmile.microservice.building.repo.SystemConfigRepository;
import com.appzoneltd.lastmile.microservice.building.transformer.BuildingTransformer;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.utility.Utils;

@Service
public class BuildingService {

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private BuildingServingAreaService buildingServingAreaService;

	@Autowired
	private CountryCodesRepository countryCodeRepository;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private BuildingTypeRepository buildingTypeRepository;

	@Autowired
	private BuildingServingAreaRepository buildingServingAreaRepository;

	@Autowired
	private SystemConfigRepository systemConfigRepository;

	@Autowired
	private HubConfigurationRepository hubConfigurationRepository;

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private PrincipalService principalService;

	@Autowired
	private CalendarDao calendarDao;

	@Autowired
	private HubCalendarDao hubCalendarDao;

	@Autowired
	private OutOfCoverageRequestRepository outOfCoverageRequestRepository;

	public List<BuildingInfo> findAllBuildings(PageRequest pageRequest, Principal principal) {
		List<BuildingEntity> buildingEntities = new ArrayList<>();
		if (pageRequest != null && pageRequest.getPageNumber() < 0) {
			pageRequest = null;
		}
		if (principalService.isSuperAdmin(principal)) {
			buildingEntities = buildingRepository.findAll(pageRequest).getContent();
		} else {
			List<Long> hubs = new ArrayList<>();
			hubs = principalService.getHubs(principal);
			buildingEntities = buildingRepository.getSelectedHubs(hubs, pageRequest).getContent();
		}
		return BuildingTransformer.getBuildingInfosFromEntities(buildingEntities);
	}

	public BuildingEntity findBuildingEntityById(Long hubId) {
		return buildingRepository.findOne(hubId);
	}

	public BuildingInfo findBuildingInfoById(Long hubId) {
		BuildingEntity buildingEntity = buildingRepository.findOne(hubId);
		if (buildingEntity == null)
			return null;
		return BuildingTransformer.getBuildingInfoFromEntity(buildingEntity);
	}

	public List<Message> saveBuilding(Building building) throws DuplicatedKeyException {
		List<Message> messages = new ArrayList<>();
		boolean isBuildingNameExists = isBuildingNameExists(building.getBuildingId(), building.getBuildingname());
		if (isBuildingNameExists) {
			Message message = new Message(MessageType.SUCCESS, "buildingId",
					messageSource.getMessage("building.buildingname.unique", null, "building.buildingname.unique",
							LocaleContextHolder.getLocale()));
			messages.add(message);
		}
		checkBuildingLocationInsideServingArea(building);
		checkIntersctionRegionExceptMyRegion(building);
		saveBuildingInfo(building);

		return messages;
	}

	private void saveBuildingInfo(Building building) {
		BuildingEntity buildingEntity = new BuildingEntity();
		if (building.getBuildingId() == null) {
			buildingEntity.setBuildingId(Utils.generateUUID());
			buildingEntity.setCreated(new Date());
			buildingEntity.setVersion(0L);
			buildingEntity.setStatus("ACTIVE");
		} else {
			BuildingEntity newbuildingEntity = buildingRepository.findOne(building.getBuildingId());
			if (newbuildingEntity != null) {
				buildingEntity = newbuildingEntity;
				List<BuildingServingAreaEntity> buildingServingAreaEntities = buildingServingAreaRepository
						.findAllByBuildingId(buildingEntity.getBuildingId());
				buildingServingAreaRepository.delete(buildingServingAreaEntities);
			} else {
				throw new EntityNotFoundException("building not found");
			}
			if (building.getStatus() != null) {
				buildingEntity.setStatus(building.getStatus().getValue());
			}
		}

		buildingEntity.setArea(building.getArea());
		buildingEntity.setBuildingname(building.getBuildingname());
		buildingEntity.setBuildingnumber(building.getBuildingnumber());

		if (building.getBuildingTypeId() != null) {
			BuildingTypeEntity buildingTypeEntity = buildingTypeRepository.findOne(building.getBuildingTypeId());
			if (buildingTypeEntity != null) {
				buildingEntity.setBuildingType(buildingTypeEntity);
			}
		}

		if (building.getCityId() != null) {
			CityEntity cityEntity = cityRepository.findOne(building.getCityId());
			if (cityEntity != null) {
				buildingEntity.setCity(cityEntity);
			}
		}

		if (building.getCountryId() != null) {
			CountryEntity countryEntity = countryRepository.findOne(building.getCountryId());
			if (countryEntity != null) {
				buildingEntity.setCountry(countryEntity);
			}
		}

		if (building.getCountryCodeId() != null) {
			CountryCodesEntity countryCodeEntity = countryCodeRepository.findOne(building.getCountryCodeId());
			if (countryCodeEntity != null) {
				buildingEntity.setCountryCodes(countryCodeEntity);
			}
		}

		buildingEntity.setDescription(building.getDescription());
		buildingEntity.setLatitude(building.getLatitude());
		buildingEntity.setLongitude(building.getLongitude());
		buildingEntity.setPhoneNumber(building.getPhoneNumber());
		buildingEntity.setWaselcode(building.getWaselcode());

		buildingEntity.setStreet(building.getStreet());
		buildingEntity.setListOfBuildingServingArea(null);
		buildingRepository.save(buildingEntity);
		if (building.getBuildingId() == null) {
			setupHubConfigurations(buildingEntity);
			setUpHubCalendar(buildingEntity);
		}

		List<BuildingServingAreaEntity> buildingServingAreaEntities = new ArrayList<>();
		for (ServingArealocation servingArealocation : building.getBuildingServingArea()) {
			BuildingServingAreaEntity buildingServingAreaEntity = new BuildingServingAreaEntity();
			buildingServingAreaEntity.setBuildingServingId(Utils.generateUUID());
			buildingServingAreaEntity.setLatitude(servingArealocation.getLatitude());
			buildingServingAreaEntity.setLongitude(servingArealocation.getLongitude());
			buildingServingAreaEntity.setCreated(new Date());
			buildingServingAreaEntity.setBuilding(buildingEntity);
			buildingServingAreaRepository.save(buildingServingAreaEntity);

			buildingServingAreaEntities.add(buildingServingAreaEntity);
		}
		buildingRepository.save(buildingEntity);
	}

	public void setupHubConfigurations(BuildingEntity buildingEntity) {
		List<SystemConfigEntity> systemConfigEntities = systemConfigRepository.findAllDefaultConfigurations();
		if (systemConfigEntities != null) {
			for (SystemConfigEntity systemConfigEntity : systemConfigEntities) {
				HubConfigurationEntity hubConfigurationEntity = new HubConfigurationEntity();
				hubConfigurationEntity.setValue(systemConfigEntity.getValue());
				hubConfigurationEntity.setTextValue(systemConfigEntity.getTextValue());
				hubConfigurationEntity.setSystemConfig(systemConfigEntity);
				hubConfigurationEntity.setBuilding(buildingEntity);
				hubConfigurationEntity.setConfigType(systemConfigEntity.getConfigType());
				hubConfigurationEntity.setCreated(new Date());
				hubConfigurationRepository.save(hubConfigurationEntity);
			}
		}
	}

	public void setUpHubCalendar(BuildingEntity buildingEntity) {
		List<CalendarEntity> calendarEntities = calendarDao.findAllCalendars();
		if (calendarEntities != null) {
			for (CalendarEntity calendarEntity : calendarEntities) {
				HubCalendarEntity hubCalendarEntity = new HubCalendarEntity();
				hubCalendarEntity.setStatus(calendarEntity.getStatus());
				hubCalendarEntity.setBuilding(buildingEntity);
				hubCalendarEntity.setCalendar(calendarEntity);
				hubCalendarEntity.setCreated(new Date());
				hubCalendarEntity.setVersion(0L);
				hubCalendarDao.save(hubCalendarEntity);
			}
		}
	}

	private boolean isBuildingNameExists(Long buildingId, String buildingName) {
		List<BuildingEntity> buildingEntities = buildingRepository.getBuildingByName(buildingId, buildingName);
		if (buildingEntities != null && buildingEntities.size() > 0)
			return true;
		return false;
	}

	private void checkBuildingLocationInsideServingArea(Building building) throws DuplicatedKeyException {
		boolean chkBuildingLocationInsideServingArea = buildingServingAreaService
				.isBuildingLocationWithInServingArea(building, building.getBuildingServingArea());
		if (!chkBuildingLocationInsideServingArea) {
			throw new DuplicatedKeyException("building.buildinglocation.range", "buildingServingArea",
					building.getBuildingname());
		}
	}

	public List<CalendarDto> getCalendarDtoFromPointLocation(ServingArealocation point) throws DuplicatedKeyException {
		Long hubId = null;
		List<BuildingEntity> buildingEntities = buildingRepository.getActiveHubs();
		List<ServingArealocation> servingArealocations = new ArrayList<>();
		for (BuildingEntity buildingEntity : buildingEntities) {
			List<BuildingServingAreaEntity> areaEntities = buildingEntity.getListOfBuildingServingArea();
			if (areaEntities != null) {
				for (BuildingServingAreaEntity buildingServingAreaEntity : areaEntities) {
					ServingArealocation servingArealocation = new ServingArealocation(
							buildingServingAreaEntity.getLatitude(), buildingServingAreaEntity.getLongitude());
					servingArealocations.add(servingArealocation);
					if (buildingServingAreaService.isPointLocationWithInBuildingsServingArea(point,
							servingArealocations)) {
						hubId = buildingEntity.getBuildingId();
						break;
					}
				}
			}
		}

		return getHubCalendarDtoFromId(hubId, point);

	}

	private List<CalendarDto> getHubCalendarDtoFromId(Long hubId, ServingArealocation point) {
		if (hubId == null) {
			saveOutOfCoverageRequest(point);
			return null;
		}
		List<CalendarDto> calendarDtos = new ArrayList<>();
		List<HubCalendarEntity> hubCalendarEntities = hubCalendarDao.findAllHubCalendar(hubId);
		if (hubCalendarEntities != null) {
			for (HubCalendarEntity hubCalendarEntity : hubCalendarEntities) {
				if (hubCalendarEntity.getCalendar() != null
						&& "non-working".equalsIgnoreCase(hubCalendarEntity.getStatus())) {
					CalendarDto calendarDto = new CalendarDto();
					calendarDto.setId(hubCalendarEntity.getId());
					calendarDto.setDayName(hubCalendarEntity.getCalendar().getDayName());
					calendarDto.setStatus(hubCalendarEntity.getStatus());
					calendarDto.setHubId(hubId);
					calendarDtos.add(calendarDto);
				}
			}
			return calendarDtos;
		}
		return null;
	}

	private void saveOutOfCoverageRequest(ServingArealocation point) {
		if (point.getLatitude() != null && point.getLongitude() != null) {
			OutOfCoverageRequestEntity outOfCoverageRequestEntity = new OutOfCoverageRequestEntity();
			outOfCoverageRequestEntity.setLatitude(point.getLatitude());
			outOfCoverageRequestEntity.setLongitude(point.getLongitude());
			if("ON-DEMAND".equalsIgnoreCase(point.getType()) || "SCHEDULED".equalsIgnoreCase(point.getType())){
				outOfCoverageRequestEntity.setType(point.getType());
			}else{
				outOfCoverageRequestEntity.setType("ON-DEMAND");
			}
			
			outOfCoverageRequestRepository.save(outOfCoverageRequestEntity);
		}
	}

	private void checkIntersctionRegionExceptMyRegion(Building building) throws DuplicatedKeyException {
		boolean chkIntersctionRegion = buildingServingAreaService.isIntersectedWithRegionExceptDeterminedRegion(
				building.getBuildingServingArea(), building.getBuildingId());
		if (chkIntersctionRegion) {
			throw new DuplicatedKeyException("building.buildingarea.intersected", "buildingServingArea",
					building.getBuildingname());
		}

	}

	public boolean deactivateBuilding(Long hubId, String status) {
		BuildingEntity buildingEntity = findBuildingEntityById(hubId);
		if (buildingEntity != null) {
			if ("ACTIVE".equalsIgnoreCase(status) || "INACTIVE".equalsIgnoreCase(status)) {
				buildingEntity.setStatus(status.toUpperCase());
				buildingRepository.save(buildingEntity);
				return true;
			}
		}
		return false;
	}

	public boolean checkVehicleRelatedToBuildingOrUser(Long hubId) {
		BuildingEntity buildingEntity = findBuildingEntityById(hubId);
		if (buildingEntity != null) {
			List<UserHubEntity> userHubEntities = buildingEntity.getListOfUserHub();
			if (userHubEntities != null && userHubEntities.size() > 0)
				return true;
			List<VehicleEntity> vehicleEntities = buildingEntity.getListOfVehicle();
			if (vehicleEntities != null && vehicleEntities.size() > 0)
				return true;

		}
		return false;
	}

}
