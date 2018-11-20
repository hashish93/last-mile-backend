package com.appzoneltd.lastmile.microservice.vehicle.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.appzoneltd.lastmile.microservice.vehicle.dao.Vehicle;
import com.appzoneltd.lastmile.microservice.vehicle.dao.VehicleInfo;
import com.appzoneltd.lastmile.microservice.vehicle.entity.ActiveVehicleEntity;
import com.appzoneltd.lastmile.microservice.vehicle.entity.BuildingEntity;
import com.appzoneltd.lastmile.microservice.vehicle.entity.VehicleEntity;
import com.appzoneltd.lastmile.microservice.vehicle.entity.VehicleTypeEntity;
import com.appzoneltd.lastmile.microservice.vehicle.repo.ActiveVehicleRepository;
import com.appzoneltd.lastmile.microservice.vehicle.repo.BuildingRepository;
import com.appzoneltd.lastmile.microservice.vehicle.repo.VehicleRepository;
import com.appzoneltd.lastmile.microservice.vehicle.repo.VehicleTypeRepository;
import com.appzoneltd.lastmile.microservice.vehicle.transformer.VehicleTransformer;

@Service
public class VehicleService {

	@Autowired
	private VehicleRepository vehicleRepository;

	@Autowired
	private BuildingRepository buildingRepository;

	@Autowired
	private VehicleTypeRepository vehicleTypeRepository;

	@Autowired
	private ActiveVehicleRepository activeVehicleRepository;

	@Autowired
	private PrincipalService principalService;

	@Autowired
	private MessageSource messageSource;

	private List<VehicleEntity> getVehicleEntitiesForAdmin(PageRequest pageRequest) {
		if (pageRequest != null && pageRequest.getPageNumber() < 0) {
			pageRequest = null;
		}
		if (pageRequest != null) {
			System.out.println(pageRequest.toString());
		}
		List<VehicleEntity> vehicleEntities = vehicleRepository.findAll(pageRequest).getContent();

		return vehicleEntities;
	}

	private List<VehicleEntity> getVehicleEntitiesForNonAdmin(PageRequest pageRequest, List<Long> hubs) {
		if (pageRequest != null && pageRequest.getPageNumber() < 0) {
			pageRequest = null;
		}
		List<VehicleEntity> vehicleEntities = vehicleRepository.getVehicleEntitiesForNonAdmin(pageRequest, hubs)
				.getContent();

		return vehicleEntities;
	}

	public List<VehicleInfo> getAllVehiclesInfo(Principal principal, PageRequest pageRequest) {
		List<VehicleEntity> vehicleEntities = new ArrayList<>();
		if (principalService.isSuperAdmin(principal)) {
			vehicleEntities = getVehicleEntitiesForAdmin(pageRequest);
		} else {
			List<Long> hubs = principalService.getHubs(principal);
			vehicleEntities = getVehicleEntitiesForNonAdmin(pageRequest, hubs);
		}
		return VehicleTransformer.getVehicleInfosFromEntities(vehicleEntities);
	}
	
	public List<VehicleInfo> getAllVehiclesInfoForHub(Principal principal, PageRequest pageRequest,Long hubId) {
		List<VehicleEntity> vehicleEntities = new ArrayList<>();
		List<Long> hubs = new ArrayList<>();
		if (principalService.isSuperAdmin(principal) || principalService.isSuperVisor(principal)) {
			if(hubId !=null){
				hubs.add(hubId);
			}
		} else {
			hubs = principalService.getHubs(principal);
		}
		if(hubs.size()>0){
			vehicleEntities = getVehicleEntitiesForNonAdmin(pageRequest, hubs);
		}
		return VehicleTransformer.getVehicleInfosFromEntities(vehicleEntities);
	}

	public VehicleInfo findVehicleById(Long id) {
		VehicleEntity vehicleEntity = vehicleRepository.findVehicleById(id);
		VehicleInfo vehicleInfo = VehicleTransformer.getVehicleInfoFromEntity(vehicleEntity);
		return vehicleInfo;
	}

	public VehicleEntity findVehicleEntityById(Long id) {
		VehicleEntity vehicleEntity = vehicleRepository.findVehicleById(id);
		return vehicleEntity;
	}

