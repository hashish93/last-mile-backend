package com.appzoneltd.lastmile.microservice.pickuptime.service;

import java.util.List;

import com.appzoneltd.lastmile.microservice.pickuptime.dao.PickupTime;

/**
 * @author alaa.nabil
 *
 */
public interface PickupTimeService {
	List<PickupTime> pickupTimeFindAll();
}
