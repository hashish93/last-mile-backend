package com.appzoneltd.lastmile.microservice.details.dao.repos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Repository;

import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryRow;

@Repository
public class ActiveVehicleCouchbaseRepo {

	private final CouchbaseTemplate couchbaseTemplate;

	private final static String BUCKET_NAME = "vehicle_tracking";

	@Autowired
	public ActiveVehicleCouchbaseRepo(CouchbaseTemplate couchbaseTemplate) {

		this.couchbaseTemplate = couchbaseTemplate;

	}



	public Long getNextOrder(Long activeVehicleId) {

		String query = "SELECT jobOrders FROM " + BUCKET_NAME + " WHERE  META(vehicle_tracking).id = '"
				+ activeVehicleId + "'  ";

		List<N1qlQueryRow> activeVehicleRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple(query)).allRows();
		Long nextJobOrderId=null;
		if (!activeVehicleRows.isEmpty()) {
			N1qlQueryRow vehicleInfoRow = activeVehicleRows.get(0);		
			if(activeVehicleRows !=null){
				JsonArray vehicleOrders = (JsonArray) vehicleInfoRow.value().get("jobOrders");
				/// Loop in Orders
				for (int index = 0; index < vehicleOrders.size(); index++) {
					JsonObject vehicleOrder = vehicleOrders.getObject(index);
					if(vehicleOrder.getBoolean("currentOrder") != null){
						if(vehicleOrder.getBoolean("currentOrder")){
							if(index !=vehicleOrders.size()-1)
								nextJobOrderId = vehicleOrders.getObject(index+1).getLong("jobOrderId");
							else
								nextJobOrderId = vehicleOrders.getObject(index).getLong("jobOrderId");
						}
					}		                
				}		            
			}
		}

		if (nextJobOrderId == null) {
			if (!activeVehicleRows.isEmpty()) {
				N1qlQueryRow vehicleInfoRow = activeVehicleRows.get(0);		
				if(activeVehicleRows !=null){
					JsonArray vehicleOrders = (JsonArray) vehicleInfoRow.value().get("jobOrders");
					JsonObject vehicleOrder = vehicleOrders.getObject(0);
					nextJobOrderId = vehicleOrder.getLong("jobOrderId");
				} else {
					nextJobOrderId = 0L;
				}
			}
		}
		return nextJobOrderId;
	}
}
