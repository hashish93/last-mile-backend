/**
 * Jul 20, 201612:18:40 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.pickuptime.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.pickuptime.dao.PickupTime;
import com.appzoneltd.lastmile.microservice.pickuptime.dao.PickupTimeRepositoryImpl;

/**
 * @author alaa.nabil
 *
 */
@Service
public class PickupTimeServiceImp implements PickupTimeService {

	private final static Logger LOGGER = LoggerFactory.getLogger(PickupTimeServiceImp.class);

	private final PickupTimeRepositoryImpl pickupTimeRepo;

	public PickupTimeServiceImp(PickupTimeRepositoryImpl pickupTimeRepositoryImpl) {
		this.pickupTimeRepo = pickupTimeRepositoryImpl;
	}

	/**
	 * Method to invoke fin all pickup time and get all of them .
	 * 
	 * @param companyId
	 * @return {@link List<PickupTime>}
	 */
	@SuppressWarnings("finally")
	@Override
	public List<PickupTime> pickupTimeFindAll() {
		List<PickupTime> pickupTimes = null;
		try {
			pickupTimes = pickupTimeRepo.query();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			return pickupTimes;
		}

	}
}
