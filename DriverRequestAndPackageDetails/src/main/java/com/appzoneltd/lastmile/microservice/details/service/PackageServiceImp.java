package com.appzoneltd.lastmile.microservice.details.service;

import com.appzoneltd.lastmile.microservice.details.dao.couchbase.RegistrationRepository;
import com.appzoneltd.lastmile.microservice.details.dao.entity.ActiveVehicleEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.RequestHistoryEntity;
import com.appzoneltd.lastmile.microservice.details.dao.repos.CustomerJpaRepository;
import com.appzoneltd.lastmile.microservice.details.dao.repos.PickupRequestJpaRepository;
import com.appzoneltd.lastmile.microservice.details.dao.repos.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.details.dao.repos.UsersJpaRepository;
import com.appzoneltd.lastmile.microservice.details.dto.*;
import com.appzoneltd.lastmile.microservice.details.kafka.model.ActiveVehicleModel;
import com.appzoneltd.lastmile.microservice.details.service.component.OnDemandPickupRequest;
import com.appzoneltd.lastmile.microservice.details.service.component.RequestChain;
import com.appzoneltd.lastmile.microservice.details.service.utils.ActiveVehicleUtil;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author alaa.nabil
 */
@Service
public class PackageServiceImp implements PackageService {

    private final RequestHistoryJpaRepository requestHistoryRepository;
    private final PickupRequestJpaRepository pickupRequestRepository;
    private final UsersJpaRepository usersRepository;
    private final CustomerJpaRepository customerJpaRepository;
    //	private final ActiveVehicleJpaRepository activeVehicleJpaRepository;
    //	private final CouchbaseTemplate couchbaseTemplate;
    private final KafkaTemplate<Long, String> kafkaTemplate;
    private final com.fasterxml.jackson.databind.ObjectMapper mapper;
    private final RegistrationRepository registrationRepository;
    private final RequestChain requestChain;
    private final OnDemandPickupRequest onDemandPickupRequest;
    //	private final FreelancerDriverJpaRepository freelancerDriverRepository;
    //	private final CarsBrandsJpaRepository carsBrandsJpaRepository;
    //	private final CarsModelsJpaRepository carsModelsJpaRepository;
    private final ActiveVehicleUtil activeVehicleUtil;

    @Autowired
    public PackageServiceImp(RequestHistoryJpaRepository requestHistoryRepository,
                             PickupRequestJpaRepository pickupRequestRepository, UsersJpaRepository usersRepository,
                             CustomerJpaRepository customerJpaRepository,
                             //			CouchbaseTemplate couchbaseTemplate,
                             //			ActiveVehicleJpaRepository activeVehicleJpaRepository,
                             //			FreelancerDriverJpaRepository freelancerDriverRepository,
                             //			CarsBrandsJpaRepository carsBrandsJpaRepository,
                             //			CarsModelsJpaRepository carsModelsJpaRepository,
                             ActiveVehicleUtil activeVehicleUtil,
                             KafkaTemplate<Long, String> kafkaTemplate,
                             RegistrationRepository registrationRepository,
                             @Qualifier(value = "pickupRequest") RequestChain requestChain, OnDemandPickupRequest onDemandPickupRequest) {
        this.pickupRequestRepository = pickupRequestRepository;
        this.requestHistoryRepository = requestHistoryRepository;
        this.usersRepository = usersRepository;
        this.customerJpaRepository = customerJpaRepository;
        //		this.couchbaseTemplate = couchbaseTemplate;
        //		this.activeVehicleJpaRepository = activeVehicleJpaRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.requestChain = requestChain;
        this.onDemandPickupRequest = onDemandPickupRequest;
        this.mapper = new com.fasterxml.jackson.databind.ObjectMapper();
        this.registrationRepository = registrationRepository;
        //		this.freelancerDriverRepository = freelancerDriverRepository;
        //		this.carsBrandsJpaRepository = carsBrandsJpaRepository;
        //		this.carsModelsJpaRepository = carsModelsJpaRepository;

        this.activeVehicleUtil = activeVehicleUtil;
    }

