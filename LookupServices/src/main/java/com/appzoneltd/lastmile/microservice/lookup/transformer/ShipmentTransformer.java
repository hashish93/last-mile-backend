package com.appzoneltd.lastmile.microservice.lookup.transformer;

import java.util.ArrayList;
import java.util.List;

import com.appzoneltd.lastmile.microservice.lookup.dto.ShipmentServiceDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.ShipmentServiceTypeDto;
import com.appzoneltd.lastmile.microservice.lookup.entity.ShipmentServiceEntity;
import com.appzoneltd.lastmile.microservice.lookup.entity.ShipmentServiceTypeEntity;

public class ShipmentTransformer {

	public static List<ShipmentServiceDto> getShipmentServiceDtosFromEntities(
			List<ShipmentServiceEntity> shipmentServiceEntities) {
		List<ShipmentServiceDto> shipmentServiceDtos = new ArrayList<>();
		if (shipmentServiceEntities != null) {
			for (ShipmentServiceEntity shipmentServiceEntity : shipmentServiceEntities) {
				shipmentServiceDtos.add(getShipmentServiceDtoFromEntity(shipmentServiceEntity));
			}
		}
		return shipmentServiceDtos;
	}

	public static ShipmentServiceDto getShipmentServiceDtoFromEntity(ShipmentServiceEntity shipmentServiceEntity) {
		ShipmentServiceDto shipmentServiceDto = new ShipmentServiceDto();
		shipmentServiceDto.setShipmentServiceId(shipmentServiceEntity.getShipmentServiceId());
		shipmentServiceDto.setService(shipmentServiceEntity.getService());
		shipmentServiceDto.setDescription(shipmentServiceEntity.getDescription());
		shipmentServiceDto.setCreated(shipmentServiceEntity.getCreated());
		shipmentServiceDto.setVersion(shipmentServiceEntity.getVersion());
		return shipmentServiceDto;
	}

	public static List<ShipmentServiceTypeDto> getShipmentServiceTypeDtosFromEntities(
			List<ShipmentServiceTypeEntity> shipmentServiceTypeEntities) {
		List<ShipmentServiceTypeDto> shipmentServiceTypeDtos = new ArrayList<>();
		if (shipmentServiceTypeEntities != null) {
			for (ShipmentServiceTypeEntity shipmentServiceTypeEntity : shipmentServiceTypeEntities) {
				shipmentServiceTypeDtos.add(getShipmentServiceTypeDtoFromEntity(shipmentServiceTypeEntity));
			}
		}
		return shipmentServiceTypeDtos;
	}

	public static ShipmentServiceTypeDto getShipmentServiceTypeDtoFromEntity(
			ShipmentServiceTypeEntity shipmentServiceTypeEntity) {
		ShipmentServiceTypeDto shipmentServiceTypeDto = new ShipmentServiceTypeDto();
		shipmentServiceTypeDto.setShipmentServiceTypeId(shipmentServiceTypeEntity.getShipmentServiceTypeId());
		if (shipmentServiceTypeEntity.getShipmentService() != null) {
			shipmentServiceTypeDto
					.setShipmentServiceId(shipmentServiceTypeEntity.getShipmentService().getShipmentServiceId());
		}
		shipmentServiceTypeDto.setType(shipmentServiceTypeEntity.getType());
		shipmentServiceTypeDto.setDescription(shipmentServiceTypeEntity.getDescription());
		shipmentServiceTypeDto.setCreated(shipmentServiceTypeEntity.getCreated());
		shipmentServiceTypeDto.setVersion(shipmentServiceTypeEntity.getVersion());
		return shipmentServiceTypeDto;
	}

}
