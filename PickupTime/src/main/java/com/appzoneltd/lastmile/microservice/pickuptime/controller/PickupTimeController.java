/**
 * Jul 20, 20161:17:34 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.pickuptime.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.appzoneltd.lastmile.microservice.pickuptime.dao.PickupTime;
import com.appzoneltd.lastmile.microservice.pickuptime.service.PickupTimeService;

/**
 * @author alaa.nabil
 *
 */
@RestController
public class PickupTimeController {
	/** Autowiring Pickup Time service annotated class */
	private final PickupTimeService pickupTimeService;

	public PickupTimeController(PickupTimeService pickupTimeService) {
		this.pickupTimeService = pickupTimeService;
	}

	/**
	 * Method to controll httprequests to perform find all Pickup Time
	 * 
	 * @param companyId
	 * @return {@link ResponseEntity} with {@link HttpStatus} of a
	 *         {@link List<PickupTime>}
	 */
	@RequestMapping(value = "/findall", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PickupTime>> pickupTimeFindAllAPI() {
		List<PickupTime> pickupTimes = pickupTimeService.pickupTimeFindAll();
		if (pickupTimes == null)
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		return new ResponseEntity<List<PickupTime>>(pickupTimes, HttpStatus.OK);
	}
}
