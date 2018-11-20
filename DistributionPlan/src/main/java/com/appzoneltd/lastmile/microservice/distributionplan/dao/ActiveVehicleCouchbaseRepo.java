package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Repository;

import com.appzoneltd.lastmile.microservice.distributionplan.service.PrincipalService;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryRow;

@Repository
public class ActiveVehicleCouchbaseRepo {

	private final CouchbaseTemplate couchbaseTemplate;

	private final PrincipalService principalService;

	@Autowired
	private ActiveVehicleRepository activeVehicleRepository;

	@Autowired
	private UsersRepository usersRepository;

	private final static String BUCKET_NAME = "vehicle_tracking";

	@Autowired
	public ActiveVehicleCouchbaseRepo(CouchbaseTemplate couchbaseTemplate, PrincipalService principalService) {

		this.couchbaseTemplate = couchbaseTemplate;
		this.principalService = principalService;

	}

	public VehicleActiveOrder getActiveVehicleStatus(Long activeVehicleId) {

		String query = "SELECT vehicleStatus FROM " + BUCKET_NAME + " WHERE  META(vehicle_tracking).id = '"
				+ activeVehicleId + "'  ";

		List<N1qlQueryRow> activeVehicleRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple(query)).allRows();

		VehicleActiveOrder activeVehicleOrder = new VehicleActiveOrder();

		if (!activeVehicleRows.isEmpty()) {

			N1qlQueryRow vehicleInfoRow = activeVehicleRows.get(0);
			activeVehicleOrder.setVehicleState(vehicleInfoRow.value().getString("vehicleStatus"));

		}

