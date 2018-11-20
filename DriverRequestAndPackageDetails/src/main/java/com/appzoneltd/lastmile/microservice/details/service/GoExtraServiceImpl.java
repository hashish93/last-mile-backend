package com.appzoneltd.lastmile.microservice.details.service;

import com.appzoneltd.lastmile.microservice.details.dao.entity.NearByVehicleModel;
import com.appzoneltd.lastmile.microservice.details.dao.entity.OrderStatus;
import com.appzoneltd.lastmile.microservice.details.dao.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.PickupRequestTypeEntity;
import com.appzoneltd.lastmile.microservice.details.dao.repos.*;
import com.appzoneltd.lastmile.microservice.details.dto.JobOrder;
import com.appzoneltd.lastmile.microservice.details.dto.Location;
import com.appzoneltd.lastmile.microservice.details.dto.MyPrincipal;
import com.couchbase.client.java.query.N1qlQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by alaa.nabil on 5/18/2017.
 */
@Service
public class GoExtraServiceImpl implements GoExtraService {


    private final NearByVehicleDao nearByVehicleDao;
    private final PackageJpaRepository packageRepository;
    private final PickupRequestJpaRepository pickupRequestRepository;
    private final PickupRequestTypeJpaRepository pickupRequestTypeRepository;
    private final CouchbaseTemplate couchbaseTemplate;
    private final UsersJpaRepository usersRepository;
    private final ActiveVehicleJpaRepository activeVehicleJpaRepository;
    private final RequestHistoryJpaRepository requestHistoryJpaRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public GoExtraServiceImpl(NearByVehicleDao nearByVehicleDao, PackageJpaRepository packageRepository, PickupRequestJpaRepository pickupRequestRepository, PickupRequestTypeJpaRepository pickupRequestTypeRepository, CouchbaseTemplate couchbaseTemplate, UsersJpaRepository usersRepository, ActiveVehicleJpaRepository activeVehicleJpaRepository, RequestHistoryJpaRepository requestHistoryJpaRepository) {
        this.nearByVehicleDao = nearByVehicleDao;
        this.packageRepository = packageRepository;
        this.pickupRequestRepository = pickupRequestRepository;
        this.pickupRequestTypeRepository = pickupRequestTypeRepository;
        this.couchbaseTemplate = couchbaseTemplate;
        this.usersRepository = usersRepository;
        this.activeVehicleJpaRepository = activeVehicleJpaRepository;
        this.requestHistoryJpaRepository = requestHistoryJpaRepository;
        objectMapper = new ObjectMapper();
    }

