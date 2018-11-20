package com.appzoneltd.lastmile.microservice.details.service.utils;

import com.appzoneltd.lastmile.microservice.details.dao.couchbase.RegistrationModel;
import com.appzoneltd.lastmile.microservice.details.dao.couchbase.RegistrationRepository;
import com.appzoneltd.lastmile.microservice.details.dao.entity.*;
import com.appzoneltd.lastmile.microservice.details.dao.repos.*;
import com.appzoneltd.lastmile.microservice.details.dto.MyPrincipal;
import com.appzoneltd.lastmile.microservice.details.dto.VehicleStatus;
import com.couchbase.client.java.query.N1qlQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;

@Component
public class ActiveVehicleUtil {

    @Autowired
    private UsersJpaRepository usersRepository;
    @Autowired
    private ActiveVehicleJpaRepository activeVehicleJpaRepository;
    @Autowired
    private RegistrationRepository registrationRepository;
    @Autowired
    private FreelancerDriverJpaRepository freelancerDriverRepository;
    @Autowired
    private CarsBrandsJpaRepository carsBrandsJpaRepository;
    @Autowired
    private CarsModelsJpaRepository carsModelsJpaRepository;
    @Autowired
    private CouchbaseTemplate couchbaseTemplate;
    @Autowired
    private com.fasterxml.jackson.databind.ObjectMapper mapper;


    public ActiveVehicleEntity getActiveVehicleInfo(Principal principal) {
        MyPrincipal myPrincipal = null;
        try {
            myPrincipal = mapper.readValue(principal.getName(), MyPrincipal.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (myPrincipal == null) throw new AssertionError();
        UsersEntity currentDriverUserInfo = usersRepository.findOne(myPrincipal.getUserId());

        RegistrationModel driverCouchbaseModel = getActiveVehicleInfoFromCouchbase(currentDriverUserInfo.getUserId(), now());

        if (driverCouchbaseModel == null) {
            return null;
        }

        if ("FREELANCER_DRIVER".equals(driverCouchbaseModel.getUserType())) {

            FreelancerDriverEntity freelancerDriver = freelancerDriverRepository.findOne(driverCouchbaseModel.getDriverId());
            ActiveVehicleEntity activeVehicleEntity = new ActiveVehicleEntity();
            DriverEntity dE = new DriverEntity();
            dE.setId(driverCouchbaseModel.getDriverId());
            dE.setRating(freelancerDriver.getRating());
            dE.setNationalId(freelancerDriver.getNationalId());
            dE.setUsers(currentDriverUserInfo);
            activeVehicleEntity.setDriver(dE);


            VehicleEntity vEntity = new VehicleEntity();
            vEntity.setVehicleId(driverCouchbaseModel.getVehicleId());

            vEntity.setBrand(
                    carsBrandsJpaRepository.findOne(freelancerDriver.getBrand()).getBrandName());
            vEntity.setModel(
                    carsModelsJpaRepository.findOne(freelancerDriver.getModel()).getModelName());

            vEntity.setChassis(freelancerDriver.getChassis());
            vEntity.setPurpose("ON_DEMAND_SERVICES");
            vEntity.setPlate(freelancerDriver.getPlate());

            BuildingEntity bE = new BuildingEntity();
            bE.setBuildingId(driverCouchbaseModel.getHubId());

            vEntity.setBuilding(bE);

            activeVehicleEntity.setVehicle(vEntity);

            DevicesEntity deviceE = new DevicesEntity();
            deviceE.setPhonenumber(currentDriverUserInfo.getPhone());
            activeVehicleEntity.setDevices(deviceE);

            activeVehicleEntity.setId(driverCouchbaseModel.get_ID());
            WorkShiftEntity wE = new WorkShiftEntity();
            wE.setDateFrom(new Date(driverCouchbaseModel.getWorkShiftFrom()));
            wE.setDateTo(new Date(driverCouchbaseModel.getWorkShiftTo()));
            wE.setId(driverCouchbaseModel.getWorkShiftId());
            activeVehicleEntity.setWorkShift(wE);


            return activeVehicleEntity;

        } else {

            return activeVehicleJpaRepository.findOne(driverCouchbaseModel.get_ID());
        }
    }

    private RegistrationModel getActiveVehicleInfoFromCouchbase(Long driverId, Date now) {

        try {

            RegistrationModel moodel = mapper.readValue(couchbaseTemplate.queryN1QL(N1qlQuery.simple("SELECT * FROM "
                    + couchbaseTemplate.getCouchbaseBucket().name() + " WHERE driverId=" + driverId + " AND workShiftFrom<="
                    + now.toInstant().toEpochMilli() + " AND workShiftTo>=" + now.toInstant().toEpochMilli())).allRows()
                    .get(0).value().getObject(couchbaseTemplate.getCouchbaseBucket().name()).toString(), RegistrationModel.class);

            Long ideee = getActiveVehicleId(driverId, now);
            moodel.set_ID(ideee);
            return moodel;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Date now() {
        Date now = new Date();
        now.setYear(1970);
        now.setMonth(1);
        now.setDate(1);
        now.setSeconds(1);
        return now;
    }

    public Long getActiveVehicleId(Long driverId, Date now) {
        String id = couchbaseTemplate.queryN1QL(N1qlQuery.simple("SELECT META(vehicle_tracking).id FROM "
                + couchbaseTemplate.getCouchbaseBucket().name() + " WHERE driverId=" + driverId + " AND workShiftFrom<="
                + now.toInstant().toEpochMilli() + " AND workShiftTo>=" + now.toInstant().toEpochMilli())).allRows()
                .get(0).value().getString("id");

        return Long.parseLong(id);
    }

    public void updateActiveVehicleStatus(Long activeVehicleId, VehicleStatus vehicleStatus) {
        RegistrationModel model = registrationRepository.findOne(activeVehicleId);
        model.setVehicleStatus(vehicleStatus.name());
        registrationRepository.save(model);
    }
}
