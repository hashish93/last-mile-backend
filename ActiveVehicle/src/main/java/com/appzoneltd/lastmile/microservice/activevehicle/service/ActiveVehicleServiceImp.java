package com.appzoneltd.lastmile.microservice.activevehicle.service;

import com.appzoneltd.lastmile.microservice.activevehicle.couchbasedao.RegistrationModel;
import com.appzoneltd.lastmile.microservice.activevehicle.couchbasedao.RegistrationRepository;
import com.appzoneltd.lastmile.microservice.activevehicle.dao.ActiveVehicleEntity;
import com.appzoneltd.lastmile.microservice.activevehicle.dao.ActiveVehicleJpaRepository;
import com.appzoneltd.lastmile.microservice.activevehicle.dto.ActiveVehicle;
import com.appzoneltd.lastmile.microservice.activevehicle.dto.ActiveVehicleInfo;
import com.appzoneltd.lastmile.microservice.activevehicle.dto.ActiveVehicleMappers;
import com.appzoneltd.lastmile.microservice.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ActiveVehicleServiceImp implements ActiveVehicleService {

	private final ActiveVehicleJpaRepository activeVehicleJpaRepository;
	private final RegistrationRepository couchBaseRepository;
	private final PrincipalService principalService;
	
	@Autowired
	private ActiveVehicleMappers activeVehicleMappers;

	@Autowired
	public ActiveVehicleServiceImp(ActiveVehicleJpaRepository activeVehicleJpaRepository,
			RegistrationRepository registrationRepository, PrincipalService principalService) {
		this.activeVehicleJpaRepository = activeVehicleJpaRepository;
		this.couchBaseRepository = registrationRepository;
		this.principalService = principalService;
	}

	@Override
	public ActiveVehicleEntity createActiveVehicle(ActiveVehicle activeVehicle) throws DuplicatedKeyException {
		activeVehicle.setId(Utils.generateUUID());
		validateActiveVehicle(activeVehicle);
		ActiveVehicleEntity entity = activeVehicleJpaRepository
				.save(activeVehicleMappers.mappingToEntity(activeVehicle));

		try {
			saveOnCouchBase(entity);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return entity;
	}

	private void validateActiveVehicle(ActiveVehicle activeVehicle) throws DuplicatedKeyException {
		int countAllCriteriaResult = activeVehicleJpaRepository.countActiveVehiceWithAllCriteria(
				activeVehicle.getVehicleId(), activeVehicle.getDriverId(), activeVehicle.getDeviceId(),
				activeVehicle.getWorkShiftId());
		if (countAllCriteriaResult > 0)
			throw new DuplicatedKeyException("activevehicle.all", "activeVehicleId", "");

		int countVehicleWithWorkShift = activeVehicleJpaRepository
				.countActiveVehiceWithVehicleAndWorkShift(activeVehicle.getVehicleId(), activeVehicle.getWorkShiftId());
		if (countVehicleWithWorkShift > 0)
			throw new DuplicatedKeyException("activevehicle.vehicle", "vehicleId", "");

		int countDriverWithWorkShift = activeVehicleJpaRepository
				.countActiveVehiceWithDriverAndWorkShift(activeVehicle.getDriverId(), activeVehicle.getWorkShiftId());
		if (countDriverWithWorkShift > 0)
			throw new DuplicatedKeyException("activevehicle.driver", "driverId", "");

		int countDeviceWithWorkShift = activeVehicleJpaRepository
				.countActiveVehiceWithDeviceAndWorkShift(activeVehicle.getDeviceId(), activeVehicle.getWorkShiftId());
		if (countDeviceWithWorkShift > 0)
			throw new DuplicatedKeyException("activevehicle.device", "deviceId", "");
	}

	private void saveOnCouchBase(ActiveVehicleEntity activeVehicle) throws ParseException {
		RegistrationModel model = fillCouchBaseObject(activeVehicle);
		model.set_ID(activeVehicle.getId());
		// RegistrationModel existModel =
		// couchBaseRepository.findByVehicleIdAndDriverIdAndDeviceIdAndWorkShiftId(
		// model.getVehicleId(), model.getDriverId(), model.getDeviceId(),
		// model.getWorkShiftId());
		// if (existModel != null)
		// model.set_ID(existModel.get_ID());
		couchBaseRepository.save(model);
	}

	@SuppressWarnings("deprecation")
	private RegistrationModel fillCouchBaseObject(ActiveVehicleEntity activeVehicleEntity) throws ParseException {
		RegistrationModel model = new RegistrationModel();

		Date from = activeVehicleEntity.getWorkShift().getFrom();
		from.setYear(1970);
		from.setMonth(1);
		from.setDate(1);

		Date to = activeVehicleEntity.getWorkShift().getTo();
		to.setYear(1970);
		to.setMonth(1);
		to.setDate(1);

		if(activeVehicleEntity.getDevices()!=null){
			model.setDeviceId(activeVehicleEntity.getDevices().getDeviceId());
			model.setDriverNumber(activeVehicleEntity.getDevices().getPhonenumber());
		}
		
		if(activeVehicleEntity.getDriver()!=null){
			model.setDriverId(activeVehicleEntity.getDriver().getId());
			if(activeVehicleEntity.getDriver().getUsers()!=null){
				model.setDriverName(activeVehicleEntity.getDriver().getUsers().getUsername());
				model.setUserType(activeVehicleEntity.getDriver().getUsers().getUserType().getName());				
			}						
			if(activeVehicleEntity.getDriver().getRating() !=null){
				model.setRating(activeVehicleEntity.getDriver().getRating().intValue());
			}
		}
		
		if(activeVehicleEntity.getVehicle()!=null){
			model.setHubId(activeVehicleEntity.getVehicle().getBuildingId());
			model.setVehicleId(activeVehicleEntity.getVehicle().getVehicleId());
			model.setPurpose(activeVehicleEntity.getVehicle().getPurpose());
			model.setCapacity(activeVehicleEntity.getVehicle().getWeight().intValue());
		}
		
		model.setVehicleStatus("AVAILABLE");
		model.setWorkShiftId(activeVehicleEntity.getWorkShift().getId());
		model.setWorkShiftFrom(from.toInstant().toEpochMilli());
		model.setWorkShiftTo(to.toInstant().toEpochMilli());
		
		model.setIsWorking(false);
		return model;
	}

	@Override
	public void deleteActiveVehicle(Long id) {
		deleteCouchbaseDocument(activeVehicleJpaRepository.findOne(id));
		activeVehicleJpaRepository.delete(id);
	}

	private void deleteCouchbaseDocument(ActiveVehicleEntity activeVehicleEntity) {
		// RegistrationModel model =
		// couchBaseRepository.findByVehicleIdAndDriverIdAndDeviceIdAndWorkShiftId(
		// activeVehicleEntity.getVehicle().getVehicleId(),
		// activeVehicleEntity.getDriver().getId(),
		// activeVehicleEntity.getDevices().getDeviceId(),
		// activeVehicleEntity.getWorkShift().getId());
		RegistrationModel model = couchBaseRepository.findOne(activeVehicleEntity.getId());
		if (model != null)
			couchBaseRepository.delete(model.get_ID());
	}

	@Override
	public List<ActiveVehicleInfo> getActiveVehicles(int page, int maxResult, OrderBy orderBy, Principal principal) {

		List<ActiveVehicleInfo> activeVehicleInfos = new ArrayList<>();

		if (principalService.isSuperAdmin(principal)) {
			activeVehicleInfos = getActiveVehiclesForSuperAdmin(principal, page, maxResult, orderBy);
		}
		else {
			activeVehicleInfos = getActiveVehiclesForNonSuperAdmin(principal, page, maxResult, orderBy);
		}
		return activeVehicleInfos;

	}

	private List<ActiveVehicleInfo> getActiveVehiclesForSuperAdmin(Principal principal, int page, int maxResult,
			OrderBy orderBy) {
		if (page == 0)
			return activeVehicleMappers.mappingToDto((List<ActiveVehicleEntity>) activeVehicleJpaRepository
					.findAll(new Sort(Direction.fromString(orderBy.getOrderBy()), "created")));

		PageRequest pageRequest = new PageRequest(page - 1, maxResult, Direction.fromString(orderBy.getOrderBy()),
				"created");

		return activeVehicleMappers.mappingToDto(activeVehicleJpaRepository.findAll(pageRequest).getContent());

	}

	private List<ActiveVehicleInfo> getActiveVehiclesForNonSuperAdmin(Principal principal, int page, int maxResult,
			OrderBy orderBy) {

		List<Long> hubs = principalService.getHubs(principal);
		PageRequest pageRequest = new PageRequest(page - 1, maxResult, Direction.fromString(orderBy.getOrderBy()),
				"created");

		if (pageRequest != null && pageRequest.getPageNumber() < 0) {
			pageRequest = null;
		}

		List<ActiveVehicleEntity> activeVehicles = activeVehicleJpaRepository.findAllByHubs(pageRequest, hubs);
		return activeVehicleMappers.mappingToDto(activeVehicles);

	}

	@Override
	public List<ActiveVehicleInfo> getActiveVehiclesOnDemand(int page, int maxResult, OrderBy orderBy,
			Long hubId , Principal principal) {

		List<ActiveVehicleInfo> activeVehicleInfos = new ArrayList<>();

		
		if (!principalService.isSuperAdmin(principal) && !principalService.isSuperVisor(principal)) {
			hubId = principalService.getHubIdIfFound(principal);
		}
		activeVehicleInfos = getActiveVehiclesOnDemandToNonSuperAdmin(page, maxResult, orderBy,hubId, principal);

		return activeVehicleInfos;

	}

	private List<ActiveVehicleInfo> getActiveVehiclesOnDemandToSuperAdmin(int page, int maxResult, OrderBy orderBy,
			Principal principal) {

		if (page == 0)
			return activeVehicleMappers.mappingToDto(
					(List<ActiveVehicleEntity>) activeVehicleJpaRepository.getScheduledActiveVehiclesToSuperAdmin());

		PageRequest page1 = new PageRequest(page - 1, maxResult, Direction.fromString(orderBy.getOrderBy()), "created");

		return activeVehicleMappers
				.mappingToDto(activeVehicleJpaRepository.getScheduledActiveVehiclesPagedToSuperAdmin(page1));

	}

	private List<ActiveVehicleInfo> getActiveVehiclesOnDemandToNonSuperAdmin(int page, int maxResult, OrderBy orderBy,
		Long hubId , Principal principal) {
		
		if (page == 0)
			return activeVehicleMappers.mappingToDto(
					(List<ActiveVehicleEntity>) activeVehicleJpaRepository.getScheduledActiveVehicles(hubId));

		PageRequest page1 = new PageRequest(page - 1, maxResult, Direction.fromString(orderBy.getOrderBy()), "created");

		return activeVehicleMappers
				.mappingToDto(activeVehicleJpaRepository.getScheduledActiveVehiclesPaged(page1, hubId));

	}

	@Override
	public ActiveVehicleEntity getActiveVehicleById(Long id) {
		return activeVehicleJpaRepository.findOne(id);
	}

	@Override
	public ActiveVehicleEntity updateActiveVehicle(ActiveVehicle activeVehicle) {
		ActiveVehicleEntity activeVehicleEntity = activeVehicleMappers.mappingToEntity(activeVehicle);
		try {
			updateCouchBaseVehicleStatus(activeVehicleEntity);
		} catch (ParseException e) {

			e.printStackTrace();
		}
		return activeVehicleJpaRepository.save(activeVehicleEntity);
	}

	private void updateCouchBaseVehicleStatus(ActiveVehicleEntity activeVehicle) throws ParseException {
		ActiveVehicleEntity activeVehicleEntity = activeVehicleJpaRepository.findOne(activeVehicle.getId());
		if (activeVehicle.getActive()) {
			RegistrationModel model = fillCouchBaseObject(activeVehicleEntity);
			model.set_ID(activeVehicle.getId());
			couchBaseRepository.save(model);
		} else {
			// RegistrationModel model =
			// couchBaseRepository.findByVehicleIdAndDriverIdAndDeviceIdAndWorkShiftId(
			// activeVehicleEntity.getVehicle().getVehicleId(),
			// activeVehicleEntity.getDriver().getId(),
			// activeVehicleEntity.getDevices().getDeviceId(),
			// activeVehicleEntity.getWorkShift().getId());
			couchBaseRepository.delete(activeVehicle.getId());
		}
	}

	@Override
	public ActiveVehicleEntity updateActiveVehicleStatus(boolean active, Long id) {
		return null;
	}

	@Override
	public long countActiveVehicles(Principal principal) {

		if (principalService.isSuperAdmin(principal)) {
			return activeVehicleJpaRepository.count();
		}

		return countActiveVehiclesToNonSuperAdmin(principal);

	}

	private long countActiveVehiclesToNonSuperAdmin(Principal principal) {
		List<Long> hubs = principalService.getHubs(principal);

		return activeVehicleJpaRepository.countActiveVehiclesToNonSuperAdmin(hubs);

	}

	@Override
	public ActiveVehicleInfo getActiveVehicle(Long id) {
		return activeVehicleMappers.mappingToDto(activeVehicleJpaRepository.findOne(id));
	}

}
