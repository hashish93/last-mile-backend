/**
 * Jul 20, 201612:18:40 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.vehicletype.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.vehicletype.dao.VehicleType;
import com.appzoneltd.lastmile.microservice.vehicletype.dao.VehicleTypeRepositoryImpl;

/**
 * @author alaa.nabil
 *
 */
@Service
public class VehicleTypeServiceImp implements VehicleTypeService {

	private final static Logger LOGGER = LoggerFactory.getLogger(VehicleTypeServiceImp.class);

	private final VehicleTypeRepositoryImpl vehicleTypeRepositoryImpl;

	@Autowired
	public VehicleTypeServiceImp(VehicleTypeRepositoryImpl vehicleTypeRepositoryImpl) {
		this.vehicleTypeRepositoryImpl = vehicleTypeRepositoryImpl;
	}

	/**
	 * Method to invoke fin all vehicle types and get all of them .
	 * 
	 * @param companyId
	 * @return {@link List<vehicleTypes>}
	 */
	@SuppressWarnings("finally")
	@Override
	public List<VehicleType> vehicleTypeFindAll() {
		List<VehicleType> vehicleTypes = null;
		try {
			vehicleTypes = vehicleTypeRepositoryImpl.query();
			Collections.sort(vehicleTypes, new Comparator<VehicleType>() {

				@Override
				public int compare(VehicleType o1, VehicleType o2) {
					return o1.getType().compareToIgnoreCase(o2.getType());
				}
			});
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			return vehicleTypes;
		}
	}
}
