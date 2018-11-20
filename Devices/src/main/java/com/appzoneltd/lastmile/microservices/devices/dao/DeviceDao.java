package com.appzoneltd.lastmile.microservices.devices.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.appzoneltd.lastmile.microservices.devices.entity.DeviceEntity;

public interface DeviceDao extends PagingAndSortingRepository<DeviceEntity, Long> {
	
	
	@Query("SELECT d FROM DeviceEntity d WHERE d.imeiInfo = :imei OR d.phoneNumber = :phone ")
	public List<DeviceEntity> getDeviceByIMEIOrPhoneNumber(@Param("imei") String imei , @Param("phone") String phone);
	
	
	@Query("SELECT d FROM DeviceEntity d WHERE d.status='ACTIVE'")
	public List<DeviceEntity> getActiveDevice();
	
	@Query("SELECT d FROM DeviceEntity d inner join d.building b WHERE b.buildingId IN (:hubs) AND d.status='ACTIVE'")
	public List<DeviceEntity> getActiveDeviceForHub(@Param("hubs") List<Long> hubs);
	
	
	@Query("SELECT d FROM DeviceEntity d inner join d.building b WHERE b.buildingId IN (:hubs)")
	public List<DeviceEntity> getDevicesForHubs(@Param("hubs") List<Long> hubs , Pageable pageable);
	

	
}