	public List<Message> saveVehicle(Vehicle vehicle, Principal principal) {

		List<Message> messages = new ArrayList<Message>();

		boolean isPlateExists = isPlateExists(vehicle.getPlate(), vehicle.getVehicleId());
		if (isPlateExists) {
			messages.add(new Message(MessageType.ERROR, "plate",
					messageSource.getMessage("vehicle.plate.exists", null, LocaleContextHolder.getLocale())));
		}

		boolean isMotorExists = isMotorExists(vehicle.getMotor(), vehicle.getVehicleId());
		if (isMotorExists) {
			messages.add(new Message(MessageType.ERROR, "motor",
					messageSource.getMessage("vehicle.motor.exists", null, LocaleContextHolder.getLocale())));
		}
		if (!messages.isEmpty()) {
			return messages;
		} else {
			VehicleEntity vehicleEntity = getVehicleEntityFromVehicle(vehicle, principal);
			if (vehicleEntity != null) {
				vehicleRepository.save(vehicleEntity);
			}
		}
		return null;
	}

	private boolean isPlateExists(String plate, Long vehicleId) {
		VehicleEntity vehicleEntity = vehicleRepository.checkPlateExists(vehicleId, plate);
		return vehicleEntity != null;
	}

	private boolean isMotorExists(String motor, Long vehicleId) {
		VehicleEntity vehicleEntity = vehicleRepository.checkPlateExists(vehicleId, motor);
		return vehicleEntity != null;
	}

	private VehicleEntity getVehicleEntityFromVehicle(Vehicle vehicle, Principal principal) {
		VehicleEntity vehicleEntity = null;
		if (vehicle != null) {
			vehicleEntity = new VehicleEntity();
			if (vehicle.getVehicleId() == null) {
				vehicleEntity = saveIntialVehicleEntity();
			} else {
				VehicleEntity existingVehicleEntity = findVehicleEntityById(vehicle.getVehicleId());
				if (existingVehicleEntity == null) {
					vehicleEntity = saveIntialVehicleEntity();
				} else {
					vehicleEntity.setVehicleId(vehicle.getVehicleId());
					vehicleEntity.setCreated(vehicle.getCreated());
					vehicleEntity.setVersion(vehicle.getVersion());
					vehicleEntity.setStatus(vehicle.getStatus().getValue());
				}

			}
			vehicleEntity.setBrand(vehicle.getBrand());
			if (!"SUPER_ADMIN".equalsIgnoreCase(principalService.getUserType(principal))
					&& !"SUPERVISOR".equalsIgnoreCase(principalService.getUserType(principal))) {
				vehicle.setBuildingId(principalService.getHubIdIfFound(principal));
			}
			if (vehicle.getBuildingId() != null) {
				BuildingEntity buildingEntity = buildingRepository.findOne(vehicle.getBuildingId());
				if (buildingEntity != null) {
					vehicleEntity.setBuilding(buildingEntity);
				}
			}

			vehicleEntity.setChassis(vehicle.getChassis());
			vehicleEntity.setColor(vehicle.getColor());
			vehicleEntity.setDescription(vehicle.getDescription());
			vehicleEntity.setModel(vehicle.getModel());
			vehicleEntity.setMotor(vehicle.getMotor());
			vehicleEntity.setPlate(vehicle.getPlate());
			vehicleEntity.setPurpose(vehicle.getPurpose().getPurpose());
			if (vehicle.getVehicleTypeId() != null) {
				VehicleTypeEntity vehicleTypeEntity = vehicleTypeRepository.findOne(vehicle.getVehicleTypeId());
				if (vehicleTypeEntity != null) {
					vehicleEntity.setVehicleType(vehicleTypeEntity);
				}
			}
			vehicleEntity.setWeight(vehicle.getWeight());
		}
		return vehicleEntity;
	}

	private VehicleEntity saveIntialVehicleEntity() {
		VehicleEntity vehicleEntity = new VehicleEntity();
		vehicleEntity.setVehicleId(Utils.generateUUID());
		vehicleEntity.setCreated(new Date());
		vehicleEntity.setVersion(1L);
		vehicleEntity.setStatus("ACTIVE");
		return vehicleEntity;
	}

	public boolean removeVehicle(Long vehicleId,String status) throws EntityNotFoundException {
		VehicleEntity vehicleEntity = findVehicleEntityById(vehicleId);
		if (vehicleEntity == null) {
			throw new EntityNotFoundException("vehicle.vehicleid.exist");
		}
		if("ACTIVE".equalsIgnoreCase(status)){
			vehicleEntity.setStatus("ACTIVE");
		}else{
			vehicleEntity.setStatus("INACTIVE");
		}
		vehicleRepository.save(vehicleEntity);
		return true;

	}

	public boolean checkVehicleRelatedToActiveVehicle(Long vehicleId) {
		boolean chkVehicleAsActive = false;
		List<ActiveVehicleEntity> countVehicleAsActive = activeVehicleRepository.getActiveVehicleByVehicleId(vehicleId);
		if (countVehicleAsActive != null && countVehicleAsActive.size() > 0) {
			chkVehicleAsActive = true;
		}
		return chkVehicleAsActive;
	}

}
