package com.appzoneltd.lastmile.microservice.pickuprequest.service;

import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.PackageEntity;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.PickupRequestEntity;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.activevehicle.RegistrationModel;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.activevehicle.RegistrationRepository;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.PackageJpaRepository;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.PickupRequestJpaRepository;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.UsersRepository;
import com.appzoneltd.lastmile.microservice.pickuprequest.dao.repo.activevehicle.ActiveVehicleJpaRepository;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.CustomerDtoMapper;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.CustomerRequestDetails;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.DriverInfo;
import com.appzoneltd.lastmile.microservice.pickuprequest.dto.MyPrincipal;
import com.couchbase.client.java.query.N1qlQuery;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * Created by alaa.nabil on 4/3/2017.
 */
@Service
public class CustomerRequestDetailsServiceImpl implements CustomerRequestDetailsService {
    private final UsersRepository usersRepository;
    private final PickupRequestJpaRepository pickupRequestJpaRepository;
    private final RequestHistoryJpaRepository requestHistoryJpaRepository;
    private final PackageJpaRepository packageJpaRepository;
    private final RegistrationRepository registrationRepository;
    private final ActiveVehicleJpaRepository activeVehicleJpaRepository;
    private final CouchbaseTemplate couchbaseTemplate;
    private final CustomerDtoMapper dtoMapper;
    private final ObjectMapper objectMapper;

    @Autowired
    public CustomerRequestDetailsServiceImpl(UsersRepository usersRepository, PickupRequestJpaRepository pickupRequestJpaRepository, RequestHistoryJpaRepository requestHistoryJpaRepository, PackageJpaRepository packageJpaRepository, RegistrationRepository registrationRepository, ActiveVehicleJpaRepository activeVehicleJpaRepository, CouchbaseTemplate couchbaseTemplate, CustomerDtoMapper dtoMapper) {
        this.usersRepository = usersRepository;
        this.pickupRequestJpaRepository = pickupRequestJpaRepository;
        this.requestHistoryJpaRepository = requestHistoryJpaRepository;
        this.packageJpaRepository = packageJpaRepository;
        this.registrationRepository = registrationRepository;
        this.activeVehicleJpaRepository = activeVehicleJpaRepository;
        this.couchbaseTemplate = couchbaseTemplate;
        this.dtoMapper = dtoMapper;
        objectMapper = new ObjectMapper();
    }

    @Override
    public CustomerRequestDetails getRequestDetailsForCustomer(Long requestId) {
        return dtoMapper.requestToCustomerRequestDetails(pickupRequestJpaRepository.findOne(requestId));
    }

    @Override
    public CustomerRequestDetails tracePackageWithTrackingNumberOrNickname(String identifier, String principal) {
        if (identifier == null || identifier.equalsIgnoreCase(""))
            return null;

        CustomerRequestDetails customerRequestDetails = null;
        Long userId = getUserId(principal);
        List<PackageEntity> packageEntities = packageJpaRepository.findByNicknameOrTrackingNumber(identifier, identifier);
        PickupRequestEntity pickupRequestEntity = null;
        for (PackageEntity packageEntity : packageEntities) {
            pickupRequestEntity = pickupRequestJpaRepository.findOne(packageEntity.getListOfRequestHistory().get(0).getRequestId());
            if (pickupRequestEntity.getRequesterId().equals(userId)) {
                customerRequestDetails = dtoMapper.requestToCustomerRequestDetails(pickupRequestEntity);
                break;
            }
            if (pickupRequestEntity.getRecipientId().equals(userId)) {
                customerRequestDetails = dtoMapper.requestToCustomerRequestDetails(pickupRequestEntity);
                customerRequestDetails.setRequestType("DELIVERY");
                break;
            }

        }

        return customerRequestDetails;
    }

    @Override
    public DriverInfo getDriverInfo(Long requestId) {
        List<RegistrationModel> registrationModels = couchbaseTemplate.findByN1QLProjection(N1qlQuery.simple("SELECT META(vehicle_tracking).id AS _ID, rating, jobOrders FROM " + couchbaseTemplate.getCouchbaseBucket().name()), RegistrationModel.class);
        RegistrationModel registrationModel1 = registrationModels.parallelStream().filter(registrationModel -> registrationModel.getJobOrders().parallelStream().anyMatch(jobOrder -> requestId.equals(jobOrder.getJobOrderId())))
                .findFirst().get();
        return dtoMapper.toDriverInfo(registrationModel1);
    }

    private Long getUserId(String credentials) {
        MyPrincipal myPrincipal = null;
        try {

            myPrincipal = objectMapper.readValue(credentials, MyPrincipal.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myPrincipal != null ? myPrincipal.getUserId() : null;
    }
}
