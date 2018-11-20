package com.appzoneltd.lastmile.microservice.activevehicle.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.activevehicle.dao.ActiveVehicleEntity;
import com.appzoneltd.lastmile.microservice.activevehicle.dao.BuildingEntity;
import com.appzoneltd.lastmile.microservice.activevehicle.dao.BuildingRepository;
//import com.appzoneltd.lastmile.microservice.activevehicle.dao.CalendarEntity;
import com.appzoneltd.lastmile.microservice.activevehicle.dao.DevicesEntity;
import com.appzoneltd.lastmile.microservice.activevehicle.dao.DriverEntity;
import com.appzoneltd.lastmile.microservice.activevehicle.dao.VehicleEntity;
import com.appzoneltd.lastmile.microservice.activevehicle.dao.WorkShiftEntity;

@Component
public class ActiveVehicleMappers {

	@Autowired
	private  BuildingRepository buildingRepository;

	public ActiveVehicleEntity mappingToEntity(ActiveVehicle activeVehicle) {
		ActiveVehicleEntity entity = new ActiveVehicleEntity();
		// CalendarEntity calendar = new CalendarEntity();
		DevicesEntity devices = new DevicesEntity();
		DriverEntity driver = new DriverEntity();
		WorkShiftEntity workShift = new WorkShiftEntity();
		VehicleEntity vehicleEntity = new VehicleEntity();

		entity.setId(activeVehicle.getId());
		vehicleEntity.setVehicleId(activeVehicle.getVehicleId());
		entity.setVehicle(vehicleEntity);
		entity.setActive(activeVehicle.isActive());
		entity.setVersion(activeVehicle.getVersion());
		// calendar.setId(activeVehicle.getCalendarId());
		// entity.setCalendar(calendar);
		devices.setDeviceId(activeVehicle.getDeviceId());
		entity.setDevices(devices);
		driver.setId(activeVehicle.getDriverId());
		entity.setDriver(driver);
		workShift.setId(activeVehicle.getWorkShiftId());
		entity.setWorkShift(workShift);
		if (activeVehicle.getCreated() == null)
			entity.setCreated(new Date());
		else
			entity.setCreated(activeVehicle.getCreated());

		return entity;
	}

	public ActiveVehicleInfo mappingToDto(ActiveVehicleEntity entity) {
		ActiveVehicleInfo info = new ActiveVehicleInfo();

		info.setId(entity.getId());
		info.setVehicleId(entity.getVehicle().getVehicleId());
		info.setActive(entity.getActive());
		info.setVersion(entity.getVersion());
		info.setDriverId(entity.getDriver().getId());
		info.setDeviceId(entity.getDevices().getDeviceId());
		// info.setCalendarId(entity.getCalendar().getId());
		info.setWorkShiftId(entity.getWorkShift().getId());
		info.setDriverUsername(entity.getDriver().getUsers().getUsername());
		info.setDeviceModel(entity.getDevices().getModel());
		// info.setCalendar(entity.getCalendar().getDayName());
		info.setFrom(entity.getWorkShift().getFrom());
		info.setTo(entity.getWorkShift().getTo());
		info.setCreated(entity.getCreated());
		info.setVehicleType(entity.getVehicle().getVehicleType().getType());
		if (entity.getVehicle().getBuildingId() != null)
			info.setBuildingName(getBuildingName(entity.getVehicle().getBuildingId()));

		return info;
	}

	public List<ActiveVehicleInfo> mappingToDto(List<ActiveVehicleEntity> entities) {
		List<ActiveVehicleInfo> infos = new ArrayList<>();
		for (ActiveVehicleEntity activeVehicleEntity : entities) {
			ActiveVehicleInfo info = new ActiveVehicleInfo();

			info.setId(activeVehicleEntity.getId());
			info.setVehicleId(activeVehicleEntity.getVehicle().getVehicleId());
			info.setActive(activeVehicleEntity.getActive());
			info.setVersion(activeVehicleEntity.getVersion());
			info.setDriverId(activeVehicleEntity.getDriver().getId());
			info.setDeviceId(activeVehicleEntity.getDevices().getDeviceId());
			// info.setCalendarId(activeVehicleEntity.getCalendar().getId());
			info.setWorkShiftId(activeVehicleEntity.getWorkShift().getId());
			info.setDriverUsername(activeVehicleEntity.getDriver().getUsers().getUsername());
			info.setDeviceModel(activeVehicleEntity.getDevices().getModel());
			// info.setCalendar(activeVehicleEntity.getCalendar().getDayName());
			info.setFrom(activeVehicleEntity.getWorkShift().getFrom());
			info.setTo(activeVehicleEntity.getWorkShift().getTo());
			info.setCreated(activeVehicleEntity.getCreated());
			info.setVehicleType(activeVehicleEntity.getVehicle().getVehicleType().getType());

			if (activeVehicleEntity.getVehicle().getBuildingId() != null)
				info.setBuildingName(getBuildingName(activeVehicleEntity.getVehicle().getBuildingId()));

			infos.add(info);
		}

		return infos;
	}

	private String getBuildingName(Long buildingId) {
		String buildingName =null;
		BuildingEntity buildingEntity =buildingRepository.findOne(buildingId);
		if(buildingEntity!=null){
			buildingName = buildingEntity.getBuildingname();
		}
		return buildingName;

	}
}
