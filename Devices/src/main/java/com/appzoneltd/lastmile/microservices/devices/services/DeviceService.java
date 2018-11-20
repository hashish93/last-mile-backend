package com.appzoneltd.lastmile.microservices.devices.services;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.appzoneltd.lastmile.microservices.devices.dao.ActiveVehicleDao;
import com.appzoneltd.lastmile.microservices.devices.dao.BrandDao;
import com.appzoneltd.lastmile.microservices.devices.dao.BuildingRepository;
import com.appzoneltd.lastmile.microservices.devices.dao.DeviceDao;
import com.appzoneltd.lastmile.microservices.devices.dto.DeviceDto;
import com.appzoneltd.lastmile.microservices.devices.entity.BuildingEntity;
import com.appzoneltd.lastmile.microservices.devices.entity.DeviceEntity;

@Service
public class DeviceService {

	@Autowired
	private MessageSource messageSource;

	@Autowired
	private DeviceDao deviceDao;

	@Autowired
	private BrandDao brandDao;

	@Autowired
	private ActiveVehicleDao activeVehicleDao;
	
	@Autowired
	private PrincipalService principalService;
	
	@Autowired
	private BuildingRepository buildingRepository;

	public List<DeviceDto> findAllDevicesByBage(PageRequest requestedPage,Principal principal) {

		List<DeviceEntity> devicesEntities = null;
		List<DeviceDto> devicesDtos = new ArrayList<DeviceDto>();
		if(principalService.isSuperAdmin(principal)){
			devicesEntities =  deviceDao.findAll(requestedPage).getContent();
		}else{
			List<Long> hubs = principalService.getHubs(principal);
			if(hubs.size()>0){
				
				devicesEntities = (List<DeviceEntity>) deviceDao.getDevicesForHubs(hubs, requestedPage);
			}
			
		}
		

		// Fill Data
		for (DeviceEntity deviceEntity : devicesEntities) {
			DeviceDto deviceDto = new DeviceDto();
			deviceDto.setDeviceId(deviceEntity.getDeviceId());
			deviceDto.setBrandId(deviceEntity.getDeviceBrand().getBrandId());
			deviceDto.setBrandName(deviceEntity.getDeviceBrand().getBrandname());
			deviceDto.setImeiInfo(deviceEntity.getImeiInfo());
			deviceDto.setModel(deviceEntity.getModel());
			deviceDto.setPhoneNumber(deviceEntity.getPhoneNumber());
			deviceDto.setStatus(deviceEntity.getStatus());
			if(deviceEntity.getBuilding() !=null){
				deviceDto.setHubId(deviceEntity.getBuilding().getBuildingId());
				deviceDto.setHubName(deviceEntity.getBuilding().getBuildingname());
			}

			// Adding Object to List
			devicesDtos.add(deviceDto);

		}

		// return All DTOs
		return devicesDtos;

	}

	public List<DeviceDto> findallAllActiveDevice() {

		List<DeviceDto> devicesDtos = new ArrayList<DeviceDto>();

		List<DeviceEntity> devicesEntities = deviceDao.getActiveDevice();

		for (DeviceEntity deviceEntity : devicesEntities) {
			DeviceDto deviceDto = new DeviceDto();
			deviceDto.setDeviceId(deviceEntity.getDeviceId());
			deviceDto.setBrandId(deviceEntity.getDeviceBrand().getBrandId());
			deviceDto.setBrandName(deviceEntity.getDeviceBrand().getBrandname());
			deviceDto.setImeiInfo(deviceEntity.getImeiInfo());
			deviceDto.setModel(deviceEntity.getModel());
			deviceDto.setPhoneNumber(deviceEntity.getPhoneNumber());
			deviceDto.setStatus(deviceEntity.getStatus());
			if(deviceEntity.getBuilding() !=null){
				deviceDto.setHubId(deviceEntity.getBuilding().getBuildingId());
				deviceDto.setHubName(deviceEntity.getBuilding().getBuildingname());
			}

			// Adding Object to List
			devicesDtos.add(deviceDto);

		}

		// return All DTOs
		return devicesDtos;
	}
	
