package com.appzoneltd.lastmile.microservice.vehicle.transformer;

import java.util.ArrayList;
import java.util.List;

import com.appzoneltd.lastmile.microservice.enums.EntityStatus;
import com.appzoneltd.lastmile.microservice.vehicle.dao.Purpose;
import com.appzoneltd.lastmile.microservice.vehicle.dao.VehicleInfo;
import com.appzoneltd.lastmile.microservice.vehicle.entity.VehicleEntity;

public class VehicleTransformer {
	
	public static List<VehicleInfo> getVehicleInfosFromEntities(List<VehicleEntity> vehicleEntities){
		List<VehicleInfo> vehicleInfos = new ArrayList<>();
		for(VehicleEntity vehicleEntity : vehicleEntities){
			VehicleInfo vehicleInfo = getVehicleInfoFromEntity(vehicleEntity);
			if(vehicleInfo !=null){
				vehicleInfos.add(vehicleInfo);
			}
		}
		return vehicleInfos;
	}
	
	public static VehicleInfo getVehicleInfoFromEntity(VehicleEntity vehicleEntity){
		VehicleInfo vehicleInfo = null;
		if(vehicleEntity != null){
			vehicleInfo = new VehicleInfo();
			vehicleInfo.setVehicleId(vehicleEntity.getVehicleId());
			if(vehicleEntity.getVehicleType() !=null){
				vehicleInfo.setVehicleType(vehicleEntity.getVehicleType().getType());
				vehicleInfo.setVehicleTypeId(vehicleEntity.getVehicleType().getVehicleTypeId());
			}
			vehicleInfo.setBrand(vehicleEntity.getBrand());
			if(vehicleEntity.getBuilding() !=null){
				vehicleInfo.setBuildingId(vehicleEntity.getBuilding().getBuildingId());
				vehicleInfo.setBuildingName(vehicleEntity.getBuilding().getBuildingname());
			}
			vehicleInfo.setChassis(vehicleEntity.getChassis());
			vehicleInfo.setColor(vehicleEntity.getColor());		
			vehicleInfo.setDescription(vehicleEntity.getDescription());
			vehicleInfo.setModel(vehicleEntity.getModel());
			vehicleInfo.setMotor(vehicleEntity.getMotor());
			vehicleInfo.setPlate(vehicleEntity.getPlate());
			if(vehicleEntity.getPurpose() !=null){
				vehicleInfo.setPurpose(Purpose.valueOf(vehicleEntity.getPurpose()));
			}
			if(vehicleEntity.getStatus() !=null){
				vehicleInfo.setStatus(EntityStatus.valueOf(vehicleEntity.getStatus()));
			}
			vehicleInfo.setWeight(vehicleEntity.getWeight());
			vehicleInfo.setCreated(vehicleEntity.getCreated());
			vehicleInfo.setVersion(vehicleEntity.getVersion());
		}
		return vehicleInfo;
	}

}