    @Override
    public List<JobOrder> getUnPickedGoExtraOrders(Principal principal) {
        Long driverId = getUserId(principal.getName());
        Long activeVehicleId = getActiveVehicleId(driverId, now());
        if (activeVehicleId == null)
            throw new RuntimeException("not.active.vehicle");
        if (!isOndemandVehicle(activeVehicleId))
            throw new RuntimeException("not.ondemand.vehicle");

        List<NearByVehicleModel> nearByVehicleModels = nearByVehicleDao.findAllByActiveVehicleIdAndResponseNot(driverId, "ACCEPT");
        List<JobOrder> jobOrders = nearByVehicleModels.parallelStream().mapToLong(model -> model.getRequestId()).boxed()
                .map(requestId -> pickupRequestRepository.findByPickupRequestIdAndPickupRequestTypeAndRequestStatus(requestId, getOndemandRequestTypeEntity(), OrderStatus.ACTION_NEEDED.name()))
                .map(pickupRequestEntity -> {
                    PackageEntity packageEntity = requestHistoryJpaRepository.findByRequestId(pickupRequestEntity.getPickupRequestId()).get(0).getPackageEntity();
                    JobOrder jobOrder = new JobOrder();
                    jobOrder.setId(pickupRequestEntity.getPickupRequestId());
                    jobOrder.setRequestType("PICKUP");
                    jobOrder.setPackageId(packageEntity.getPackageId());
                    jobOrder.setPackageType(packageEntity.getPackageType().getPackagetype());
                    jobOrder.setAddress(pickupRequestEntity.getPickupformatedaddress());
                    jobOrder.setLocation(new Location(Double.parseDouble(pickupRequestEntity.getPickuplatitude()), Double.parseDouble(pickupRequestEntity.getPickuplongitude())));
                    jobOrder.setWeight(packageEntity.getActualweight());
                    // NOT MAJOR PARAMETERS. (NOT USED IN ONDEMAND REQUEST)
                    jobOrder.setTimeFrom(pickupRequestEntity.getTimeFrom());
                    jobOrder.setTimeTo(pickupRequestEntity.getTimeTo());
                    jobOrder.setPriority(0);
                    jobOrder.setTiming(null);
                    jobOrder.setRejectionReason(null);
                    jobOrder.setResponse(null);
                    return jobOrder;
                }).collect(Collectors.toList());


//        List<PickupRequestEntity> pickupRequestEntities = pickupRequestRepository.findByPickupRequestTypeAndRequestStatus(getOndemandRequestTypeEntity(), OrderStatus.ACTION_NEEDED.name());
//        List<JobOrder> jobOrders = pickupRequestEntities.parallelStream().map(pickupRequestEntity -> {
//            PackageEntity packageEntity = requestHistoryJpaRepository.findByRequestId(pickupRequestEntity.getPickupRequestId()).get(0).getPackageEntity();
//            JobOrder jobOrder = new JobOrder();
//            jobOrder.setId(pickupRequestEntity.getPickupRequestId());
//            jobOrder.setRequestType("PICKUP");
//            jobOrder.setPackageId(packageEntity.getPackageId());
//            jobOrder.setPackageType(packageEntity.getPackageType().getPackagetype());
//            jobOrder.setAddress(pickupRequestEntity.getPickupformatedaddress());
//            jobOrder.setLocation(new Location(Double.parseDouble(pickupRequestEntity.getPickuplatitude()), Double.parseDouble(pickupRequestEntity.getPickuplongitude())));
//            jobOrder.setWeight(packageEntity.getActualweight());
//            // NOT MAJOR PARAMETERS. (NOT USED IN ONDEMAND REQUEST)
//            jobOrder.setTimeFrom(pickupRequestEntity.getTimeFrom());
//            jobOrder.setTimeTo(pickupRequestEntity.getTimeTo());
//            jobOrder.setPriority(0);
//            jobOrder.setTiming(null);
//            jobOrder.setRejectionReason(null);
//            jobOrder.setResponse(null);
//            return jobOrder;
//        }).collect(Collectors.toList());
        return jobOrders;
    }

    private Long getActiveVehicleId(Long driverId, Date now) {
        String id = couchbaseTemplate.queryN1QL(N1qlQuery.simple("SELECT META(vehicle_tracking).id FROM "
                + couchbaseTemplate.getCouchbaseBucket().name() + " WHERE driverId=" + driverId + " AND workShiftFrom<="
                + now.toInstant().toEpochMilli() + " AND workShiftTo>=" + now.toInstant().toEpochMilli())).allRows()
                .get(0).value().getString("id");

        return Long.parseLong(id);
    }

    private Date now() {
        Date now = new Date();
        now.setYear(1970);
        now.setMonth(1);
        now.setDate(1);
        now.setSeconds(1);
        return now;
    }

    private boolean isOndemandVehicle(Long activeVehicleId) {
        return VehiclePurpose.ON_DEMAND_SERVICES.name().equalsIgnoreCase(activeVehicleJpaRepository.findOne(activeVehicleId).getVehicle().getPurpose());
    }

    private PickupRequestTypeEntity getOndemandRequestTypeEntity() {
        return pickupRequestTypeRepository.findOne(1L);
    }

    public boolean isActionNeededPackage(Long packageId) {
        return packageRepository.findOne(packageId).getStatus().equalsIgnoreCase(OrderStatus.ACTION_NEEDED.name());
    }

    public boolean isAcceptedPackage(Long packageId) {
        return nearByVehicleDao.findAcceptedVehiclesForPackage(packageId) != null && !nearByVehicleDao.findAcceptedVehiclesForPackage(packageId).isEmpty();
    }

    public Long getUserId(String principal) {
        MyPrincipal myPrincipal = null;
        try {
            myPrincipal = objectMapper.readValue(principal, MyPrincipal.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (myPrincipal != null) {
            return usersRepository.findOne(myPrincipal.getUserId()).getUserId();
        }
        return null;
    }
}