	public List<DeviceDto> findallAllActiveDeviceForHub(Principal principal , Long hubId) {

		List<DeviceDto> devicesDtos = new ArrayList<DeviceDto>();
		List<Long> hubs = new ArrayList<>();
		if(principalService.isSuperAdmin(principal) || principalService.isSuperVisor(principal)){
			if(hubId != null){
				hubs.add(hubId);
			}
		}else{
			 hubs = principalService.getHubs(principal);
		}
		if(hubs != null && hubs.size() > 0){
			List<DeviceEntity> devicesEntities = deviceDao.getActiveDeviceForHub(hubs);
			for (DeviceEntity deviceEntity : devicesEntities) {
				DeviceDto deviceDto = new DeviceDto();
				deviceDto.setDeviceId(deviceEntity.getDeviceId());
				deviceDto.setBrandId(deviceEntity.getDeviceBrand().getBrandId());
				deviceDto.setBrandName(deviceEntity.getDeviceBrand().getBrandname());
				deviceDto.setImeiInfo(deviceEntity.getImeiInfo());
				deviceDto.setModel(deviceEntity.getModel());
				deviceDto.setPhoneNumber(deviceEntity.getPhoneNumber());
				deviceDto.setStatus(deviceEntity.getStatus());
				if(deviceEntity.getBuilding() !=null){
					deviceDto.setHubId(deviceEntity.getBuilding().getBuildingId());
					deviceDto.setHubName(deviceEntity.getBuilding().getBuildingname());
				}
				devicesDtos.add(deviceDto);

			}
		}
		
		return devicesDtos;
	}

	public List<Message> saveDevice(DeviceDto deviceDto,Principal principal) {
		List<Message> message = new ArrayList<Message>();
		BuildingEntity buildingEntity = null;
		if("SUPER_ADMIN".equalsIgnoreCase(principalService.getUserType(principal))
				|| "SUPERVISOR".equalsIgnoreCase(principalService.getUserType(principal))){
			if(deviceDto.getHubId() == null){
				message.add(new Message(MessageType.ERROR, "hubId", messageSource.getMessage(
						"device.hubId.notfound", null, "device.hubId.notfound", LocaleContextHolder.getLocale())));
			}
		}else{
			Long hubId = principalService.getHubIdIfFound(principal);
			if(hubId == null){
				message.add(new Message(MessageType.ERROR, "hubId", messageSource.getMessage(
						"device.hubId.notfound", null, "device.hubId.notfound", LocaleContextHolder.getLocale())));
			}else{
				deviceDto.setHubId(hubId);
			}
		}
		
		if(deviceDto.getHubId() !=null){
			buildingEntity =  buildingRepository.getActiveBuildingById(deviceDto.getHubId());
			if(buildingEntity == null){
				message.add(new Message(MessageType.ERROR, "hubId", messageSource.getMessage(
						"device.hub.notfound", null, "device.hub.notfound", LocaleContextHolder.getLocale())));
			}
		}
		
		if(message.size()>0){
			return message;
		}

		// Generate random Id for Devices
		Long deviceId = Utils.generateUUID();
		DeviceEntity device = new DeviceEntity();
		device.setDeviceId(deviceId);
		device.setImeiInfo(deviceDto.getImeiInfo());
		device.setModel(deviceDto.getModel());
		device.setPhoneNumber(deviceDto.getPhoneNumber());
		device.setBuilding(buildingEntity);
		device.setStatus(deviceDto.getStatus());
		device.setDeviceBrand(brandDao.findOne(deviceDto.getBrandId()));
		device.setVersion(0L);
		// check if IMEI or PhoneNumber existing already in db
		List<DeviceEntity> chkExistingValues = deviceDao.getDeviceByIMEIOrPhoneNumber(deviceDto.getImeiInfo(),
				deviceDto.getPhoneNumber());
		if (chkExistingValues.isEmpty()) {
			// Add
			DeviceEntity newDevice = deviceDao.save(device);
			message = null;
		} else {
			for (DeviceEntity entity : chkExistingValues) {
				if (entity.getImeiInfo().equals(deviceDto.getImeiInfo()))
					message.add(new Message(MessageType.ERROR, "imeiInfo", messageSource.getMessage(
							"device.IMIE.Existing", null, "device.IMIE.Existing", LocaleContextHolder.getLocale())));
				if (entity.getPhoneNumber().equals(deviceDto.getPhoneNumber()))
					message.add(new Message(MessageType.ERROR, "phoneNumber",
							messageSource.getMessage("device.PhoneNumber.Existing", null, "device.IMIE.Existing",
									LocaleContextHolder.getLocale())));
			}
		}

		return message;
	}

