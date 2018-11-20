/**
 * Jul 20, 201612:18:40 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.shipmentservice.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.shipmentservice.dao.ShipmentServiceEntity;
import com.appzoneltd.lastmile.microservice.shipmentservice.dao.ShipmentServiceJpaRepository;
import com.appzoneltd.lastmile.microservice.shipmentservice.dao.ShipmentServiceTypeEntity;
import com.appzoneltd.lastmile.microservice.shipmentservice.dao.ShipmentServiceTypeJpaRepository;

/**
 * @author alaa.nabil
 *
 */
@Service
public class ShipmentServiceTypeService {

	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	private final ShipmentServiceJpaRepository shipmentServiceJpaRepository;
	private final ShipmentServiceTypeJpaRepository shipmentServiceTypeJpaRepository;

	@Autowired
	public ShipmentServiceTypeService(ShipmentServiceJpaRepository shipmentServiceJpaRepository,
			ShipmentServiceTypeJpaRepository shipmentServiceTypeJpaRepository) {
		this.shipmentServiceJpaRepository = shipmentServiceJpaRepository;
		this.shipmentServiceTypeJpaRepository = shipmentServiceTypeJpaRepository;
	}

	/**
	 * Method to invoke fin all shipment services and get all of them .
	 * 
	 * @param companyId
	 * @return
	 */
	@SuppressWarnings("finally")
	public List<ShipmentServiceEntity> shipmentServiceFindAll() {
		List<ShipmentServiceEntity> shipmentServices = null;
		try {
			shipmentServices = (List<ShipmentServiceEntity>) shipmentServiceJpaRepository.findAll();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			return shipmentServices;
		}
	}

	/**
	 * Method to invoke fin all shipment service types and get all of them .
	 * 
	 */
	@SuppressWarnings("finally")
	public List<ShipmentServiceTypeEntity> serviceTypesFindAllByServiceId(Long serviceId) {
		List<ShipmentServiceTypeEntity> serviceTypes = null;
		try {
			serviceTypes = shipmentServiceTypeJpaRepository.findByShipmentServiceId(serviceId);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			return serviceTypes;
		}
	}
}