    @Override
    public Map<String, Object> startNavigation(Principal driver, Long requestId, String requestType) throws EntityNotFoundException, JsonProcessingException {
        if (requestId == null || requestType == null || requestType.equalsIgnoreCase(""))
            throw new EntityNotFoundException("Invalid Parameters of null ");

        Map<String, Object> params = new HashMap<>();

        //		ActiveVehicleEntity activeVehicleEntity = activeVehicleJpaRepository.findOne(getActiveVehicleId(getUserId(driver.getName()), now()));


        ActiveVehicleEntity activeVehicleEntity = activeVehicleUtil.getActiveVehicleInfo(driver);

        List<RequestHistoryEntity> requestHistoryEntities = requestHistoryRepository.findByRequestId(requestId);

        if (requestHistoryEntities == null || requestHistoryEntities.size() == 0)
            throw new EntityNotFoundException("package.notfound");

        Long packageId = requestHistoryEntities.get(0).getPackageEntity().getPackageId();

        Object object = null;
        object = VehiclePurpose.ON_DEMAND_SERVICES.name().equalsIgnoreCase(activeVehicleEntity.getVehicle().getPurpose()) ?
                onDemandPickupRequest.getNotificationModelWithRequestDetailsAndSetHubIdForFreelancerDriver(packageId, requestId, activeVehicleEntity)
                : requestChain.getNotificationModelWithRequestDetails(packageId, requestId, requestType, activeVehicleEntity);

        String customerNumber = null;
        if (object != null && object instanceof NotificationModel) {
            NotificationModel notificationModel = (NotificationModel) object;
            if (!"RETURN".equalsIgnoreCase(requestType)) {
                sendNotification(notificationModel);
            }
            customerNumber = customerJpaRepository.findOne(notificationModel.getUserIds()[0]).getCountryCodes().getPhonecode().toString()
                    + usersRepository.findOne(notificationModel.getUserIds()[0]).getPhone();
        }

        if (object != null && object instanceof UnRegisteredSmsModel) {
            UnRegisteredSmsModel unRegisteredSmsModel = (UnRegisteredSmsModel) object;
            customerNumber = unRegisteredSmsModel.getPhoneNumber();
        }
        if (object != null) {
            activeVehicleUtil.updateActiveVehicleStatus(activeVehicleEntity.getId(), VehicleStatus.ON_ROUTE);
            updateSourceLocation(activeVehicleEntity.getId(), requestId);
        }

        params.put("packageId", packageId);
        params.put("requestId", requestId);
        params.put("requestType", requestType);
        params.put("customerNumber", customerNumber);

        return params;
    }

    @Async
    private ListenableFuture<SendResult<Long, String>> sendNotification(NotificationModel notificationModel) throws JsonProcessingException {
        return kafkaTemplate.send("push-notification", mapper.writeValueAsString(notificationModel));
    }

    private void updateSourceLocation(Long id, Long requestId) throws JsonProcessingException {
//		RegistrationModel registrationModel = registrationRepository.findOne(id);
//		for (JobOrder jobOrder : registrationModel.getJobOrders()) {
//			if (requestId.intValue() == jobOrder.getJobOrderId().intValue()) {
//				jobOrder.setCurrentOrder(true);
//				//                jobOrder.setActiveVehicleAddress(getFormattedAddress(registrationModel.getLocation().getLatitude(), registrationModel.getLocation().getLongitude()));
//			} else
//				jobOrder.setCurrentOrder(false);
//			registrationRepository.save(registrationModel);
//		}
        ActiveVehicleModel activeVehicleModel = new ActiveVehicleModel();
        activeVehicleModel.setActiveVehicleId(id);
        activeVehicleModel.setRequestId(requestId);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>     CALLING UPDATING LOCATION                            >>>>>>>>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        kafkaTemplate.send("DRIVER_SOURCE_POSITION_DETECTION", mapper.writeValueAsString(activeVehicleModel));

    }

    @Override
    public PackageDetails findRequestAndPackageDetailsByPackageId(Principal principal, Long packageId, Long requestId, String requestType)
            throws EntityNotFoundException, JsonProcessingException {
        Long driverId = getUserId(principal.getName());

        //		ActiveVehicleEntity activeVehicleEntity = activeVehicleJpaRepository.findOne(getActiveVehicleId(driverId, now()));

        ActiveVehicleEntity activeVehicleEntity = activeVehicleUtil.getActiveVehicleInfo(principal);


        List<RequestHistoryEntity> requestHistoryEntities = requestHistoryRepository.findByRequestId(requestId);
        if (requestHistoryEntities == null || requestHistoryEntities.size() == 0)
            throw new EntityNotFoundException("package.notfound");


        PackageDetails packageDetails;
        if (VehiclePurpose.ON_DEMAND_SERVICES.name().equalsIgnoreCase(activeVehicleEntity.getVehicle().getPurpose())) {
            packageDetails = onDemandPickupRequest.toPackageDetails(requestId, requestHistoryEntities.get(0).getPackageEntity());
            requestType = "PICKUP";
        } else
            packageDetails = requestChain.toPackageDetails(requestId, requestType,
                    requestHistoryEntities.get(0).getPackageEntity(), driverId);

        Map<String, Object> data = new HashMap<>();
        data.put("packageId", packageDetails.getPackageId());
        data.put("requestId", requestId);
        data.put("requesterId", packageDetails.getRequesterId());
        data.put("recipientId", packageDetails.getRecipientId());
        data.put("driverId", driverId);
        data.put("requestType", requestType);
        data.put("customerFound", true);

        activeVehicleUtil.updateActiveVehicleStatus(activeVehicleEntity.getId(), VehicleStatus.BUSY);

        kafkaTemplate.send(RequestChain.getDriverArrivalAcknowledgmentTopic(requestType), mapper.writeValueAsString(data));

        return packageDetails;
    }

    @Override
    public Long getUserId(String principal) {
        MyPrincipal myPrincipal = null;
        try {

            myPrincipal = mapper.readValue(principal, MyPrincipal.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (myPrincipal != null) {
            return usersRepository.findOne(myPrincipal.getUserId()).getUserId();
        }
        return null;
    }


}
