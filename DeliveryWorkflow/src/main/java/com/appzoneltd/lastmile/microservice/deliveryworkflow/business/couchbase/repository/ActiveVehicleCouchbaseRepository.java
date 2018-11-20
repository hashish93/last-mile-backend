package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.couchbase.repository;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.couchbase.model.ActiveVehicleModel;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.couchbase.model.JobOrder;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Date;
import java.util.List;

@Repository
public class ActiveVehicleCouchbaseRepository {

	private final CouchbaseTemplate couchbaseTemplate;
	private final ObjectMapper mapper;
	private final ActiveVehiclePersistRepository activeVehiclePersistRepository;

	@Autowired
	public ActiveVehicleCouchbaseRepository(CouchbaseTemplate couchbaseTemplate, ObjectMapper mapper,
			ActiveVehiclePersistRepository activeVehiclePersistRepository) {
		this.couchbaseTemplate = couchbaseTemplate;
		this.mapper = mapper;
		this.activeVehiclePersistRepository = activeVehiclePersistRepository;
	}

	public void changeRequestStatus(Long driverId, Long requestId, String status) {
		ActiveVehicleModel activeVehicleModel = gettingActiveVehicleModel(driverId);
		if (activeVehicleModel != null) {
			// getting JobOrders
			for (JobOrder jobOrder : activeVehicleModel.getJobOrders()) {
				if (requestId.compareTo(jobOrder.getJobOrderId()) == 0) {
					jobOrder.setOrderStatus(status);
					activeVehicleModel = activeVehiclePersistRepository.save(activeVehicleModel);
					break;
				}
			}
		}
	}
	
	public void deletePackageForActiveVehicle(Long driverId, Long requestId, String status) {
		ActiveVehicleModel activeVehicleModel = gettingActiveVehicleModel(driverId);
		if (activeVehicleModel != null) {
			// getting JobOrders
			for (JobOrder jobOrder : activeVehicleModel.getJobOrders()) {
				if (requestId.compareTo(jobOrder.getJobOrderId()) == 0) {
					jobOrder.setOrderStatus(status);
					activeVehicleModel = activeVehiclePersistRepository.save(activeVehicleModel);
					break;
				}
			}
		}
	}

	public ActiveVehicleModel gettingActiveVehicleModel(Long driverId) {

		Long currentTimeMillis = getTimeInMillis();

		String query = "SELECT META(vehicle_tracking).id,vehicle_tracking FROM "
				+ couchbaseTemplate.getCouchbaseBucket().name() + " WHERE driverId = " + driverId
				+ " AND (workShiftFrom < " + currentTimeMillis + ") AND (workShiftTo > " + currentTimeMillis + ")";

		List<N1qlQueryRow> list = couchbaseTemplate.queryN1QL(N1qlQuery.simple(query)).allRows();
		if(list !=null && !list.isEmpty()){
			JsonObject response = list.get(0).value();
			JsonObject vehicleTracking = response.getObject("vehicle_tracking");
			Long id = Long.parseLong(response.getString("id"));

			ActiveVehicleModel activeVehicleModel = null;
			try {
				activeVehicleModel = mapper.readValue(vehicleTracking.toString(), ActiveVehicleModel.class);
				activeVehicleModel.set_ID(id);
			} catch (IOException e) {
				e.printStackTrace();
			}
			return activeVehicleModel;
		}
		return null;
	}
	
	
	

	@SuppressWarnings("deprecation")
	private Long getTimeInMillis() {
		Date date = new Date();
		date.setYear(1970);
		date.setMonth(1);
		date.setDate(1);
		date.setSeconds(1);
		return date.toInstant().toEpochMilli();
	}

}