		return activeVehicleOrder;
	}

	public String getActiveVehiclePurpose(Long activeVehicleId) {

		String query = "SELECT purpose FROM " + BUCKET_NAME + " WHERE  META(vehicle_tracking).id = '" + activeVehicleId
				+ "'  ";

		List<N1qlQueryRow> activeVehicleRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple(query)).allRows();

		String purpose = "";

		if (!activeVehicleRows.isEmpty()) {

			N1qlQueryRow vehicleInfoRow = activeVehicleRows.get(0);
			purpose = vehicleInfoRow.value().getString("purpose");

		}

		return purpose;
	}

	public Long getHubIdToActiveVehicle(Long activeVehicleId) {

		String query = "SELECT hubId FROM " + BUCKET_NAME + " WHERE  META(vehicle_tracking).id = '" + activeVehicleId
				+ "'  ";
		List<N1qlQueryRow> activeVehicleRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple(query)).allRows();
		Long hubId = null;
		if (!activeVehicleRows.isEmpty()) {
			N1qlQueryRow vehicleInfoRow = activeVehicleRows.get(0);
			hubId = vehicleInfoRow.value().getLong("hubId");
		}

		return hubId;
	}

	public Boolean getActivleVehicleWorkingStatus(Long activeVehicleId) {

		Boolean activeVehicleIs_WorkingStatus = false;

		String query = "SELECT isWorking FROM " + BUCKET_NAME + " WHERE  META(vehicle_tracking).id = '"
				+ activeVehicleId + "'  ";

		List<N1qlQueryRow> activeVehicleRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple(query)).allRows();

		if (!activeVehicleRows.isEmpty()) {

			N1qlQueryRow vehicleInfoRow = activeVehicleRows.get(0);
			activeVehicleIs_WorkingStatus = vehicleInfoRow.value().getBoolean("isWorking");

		}
		return activeVehicleIs_WorkingStatus;

	}

	public String getFirstOrderLocation(Long activeVehicleId) {

		String query = "SELECT jobOrders FROM " + BUCKET_NAME + " WHERE  META(vehicle_tracking).id = '"
				+ activeVehicleId + "'  ";

		List<N1qlQueryRow> activeVehicleRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple(query)).allRows();

		VehicleActiveOrder vehicleActiveOrder = new VehicleActiveOrder();

		if (!activeVehicleRows.isEmpty()) {

			N1qlQueryRow vehicleInfoRow = activeVehicleRows.get(0);

			if (activeVehicleRows != null) {
				JsonArray vehicleOrders = (JsonArray) vehicleInfoRow.value().get("jobOrders");
				/// Loop in Orders
				for (int index = 0; index < vehicleOrders.size(); index++) {
					JsonObject vehicleOrder = vehicleOrders.getObject(index);
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					System.out.println("vehicleOrder inner "+vehicleOrder.toString());
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
					if (vehicleOrder.getBoolean("currentOrder") != null) {
						if (vehicleOrder.getBoolean("currentOrder")) {
							String location = vehicleOrder.getString("activeVehicleAddress");
							System.out.println(location);
							if (location != null) {
								vehicleActiveOrder.setLocation(location);
								System.out.println("location "+location);
								break;
							}
						}
					}

				}

			}

		}

		return vehicleActiveOrder.getLocation();
	}

	public List<VehicleOrder> getVehicleJobOrder(Long activeVehicleId) {

		String query = "SELECT jobOrders FROM " + BUCKET_NAME + " WHERE  META(vehicle_tracking).id = '"
				+ activeVehicleId + "'  ";

		List<N1qlQueryRow> activeVehicleRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple(query)).allRows();

		List<VehicleOrder> vehicleJobOrders = new ArrayList<>();

		if (!activeVehicleRows.isEmpty()) {

			N1qlQueryRow vehicleInfoRow = activeVehicleRows.get(0);

			if (activeVehicleRows != null) {
				JsonArray vehicleOrders = (JsonArray) vehicleInfoRow.value().get("jobOrders");
				/// Loop in Orders
				for (int index = 0; index < vehicleOrders.size(); index++) {
					JsonObject vehicleOrder = vehicleOrders.getObject(index);
					VehicleOrder order = new VehicleOrder();
					order.setOrderId(vehicleOrder.getLong("jobOrderId"));
					order.setOrderType(vehicleOrder.getString("orderType"));
					order.setPriorty(vehicleOrder.getInt("priority").longValue());

					vehicleJobOrders.add(order);

				} // end loop

			}

		}

		return vehicleJobOrders;
	}

	public List<VehicleActiveOrder> getOndemandPickupsToActiveVehicles(Long hubId) {

		List<VehicleActiveOrder> vehicleActiveOrders = new ArrayList<>();

		String query = "SELECT  META(vehicle_tracking).id , driverId , vehicleId , deviceId, workShiftId ,location , driverName , driverNumber , purpose ,rating , vehicleStatus, activeVehicleAddress ,jobOrders FROM "
				+ BUCKET_NAME + " WHERE " + "  purpose='ON_DEMAND_SERVICES'  ";

		StringBuilder query1 = new StringBuilder(query);
		if (hubId !=null) {
			query1.append(" AND hubId = "+hubId);
		}

		List<N1qlQueryRow> vehicleListRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple(query1.toString())).allRows();

		if (!vehicleListRows.isEmpty()) {
			for (N1qlQueryRow row : vehicleListRows) {

				if (row.value().getArray("jobOrders").isEmpty())
					continue;

				VehicleActiveOrder vehicleActiveOrder = new VehicleActiveOrder();

				Long driverId = row.value().getLong("driverId");
				Long deviceId = row.value().getLong("deviceId");
				Long workShiftId = row.value().getLong("workShiftId");
				Long vehicelId = row.value().getLong("vehicleId");
//				Long activeVehicleId = activeVehicleRepository
//						.findByDriverIdAndVehicleVehicleIdAndDevicesDeviceIdAndWorkShiftId(driverId, vehicelId,
//								deviceId, workShiftId)
//						.getId();
				
				Long activeVehicleId = Long.parseLong(row.value().getString("id"));

				Long imageId = usersRepository.findByUserId(driverId).getImageId();

				vehicleActiveOrder.setActiveVehicleId(activeVehicleId);
				vehicleActiveOrder.setDeviceNumber(row.value().getString("driverNumber"));
				vehicleActiveOrder.setDriverName(row.value().getString("driverName"));
				vehicleActiveOrder.setActiveVehiclePurpose(row.value().getString("purpose"));
				vehicleActiveOrder.setDriverRate(row.value().getInt("rating").toString());
				vehicleActiveOrder.setVehicleState(row.value().getString("vehicleStatus"));
				vehicleActiveOrder.setDriverImageId(imageId);
				vehicleActiveOrder.setActiveVehicleAddress(row.value().getString("activeVehicleAddress"));
				vehicleActiveOrder.setLocation(getFirstOrderLocation(activeVehicleId));
				vehicleActiveOrder.setVehicleOrders(getVehicleJobOrder(activeVehicleId));

				vehicleActiveOrders.add(vehicleActiveOrder);

			}
		}
		return vehicleActiveOrders;
	}

}