	public List<Message> updateDevicInfo(DeviceDto deviceDto,Principal principal) {

		List<Message> message = new ArrayList<Message>();
		BuildingEntity buildingEntity = null;
		if("SUPER_ADMIN".equalsIgnoreCase(principalService.getUserType(principal))
				|| "SUPERVISOR".equalsIgnoreCase(principalService.getUserType(principal))){
			if(deviceDto.getHubId() == null){
				message.add(new Message(MessageType.ERROR, "hubId", messageSource.getMessage(
						"device.hubId.notfound", null, "device.hubId.notfound", LocaleContextHolder.getLocale())));
			}
		}else{
			Long hubId = principalService.getHubIdIfFound(principal);
			if(hubId == null){
				message.add(new Message(MessageType.ERROR, "hubId", messageSource.getMessage(
						"device.hubId.notfound", null, "device.hubId.notfound", LocaleContextHolder.getLocale())));
			}else{
				deviceDto.setHubId(hubId);
			}
		}
		
		if(deviceDto.getHubId() !=null){
			buildingEntity =  buildingRepository.getActiveBuildingById(deviceDto.getHubId());
			if(buildingEntity == null){
				message.add(new Message(MessageType.ERROR, "hubId", messageSource.getMessage(
						"device.hub.notfound", null, "device.hub.notfound", LocaleContextHolder.getLocale())));
			}
		}
		
		if(message.size()>0){
			return message;
		}

		DeviceEntity device = new DeviceEntity();
		device.setDeviceId(deviceDto.getDeviceId());
		device.setImeiInfo(deviceDto.getImeiInfo());
		device.setModel(deviceDto.getModel());
		device.setPhoneNumber(deviceDto.getPhoneNumber());
		device.setBuilding(buildingEntity);
		device.setStatus(deviceDto.getStatus());
		device.setDeviceBrand(brandDao.findOne(deviceDto.getBrandId()));
		device.setVersion(0L);
		

		List<DeviceEntity> chkExistingValues = deviceDao.getDeviceByIMEIOrPhoneNumber(deviceDto.getImeiInfo(),
				deviceDto.getPhoneNumber());

		boolean error = false;
		for (DeviceEntity entity : chkExistingValues) {
			if (!entity.getDeviceId().equals(deviceDto.getDeviceId())) {
				if (entity.getImeiInfo().equals(deviceDto.getImeiInfo())) {
					message.add(new Message(MessageType.ERROR, "imeiInfo", messageSource.getMessage("device.IMIE.Exist",
							null, "device.IMIE.Exist", LocaleContextHolder.getLocale())));
					error = true;
				}
				if (entity.getPhoneNumber().equals(deviceDto.getPhoneNumber())) {
					message.add(new Message(MessageType.ERROR, "phoneNumber",
							messageSource.getMessage("device.PhoneNumber.Exist", null, "device.PhoneNumber.Exist",
									LocaleContextHolder.getLocale())));
					error = true;
				}

			}
		}
		if (!error) {
			deviceDao.save(device);
			message = null;

		}

		return message;

	}

	public DeviceDto findDeviceById(DeviceDto deviceDto) {

		DeviceEntity device = deviceDao.findOne(deviceDto.getDeviceId());

		DeviceDto deviceInfo = new DeviceDto();

		deviceInfo.setDeviceId(deviceDto.getDeviceId());
		deviceInfo.setBrandId(device.getDeviceBrand().getBrandId());
		deviceInfo.setBrandName(device.getDeviceBrand().getBrandname());
		deviceInfo.setImeiInfo(device.getImeiInfo());
		deviceInfo.setModel(device.getModel());
		deviceInfo.setPhoneNumber(device.getPhoneNumber());
		if(device.getBuilding() !=null){
			deviceInfo.setHubId(device.getBuilding().getBuildingId());
			deviceInfo.setHubName(device.getBuilding().getBuildingname());
		}
		deviceInfo.setStatus(device.getStatus());
//		 deviceInfo.setVersion(deviceDto.getVersion());

		return deviceInfo;

	}

	/*
	 * Deactivate Device by Setting Status = INACTIVE
	 */

	public boolean chkDeviceWithActiveVehicle(Long deviceId) {
		boolean chkDeviceWithActiveVehicle = false;
		Long result = activeVehicleDao.countAllActiveVehicleWithDevice(deviceId);
		System.out.println(result);
		if (result!=0){
			chkDeviceWithActiveVehicle=true;
		}
          return chkDeviceWithActiveVehicle ;
	}

	public DeviceEntity deactivateDevice(DeviceDto inactiveDeviceDto) {
		DeviceEntity device = deviceDao.findOne(inactiveDeviceDto.getDeviceId());
		device.setStatus(inactiveDeviceDto.getStatus());
		DeviceEntity deactivatedDevice = deviceDao.save(device);

		return deactivatedDevice;
	}

	public Long countDevice(Principal principal) {
		Long size = 0L;
		List<DeviceDto> result = findAllDevicesByBage(null , principal);
		if(result != null){
			size = (long) result.size();
		}
		return size;

	}
}
