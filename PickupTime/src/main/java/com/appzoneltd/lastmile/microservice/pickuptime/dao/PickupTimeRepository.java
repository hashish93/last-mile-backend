/**
 * Jul 20, 201610:43:49 AM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.pickuptime.dao;

/**
 * @author alaa.nabil
 *
 */
public interface PickupTimeRepository {
	String SQL_SELECT_ALL = "SELECT pickup_time_id, time_from, time_to, created, version "
			+ "FROM lastmile_request.pickup_time ";

}
