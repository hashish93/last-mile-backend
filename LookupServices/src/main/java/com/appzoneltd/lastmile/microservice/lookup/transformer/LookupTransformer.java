package com.appzoneltd.lastmile.microservice.lookup.transformer;

import com.appzoneltd.lastmile.microservice.lookup.dto.PackageLabelDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.PackageTypeDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.PickupTimeDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.VehicleTypeDto;
import com.appzoneltd.lastmile.microservice.lookup.entity.PackageLabelEntity;
import com.appzoneltd.lastmile.microservice.lookup.entity.PackageTypeEntity;
import com.appzoneltd.lastmile.microservice.lookup.entity.PickupTimeEntity;
import com.appzoneltd.lastmile.microservice.lookup.entity.VehicleTypeEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LookupTransformer {

    public static List<VehicleTypeDto> getVehicleTypeDtosFromEntities(List<VehicleTypeEntity> vehicleTypeEntities, Locale locale) {
        List<VehicleTypeDto> vehicleTypeDtos = new ArrayList<>();
        if (vehicleTypeEntities != null) {
            for (VehicleTypeEntity vehicleTypeEntity : vehicleTypeEntities) {
                vehicleTypeDtos.add(getVehicleTypeDtoFromEntity(vehicleTypeEntity, locale));
            }
        }
        return vehicleTypeDtos;
    }

    public static VehicleTypeDto getVehicleTypeDtoFromEntity(VehicleTypeEntity vehicleTypeEntity, Locale locale) {
        VehicleTypeDto vehicleTypeDto = new VehicleTypeDto();
        vehicleTypeDto.setVehicleTypeId(vehicleTypeEntity.getVehicleTypeId());
        vehicleTypeDto.setType("ar".equalsIgnoreCase(locale.getLanguage()) ? vehicleTypeEntity.getTypeAr() : vehicleTypeEntity.getType());
        vehicleTypeDto.setCreated(vehicleTypeEntity.getCreated());
        vehicleTypeDto.setVersion(vehicleTypeEntity.getVersion());
        return vehicleTypeDto;
    }

    public static List<PackageTypeDto> getPackageTypeDtosFromEntities(List<PackageTypeEntity> packageTypeEntities) {
        List<PackageTypeDto> packageTypeDtos = new ArrayList<>();
        if (packageTypeEntities != null) {
            for (PackageTypeEntity packageTypeEntity : packageTypeEntities) {
                packageTypeDtos.add(getPackageTypeDtoFromEntity(packageTypeEntity));
            }
        }
        return packageTypeDtos;
    }

    public static PackageTypeDto getPackageTypeDtoFromEntity(PackageTypeEntity packageTypeEntity) {
        PackageTypeDto packageTypeDto = new PackageTypeDto();
        packageTypeDto.setPackageTypeId(packageTypeEntity.getPackageTypeId());
        packageTypeDto.setPackageType(packageTypeEntity.getPackagetype());
        packageTypeDto.setDescription(packageTypeEntity.getDescription());
        packageTypeDto.setPackageDimension(packageTypeEntity.getPackagedimension());
        packageTypeDto.setExpectedWeight(packageTypeEntity.getExpectedweight());
        packageTypeDto.setCreated(packageTypeEntity.getCreated());
        packageTypeDto.setVersion(packageTypeEntity.getVersion());
        return packageTypeDto;
    }

    public static List<PackageLabelDto> getPackageLabelDtosFromEntities(List<PackageLabelEntity> packageLabelEntities) {
        List<PackageLabelDto> packageLabelDtos = new ArrayList<>();
        if (packageLabelEntities != null) {
            for (PackageLabelEntity packageLabelEntity : packageLabelEntities) {
                packageLabelDtos.add(getPackageLabelDtoFromEntity(packageLabelEntity));
            }
        }
        return packageLabelDtos;
    }

    public static PackageLabelDto getPackageLabelDtoFromEntity(PackageLabelEntity packageLabelEntity) {
        PackageLabelDto packageLabelDto = new PackageLabelDto();
        packageLabelDto.setPackageLabelId(packageLabelEntity.getPackageLabelId());
        packageLabelDto.setLabel(packageLabelEntity.getLabel());
        packageLabelDto.setCreated(packageLabelEntity.getCreated());
        packageLabelDto.setVersion(packageLabelEntity.getVersion());
        return packageLabelDto;
    }

    public static List<PickupTimeDto> getPickupTimeDtosFromEntities(List<PickupTimeEntity> pickupTimeEntities, Locale locale) {
        List<PickupTimeDto> pickupTimeDtos = new ArrayList<>();
        if (pickupTimeEntities != null) {
            for (PickupTimeEntity pickupTimeEntity : pickupTimeEntities) {
                pickupTimeDtos.add(getPickupTimeDtoFromEntity(pickupTimeEntity, locale));
            }
        }
        return pickupTimeDtos;
    }

    public static PickupTimeDto getPickupTimeDtoFromEntity(PickupTimeEntity pickupTimeEntity, Locale locale) {
        PickupTimeDto pickupTimeDto = new PickupTimeDto();
        pickupTimeDto.setPickupTimeId(pickupTimeEntity.getPickupTimeId());
        pickupTimeDto.setFromTime(pickupTimeEntity.getTimeFrom());
        pickupTimeDto.setToTime(pickupTimeEntity.getTimeTo());
        if (locale.getLanguage().equalsIgnoreCase("ar")) {
            if (pickupTimeDto.getFromTime() != null) {
                String str = pickupTimeDto.getFromTime();
                pickupTimeDto.setFromTime(str.replaceAll("AM", "صباحا").replaceAll("PM", "مساءا"));
            }
            if (pickupTimeDto.getToTime() != null) {
                String str = pickupTimeDto.getToTime();
                pickupTimeDto.setToTime(str.replaceAll("AM", "صباحا").replaceAll("PM", "مساءا"));
            }
        }


        pickupTimeDto.setCreated(pickupTimeEntity.getCreated());
        pickupTimeDto.setVersion(pickupTimeEntity.getVersion());
        return pickupTimeDto;
    }
}
