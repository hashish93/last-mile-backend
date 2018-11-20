/**
 * Jul 20, 201610:43:49 AM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.vehicletype.dao;

/**
 * @author alaa.nabil
 *
 */
public interface VehicleTypeRepository {
	String SQL_SELECT_ALL = "SELECT vehicle_type_id, type, created, version " + "FROM lastmile_core.vehicle_type ";
}
