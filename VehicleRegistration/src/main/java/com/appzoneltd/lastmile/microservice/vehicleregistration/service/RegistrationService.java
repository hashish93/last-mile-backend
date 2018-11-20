package com.appzoneltd.lastmile.microservice.vehicleregistration.service;

import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.appzoneltd.lastmile.microservice.vehicleregistration.dao.Location;
import com.appzoneltd.lastmile.microservice.vehicleregistration.dao.RegistrationModel;
import com.appzoneltd.lastmile.microservice.vehicleregistration.dao.RegistrationRepository;
import com.appzoneltd.lastmile.microservice.vehicleregistration.dao.UsersJpaRepository;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;

/**
 * @author alaa.nabil
 */
@Service
public class RegistrationService {

    private final static Logger LOGGER = LoggerFactory.getLogger(RegistrationService.class);
    private final RegistrationRepository registrationRepository;
    private final UsersJpaRepository usersRepository;
    private final CouchbaseTemplate couchbaseTemplate;

    @Autowired
    private PrincipalService principalService;
    
    @Autowired
    public RegistrationService(RegistrationRepository registrationRepository, UsersJpaRepository usersRepository,
                               CouchbaseTemplate couchbaseTemplate) {
        super();
        this.registrationRepository = registrationRepository;
        this.usersRepository = usersRepository;
        this.couchbaseTemplate = couchbaseTemplate;
    }

    public RegistrationModel saveOrUpdate(RegistrationModel model) {
        Date now = new Date();
        now.setYear(1970);
        now.setMonth(1);
        now.setDate(1);
        model.set_ID(Utils.generateUUID());

        String id = couchbaseTemplate.queryN1QL(N1qlQuery
                .simple("SELECT META(vehicle_tracking).id FROM " + couchbaseTemplate.getCouchbaseBucket().name()
                        + " WHERE vehicleId=" + model.getVehicleId() + " AND workShiftFrom<="
                        + now.toInstant().toEpochMilli() + " AND workShiftTo>=" + now.toInstant().toEpochMilli()))
                .allRows().get(0).value().getString("id");

        RegistrationModel checkedExistedEntity = null;
        try {
            checkedExistedEntity = new com.fasterxml.jackson.databind.ObjectMapper().readValue(
                    couchbaseTemplate
                            .queryN1QL(N1qlQuery.simple("SELECT * FROM " + couchbaseTemplate.getCouchbaseBucket().name()
                                    + " WHERE vehicleId=" + model.getVehicleId() + " AND workShiftFrom<="
                                    + now.toInstant().toEpochMilli() + " AND workShiftTo>="
                                    + now.toInstant().toEpochMilli()))
                            .allRows().get(0).value().getObject(couchbaseTemplate.getCouchbaseBucket().name())
                            .toString(),
                    RegistrationModel.class);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        checkedExistedEntity.set_ID(Long.parseLong(id));

        if (checkedExistedEntity != null)
            registrationRepository.delete(checkedExistedEntity.get_ID());
        return registrationRepository.save(model);
    }

    /**
     * @param firebaseToken
     * @param principal
     * @return
     */
    public RegistrationModel updateToken(String firebaseToken, Principal principal) {
        Date now = new Date();
        now.setYear(1970);
        now.setMonth(1);
        now.setDate(1);
        Long driverId = getDriverId(principal);
        System.out.println(">>>>>>>DRIVER ID>>>>>>>>" + driverId);

        List<N1qlQueryRow> allRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple("SELECT META(vehicle_tracking).id FROM "
                + couchbaseTemplate.getCouchbaseBucket().name() + " WHERE driverId=" + driverId + " AND workShiftFrom<="
                + now.toInstant().toEpochMilli() + " AND workShiftTo>=" + now.toInstant().toEpochMilli())).allRows();

        if (allRows == null || allRows.isEmpty())
            return null;

        String id = allRows.get(0).value().getString("id");
        RegistrationModel model = null;
        try {
            model = new com.fasterxml.jackson.databind.ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .readValue(
                            couchbaseTemplate
                                    .queryN1QL(N1qlQuery.simple("SELECT * FROM " + couchbaseTemplate.getCouchbaseBucket().name()
                                            + " WHERE driverId=" + driverId + " AND workShiftFrom <="
                                            + now.toInstant().toEpochMilli() + " AND workShiftTo >="
                                            + now.toInstant().toEpochMilli()))
                                    .allRows().get(0).value().getObject(couchbaseTemplate.getCouchbaseBucket().name())
                                    .toString(),
                            RegistrationModel.class);
        } catch (JsonParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JsonMappingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        model.set_ID(Long.parseLong(id));
        System.out.println(">>>>>>>MODEL>>>>>>>>" + model);
        if (model != null)
            model.setFirebaseToken(firebaseToken);

        return registrationRepository.save(model);
    }

    protected Long getDriverId(Principal principal) {
    	return principalService.getUserId(principal);
    }

    public RegistrationModel updateLocation(String longitude, String latitude, Principal principal) {
        Date now = new Date();
        now.setYear(1970);
        now.setMonth(1);
        now.setDate(1);
        Long driverId = getDriverId(principal);
        // RegistrationModel model =
        // registrationRepository.findByDriverId(driverId);

        // RegistrationModel model = (RegistrationModel)
        // couchbaseTemplate.findByN1QL(N1qlQuery.simple(
        // "SELECT * FROM " + couchbaseTemplate.getCouchbaseBucket().name() + "
        // WHERE driverId=" + driverId),
        // RegistrationModel.class);
        List<N1qlQueryRow> allRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple("SELECT META(vehicle_tracking).id FROM "
                + couchbaseTemplate.getCouchbaseBucket().name() + " WHERE driverId=" + driverId + " AND workShiftFrom<="
                + now.toInstant().toEpochMilli() + " AND workShiftTo>=" + now.toInstant().toEpochMilli())).allRows();

        if (allRows == null || allRows.isEmpty())
            return null;

        String id = allRows.get(0).value().getString("id");

        RegistrationModel model = null;
        try {
            model = new com.fasterxml.jackson.databind.ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).
                    readValue(
                            couchbaseTemplate
                                    .queryN1QL(N1qlQuery.simple("SELECT * FROM " + couchbaseTemplate.getCouchbaseBucket().name()
                                            + " WHERE driverId=" + driverId + " AND workShiftFrom<="
                                            + now.toInstant().toEpochMilli() + " AND workShiftTo>="
                                            + now.toInstant().toEpochMilli()))
                                    .allRows().get(0).value().getObject(couchbaseTemplate.getCouchbaseBucket().name())
                                    .toString(),
                            RegistrationModel.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.set_ID(Long.parseLong(id));

        System.out.println(">>>>>>>DRIVER ID>>>>>>>>" + driverId);
        if (model != null)
            model.setLocation(new Location(latitude, longitude));

        return registrationRepository.save(model);
    }

    public RegistrationModel updateLocationAndFirebase(String longitude, String latitude, String firebaseToken, Principal principal) {

        System.out.println("inside >>>>>>>> updateLocationAndFirebase");
        Date now = new Date();
        now.setYear(1970);
        now.setMonth(1);
        now.setDate(1);
        Long driverId = getDriverId(principal);

        List<N1qlQueryRow> allRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple("SELECT META(vehicle_tracking).id FROM "
                + couchbaseTemplate.getCouchbaseBucket().name() + " WHERE driverId=" + driverId
                + " AND workShiftFrom<="
                + now.toInstant().toEpochMilli() + " AND workShiftTo>=" + now.toInstant().toEpochMilli()))
                .allRows();

        if (allRows == null || allRows.isEmpty()) {
            System.out.println("not found on couchabse .. will return null");
            return null;
        }

        String id = allRows.get(0).value().getString("id");
        System.out.println(">>>>>>>>> found driver id :: " + id);
        RegistrationModel model = null;
        try {
            model = new com.fasterxml.jackson.databind.ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).readValue(
                    couchbaseTemplate
                            .queryN1QL(N1qlQuery.simple("SELECT * FROM " + couchbaseTemplate.getCouchbaseBucket().name()
                                    + " WHERE driverId=" + driverId + " AND workShiftFrom<="
                                    + now.toInstant().toEpochMilli() + " AND workShiftTo>="
                                    + now.toInstant().toEpochMilli()))
                            .allRows().get(0).value().getObject(couchbaseTemplate.getCouchbaseBucket().name())
                            .toString(),
                    RegistrationModel.class);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        model.set_ID(Long.parseLong(id));

        LOGGER.info("DRIVER ID : {} ,  ACTIVE VEHIVLE ID : {} , LOCATION : {},{} , FIREBASETOKEN : {}", driverId, id, latitude, longitude, firebaseToken);
        if (model != null) {
        	Location location = new Location(latitude, longitude);
        	System.out.println(location.toString());
            model.setLocation(location);
            model.setFirebaseToken(firebaseToken);
        }
        System.out.println("will save model to couchbase");
        return registrationRepository.save(model);
    }

