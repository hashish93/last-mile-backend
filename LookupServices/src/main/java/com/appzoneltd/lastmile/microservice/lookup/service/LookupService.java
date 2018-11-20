package com.appzoneltd.lastmile.microservice.lookup.service;

import com.appzoneltd.lastmile.microservice.lookup.dao.*;
import com.appzoneltd.lastmile.microservice.lookup.dto.PackageLabelDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.PackageTypeDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.PickupTimeDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.VehicleTypeDto;
import com.appzoneltd.lastmile.microservice.lookup.entity.*;
import com.appzoneltd.lastmile.microservice.lookup.transformer.LookupTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;


@Service
public class LookupService {
	
	@Autowired
	private DrivingLicenseTypeRepository drivingLicenseTypeRepository;
	
	@Autowired
	private BuildingTypeRepository buildingTypeRepository;
	
	@Autowired
	private VehicleTypeRepository vehicleTypeRepository;
	
	@Autowired
	private PackageTypeRepository packageTypeRepository;
	
	@Autowired
	private PackageLabelRepository packageLabelRepository;
	
	@Autowired
	private PickupTimeRepository pickupTimeRepository;
	
	public List<DrivingLicenseTypeEntity> getDrivingLicenseTypes(){
		return (List<DrivingLicenseTypeEntity>) drivingLicenseTypeRepository.findAll();
	}
	
	public List<BuildingTypeEntity> getBuildingTypes(){
		return (List<BuildingTypeEntity>) buildingTypeRepository.findAll();
	}
	
	public List<VehicleTypeDto> getVehicleTypes(Locale locale){
		List<VehicleTypeEntity> vehicleTypeEntities= (List<VehicleTypeEntity>) vehicleTypeRepository.findAll(); 
		return LookupTransformer.getVehicleTypeDtosFromEntities(vehicleTypeEntities, locale);
	}
	
	public List<PackageTypeDto> getPackageTypes(){
		List<PackageTypeEntity> packageTypeEntities= (List<PackageTypeEntity>) packageTypeRepository.findAll(); 
		return LookupTransformer.getPackageTypeDtosFromEntities(packageTypeEntities);
	}
	
	public List<PackageLabelDto> getPackageLabels(){
		List<PackageLabelEntity> packageLabelEntities= (List<PackageLabelEntity>) packageLabelRepository.findAll(); 
		return LookupTransformer.getPackageLabelDtosFromEntities(packageLabelEntities);
	}
	
	public List<PickupTimeDto> getPickupTimes(Locale locale){
		List<PickupTimeEntity> pickupTimeEntities= (List<PickupTimeEntity>) pickupTimeRepository.findAll(); 
		return LookupTransformer.getPickupTimeDtosFromEntities(pickupTimeEntities , locale);
	}
	
}
