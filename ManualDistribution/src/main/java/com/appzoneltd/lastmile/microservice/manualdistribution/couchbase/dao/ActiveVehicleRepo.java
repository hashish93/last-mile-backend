package com.appzoneltd.lastmile.microservice.manualdistribution.couchbase.dao;

import com.appzoneltd.lastmile.microservice.manualdistribution.entity.ActiveVehicle;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ActiveVehicleRepo {

	private final CouchbaseTemplate couchbaseTemplate;

	private final static String BUCKET_NAME = "vehicle_tracking";

	@Autowired
	public ActiveVehicleRepo(CouchbaseTemplate couchbaseTemplate) {

		this.couchbaseTemplate = couchbaseTemplate;

	}

	public ActiveVehicle getActiveVehicleInfo(Long activeVehicleId) {

		String query = "SELECT capacity,purpose,workShiftFrom,workShiftTo,hubId FROM " + BUCKET_NAME
				+ " WHERE  META(vehicle_tracking).id = '" + activeVehicleId+"' ";

		List<N1qlQueryRow> activeVehicleRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple(query)).allRows();

		ActiveVehicle activeVehicle = new ActiveVehicle();

		if (!activeVehicleRows.isEmpty()) {

			N1qlQueryRow vehicleInfoRow = activeVehicleRows.get(0);

			activeVehicle.setCapacity(vehicleInfoRow.value().getInt("capacity"));
			activeVehicle.setPurpose(vehicleInfoRow.value().getString("purpose"));
			activeVehicle.setHubId(vehicleInfoRow.value().getLong("hubId"));
			activeVehicle.setWorkShiftFrom(vehicleInfoRow.value().getLong("workShiftFrom"));
			activeVehicle.setWorkShiftTo(vehicleInfoRow.value().getLong("workShiftTo"));


		}

		return activeVehicle;
	}

	public List<ActiveVehicle> getActiveVehicles(){
		String query = "SELECT META(vehicle_tracking).id AS activeVehicleId,vehicleId,capacity,purpose,workShiftFrom,workShiftTo,hubId FROM " + BUCKET_NAME + " WHERE vehicleStatus = 'available' OR vehicleStatus = 'AVAILABLE' ";
		List<N1qlQueryRow> activeVehicleRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple(query)).allRows();
		List<ActiveVehicle> ActiveVehicleList = new ArrayList<>();
		if(!activeVehicleRows.isEmpty()){
			for(N1qlQueryRow veh : activeVehicleRows){
				ActiveVehicle vehicle = new ActiveVehicle();
				vehicle.setActiveVehicleId(Long.parseLong(veh.value().getString("activeVehicleId")));
				vehicle.setVehicleId(veh.value().getLong("vehicleId"));
				vehicle.setCapacity(veh.value().getInt("capacity"));
				vehicle.setPurpose(veh.value().getString("purpose"));
				vehicle.setHubId(veh.value().getLong("hubId"));
				vehicle.setWorkShiftFrom(veh.value().getLong("workShiftFrom"));
				vehicle.setWorkShiftTo(veh.value().getLong("workShiftTo"));
				ActiveVehicleList.add(vehicle);
			}
		}

		return ActiveVehicleList;

	}

	public Integer getActiveVehiclePickupCount(){
		int count = 0;
		String query = "SELECT COUNT (*) AS pickupCount FROM " + BUCKET_NAME + " WHERE vehicleStatus = 'available' AND (purpose = 'PICKUP' OR  purpose = 'PICKUP_DELIVERY') ";
		List<N1qlQueryRow> activeVehicleRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple(query)).allRows();

		if (!activeVehicleRows.isEmpty()) {
			N1qlQueryRow vehicleInfoRow = activeVehicleRows.get(0);
			count = vehicleInfoRow.value().getInt("pickupCount");
		}

		return count;
	}

	public Integer getActiveVehicleDeliveryCount(){
		int count = 0;
		String query = "SELECT COUNT (*) AS deliveryCount FROM " + BUCKET_NAME + " WHERE vehicleStatus = 'available' AND (purpose = 'DELIVERY' OR  purpose = 'PICKUP_DELIVERY') ";
		List<N1qlQueryRow> activeVehicleRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple(query)).allRows();

		if (!activeVehicleRows.isEmpty()) {
			N1qlQueryRow vehicleInfoRow = activeVehicleRows.get(0);
			count = vehicleInfoRow.value().getInt("deliveryCount");
		}

		return count;
	}

}