    public RegistrationModel removeToken(String firebaseToken, Principal principal) {
        Date now = new Date();
        now.setYear(1970);
        now.setMonth(1);
        now.setDate(1);
        Long driverId = getDriverId(principal);
        List<N1qlQueryRow> allRows = couchbaseTemplate.queryN1QL(N1qlQuery.simple("SELECT META(vehicle_tracking).id FROM "
                + couchbaseTemplate.getCouchbaseBucket().name() + " WHERE driverId=" + driverId )).allRows();

        if (allRows == null || allRows.isEmpty())
            return null;

        String id = allRows.get(0).value().getString("id");
        RegistrationModel model = null;
        try {
            model = new com.fasterxml.jackson.databind.ObjectMapper()
                    .readValue(
                            couchbaseTemplate
                                    .queryN1QL(N1qlQuery.simple("SELECT * FROM " + couchbaseTemplate.getCouchbaseBucket().name()
                                            + " WHERE driverId=" + driverId ))
                                    .allRows().get(0).value().getObject(couchbaseTemplate.getCouchbaseBucket().name())
                                    .toString(),
                            RegistrationModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.error(e.getMessage());
            return null;
        }
        if (model != null) {
            model.set_ID(Long.parseLong(id));
            if (firebaseToken.equals(model.getFirebaseToken()))
                model.setFirebaseToken(null);
            return registrationRepository.save(model);
        }
        return model;
    }
}
