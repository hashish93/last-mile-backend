package com.appzoneltd.lastmile.microservice.ondemandworkflow.workflowDao;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.VehicleCapacity;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.VehicleLocation;
import com.couchbase.client.java.document.json.JsonArray;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class VehicleModelDao {

    private static final String BUCKET_NAME = "vehicle_tracking";
    private final CouchbaseTemplate couchbaseTemplate;

    @Autowired
    public VehicleModelDao(CouchbaseTemplate couchbaseTemplate) {
        this.couchbaseTemplate = couchbaseTemplate;
    }

    public VehicleCapacity getVehicleCapacity(Long driverId) {
        Long currentTimeInMillis = getTimeInMillis();
        /// NearByVehicle Query
        String query = "SELECT driverId,capacity,jobOrders FROM " + BUCKET_NAME + " WHERE " +
                " driverId = " + driverId + " AND workShiftFrom<="
                + currentTimeInMillis + " AND workShiftTo>=" + currentTimeInMillis;
        /// Getting List of Vehicles
        List<N1qlQueryRow> vehicleListRows = couchbaseTemplate
                .queryN1QL(
                        N1qlQuery.simple(query))
                .allRows();

        VehicleCapacity vehicleCapacity = new VehicleCapacity();
        //// Check if Result returned
        if (!vehicleListRows.isEmpty()) {
            N1qlQueryRow vehicleRow = vehicleListRows.get(0);
            /// Creating New VehicleCapacity
            List<Double> vehicleOrderWeights = new ArrayList<Double>();
            // Assign VehicleCapacity Data
            vehicleCapacity.setCapacity(vehicleRow.value().getInt("capacity"));
            vehicleCapacity.setVehicleId(vehicleRow.value().getLong("driverId"));
            // Assing Orders Weights
            JsonArray vehicleOrders = (JsonArray) vehicleRow.value().get("jobOrders");
            /// Loop in Orders
            for (int index = 0; index < vehicleOrders.size(); index++) {
                JsonObject vehicleOrder = vehicleOrders.getObject(index);
                Double vehicleWeight = vehicleOrder.getDouble("actualWeight");
                // Add it to List
                vehicleOrderWeights.add(vehicleWeight);
            }//end loop
            vehicleCapacity.setOrderWeights(vehicleOrderWeights);
        }//end if
        /// return results
        return vehicleCapacity;
    }

    public VehicleLocation getVehicleLocation(Long driverId) {
        Long currentTimeInMillis = getTimeInMillis();
        /// NearByVehicle Query
        String query = "SELECT location FROM " + BUCKET_NAME + " WHERE " +
                " driverId = " + driverId + " AND workShiftFrom<="
                + currentTimeInMillis + " AND workShiftTo>=" + currentTimeInMillis;
        /// Getting List of Vehicles
        List<N1qlQueryRow> vehicleListRows = couchbaseTemplate
                .queryN1QL(
                        N1qlQuery.simple(query))
                .allRows();
        //Init VehicleLocation
        VehicleLocation vehicleLocation = null;
        //// Check if Result returned
        if (!vehicleListRows.isEmpty()) {
            // Getting the Column
            N1qlQueryRow vehicleRow = vehicleListRows.get(0);
            // Filling VehicleLocation

            if (vehicleRow.value().getObject("location") != null) {
                vehicleLocation = new VehicleLocation();
                vehicleLocation.setLatitude(vehicleRow.value().getObject("location").getString("latitude"));
                vehicleLocation.setLongitude(vehicleRow.value().getObject("location").getString("longitude"));
            }//end if
        }//end if
        // return result
        return vehicleLocation;
    }

    public List<Long> getAvailablePickupHubVehicles(Long hubId) {
        //Getting Current TimeMillis
        Long currentTimeMillis = getTimeInMillis();
        // NearByVehicle Query
        String query = "SELECT driverId FROM " + BUCKET_NAME + " WHERE " + " userType = 'CORPORATE_DRIVER'" +
                " AND hubId = " + hubId + " AND purpose='ON_DEMAND_SERVICES' AND (vehicleStatus = 'available' OR vehicleStatus = 'AVAILABLE') AND workShiftFrom < " + currentTimeMillis +
                " AND workShiftTo > " + currentTimeMillis;
        /// Getting List of Vehicles
        List<Long> vehicleIds = new ArrayList<Long>();

        List<N1qlQueryRow> vehicleListRows = couchbaseTemplate
                .queryN1QL(
                        N1qlQuery.simple(query))
                .allRows();
        /// Filling Vehicle List
        if (!vehicleListRows.isEmpty()) {
            for (N1qlQueryRow row : vehicleListRows) {
                vehicleIds.add(row.value().getLong("driverId"));
            }//end For-Each
        }//end if C
        // return result
        return vehicleIds;
    }//end getNearByVehicles Function

    public List<Long> getAvailablePickupFreelanceVehicles() {
        //Getting Current TimeMillis
        Long currentTimeMillis = getTimeInMillis();
        // NearByVehicle Query
        String query = "SELECT driverId FROM " + BUCKET_NAME + " WHERE " + " userType = 'FREELANCER_DRIVER'" +
//                " hubId = " + null
                " AND purpose='ON_DEMAND_SERVICES' AND (vehicleStatus = 'available' OR vehicleStatus = 'AVAILABLE') AND workShiftFrom < " + currentTimeMillis +
                " AND workShiftTo > " + currentTimeMillis;
        /// Getting List of Vehicles
        List<Long> vehicleIds = new ArrayList<Long>();

        List<N1qlQueryRow> vehicleListRows = couchbaseTemplate
                .queryN1QL(
                        N1qlQuery.simple(query))
                .allRows();
        /// Filling Vehicle List
        if (!vehicleListRows.isEmpty()) {
            for (N1qlQueryRow row : vehicleListRows) {
                vehicleIds.add(row.value().getLong("driverId"));
            }//end For-Each
        }//end if C
        // return result
        return vehicleIds;
    }//end getNearByVehicles Function


    public Long getVehicleForDriver(Long driverId) {
        //Getting Current TimeMillis
        Long currentTimeMillis = getTimeInMillis();
        // init the VehicleId
        Long vehicleId = null;
        /// NearByVehicle Query
        String query = "SELECT vehicleId FROM " + BUCKET_NAME + " WHERE " +
                " driverId = " + driverId + "AND (workShiftFrom < " + currentTimeMillis +
                ") AND (workShiftTo > " + currentTimeMillis + ")";
        /// Getting List of Vehicles
        List<N1qlQueryRow> vehicleListRows = couchbaseTemplate
                .queryN1QL(
                        N1qlQuery.simple(query))
                .allRows();
        //// Check if Result returned
        if (!vehicleListRows.isEmpty()) {
            // Getting the Column
            N1qlQueryRow vehicleRow = vehicleListRows.get(0);
            // Filling VehicleLocation
            vehicleId = vehicleRow.value().getLong("vehicleId");

        }//end if
        // return result
        return vehicleId;
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
