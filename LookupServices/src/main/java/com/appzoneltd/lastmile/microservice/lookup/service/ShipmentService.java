package com.appzoneltd.lastmile.microservice.lookup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.lookup.dao.ShipmentServiceRepository;
import com.appzoneltd.lastmile.microservice.lookup.dao.ShipmentServiceTypeRepository;
import com.appzoneltd.lastmile.microservice.lookup.dto.ShipmentServiceDto;
import com.appzoneltd.lastmile.microservice.lookup.dto.ShipmentServiceTypeDto;
import com.appzoneltd.lastmile.microservice.lookup.entity.ShipmentServiceEntity;
import com.appzoneltd.lastmile.microservice.lookup.entity.ShipmentServiceTypeEntity;
import com.appzoneltd.lastmile.microservice.lookup.transformer.ShipmentTransformer;

@Service
public class ShipmentService {

	@Autowired
	private ShipmentServiceRepository shipmentServiceRepository;
	
	@Autowired
	private ShipmentServiceTypeRepository shipmentServiceTypeRepository;
	
	public List<ShipmentServiceDto> getAllShipmentServices() {
		List<ShipmentServiceEntity> shipmentServices = (List<ShipmentServiceEntity>) shipmentServiceRepository.findAll();
		return ShipmentTransformer.getShipmentServiceDtosFromEntities(shipmentServices);
	}

	public List<ShipmentServiceTypeDto> getShipmentServiceTypeByServiceId(Long shipmentServiceId) {
		List<ShipmentServiceTypeEntity> serviceTypes = shipmentServiceTypeRepository.findByShipmentServiceId(shipmentServiceId);
		return ShipmentTransformer.getShipmentServiceTypeDtosFromEntities(serviceTypes);
	}
	
}
