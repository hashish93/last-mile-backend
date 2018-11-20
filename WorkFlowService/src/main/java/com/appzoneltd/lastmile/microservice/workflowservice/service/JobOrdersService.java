package com.appzoneltd.lastmile.microservice.workflowservice.service;

import com.appzoneltd.lastmile.microservice.workflowservice.dao.RegistrationRepository;
import com.appzoneltd.lastmile.microservice.workflowservice.model.JobOrder;
import com.appzoneltd.lastmile.microservice.workflowservice.model.RegistrationModel;
import com.couchbase.client.java.document.json.JsonObject;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class JobOrdersService {

    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private CouchbaseTemplate couchbaseTemplate;

    @Autowired
    private ObjectMapper mapper;

    public RegistrationModel findRegisterationModel(Long driverId) {

        // Getting Current TimeMillis
        Long currentTimeMillis = getTimeInMillis();
        /// Buildin Query
        String query = "SELECT META(vehicle_tracking).id,vehicle_tracking FROM "
                + couchbaseTemplate.getCouchbaseBucket().name() + " WHERE driverId = " + driverId
                + " AND (workShiftFrom < " + currentTimeMillis + ") AND (workShiftTo > " + currentTimeMillis + ")";
        // Getting RegisterationModel
        JsonObject response = couchbaseTemplate.queryN1QL(N1qlQuery.simple(query)).allRows().get(0).value();

        System.out.println(">>> RESPONSE " + response.toString());

        JsonObject vehicleTracking = response.getObject("vehicle_tracking");
        Long id = Long.parseLong(response.getString("id"));

        RegistrationModel registrationModel = null;
        try {
            registrationModel = mapper.readValue(vehicleTracking.toString(), RegistrationModel.class);
            registrationModel.set_ID(id);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("IN DAO FIND " + registrationModel.toString());

        return registrationModel;
    }/// end findRegisterationModel

    public void assignJobOrderForDriver(Long driverId, JobOrder jobOrder) {
        /// Getting Model
        RegistrationModel registrationModel = findRegisterationModel(driverId);
        // Getting List of All
        jobOrder.setOrderStatus("AWAITING_PICKUP");

        System.out.println(">>>>>>> REGISTERATION MODEL HERE FROM COUCHBASE ");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> : " + registrationModel.toString());

        if (registrationModel != null) {
            // getting JobOrders
            List<JobOrder> jobOrders = registrationModel.getJobOrders();

            if (jobOrders == null) {
                jobOrders = new ArrayList<>();
            } // end if
            jobOrder.setPriority(jobOrders.size() + 1);
            jobOrders.add(jobOrder);
            // Setting to Model
            registrationModel.setJobOrders(jobOrders);
            // Updating the Data
            registrationModel = registrationRepository.save(registrationModel);
            System.out.println("AFTER " + registrationModel.toString());
        } // end if

    }// end Method

    public void changeRequestStatus(Long driverId, Long requestId, String status) {
        /// Getting Model
        RegistrationModel registrationModel = findRegisterationModel(driverId);

        System.out.println(">>>>>>> REGISTERATION MODEL HERE FROM COUCHBASE ");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>> : " + registrationModel.toString());

        if (registrationModel != null) {
            // getting JobOrders
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>GET JOb orders"
                    + registrationModel.getJobOrders().size());
            for (JobOrder jobOrder : registrationModel.getJobOrders()) {
                System.out.println(":::::: JOB ORDER :::::" + jobOrder.getJobOrderId());
                if (requestId.compareTo(jobOrder.getJobOrderId()) == 0) {
                    /// Set Status
                    jobOrder.setOrderStatus(status);
                    // Save Result
                    registrationModel = registrationRepository.save(registrationModel);
                    System.out.println("AFTER>>>>>>>>>>>>>>>>>>>>>>>>> " + registrationModel.toString());
                    break;
                } // end if condition
            } // end for Loop
        } // end if

    }// end Method

    public void deleteCancelledRequest(Long requestId) throws JsonParseException, JsonMappingException, IOException {
        RegistrationModel registrationModel = null;
        boolean isDone = false;
        String query = "SELECT META(vehicle_tracking).id, vehicle_tracking FROM "
                + couchbaseTemplate.getCouchbaseBucket().name();

        List<N1qlQueryRow> allRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple(query)).allRows();

        for (N1qlQueryRow n1qlQueryRow : allRows) {
            registrationModel = mapper.readValue(n1qlQueryRow.value().getObject("vehicle_tracking").toString(),
                    RegistrationModel.class);
            registrationModel.set_ID(Long.parseLong(n1qlQueryRow.value().getString("id")));

            for (JobOrder jobOrder : registrationModel.getJobOrders()) {
                if (jobOrder.getJobOrderId().equals(requestId)) {
                    registrationModel.getJobOrders().remove(jobOrder);
                    registrationRepository.save(registrationModel);
                    isDone = true;
                    break;
                }
            }
            if (isDone)
                break;
        }
    }

    private Long getTimeInMillis() {
        Date date = new Date();
        date.setYear(1970);
        date.setMonth(1);
        date.setDate(1);
        date.setSeconds(1);
        return date.toInstant().toEpochMilli();
    }

}
