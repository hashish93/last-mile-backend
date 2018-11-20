package com.appzoneltd.lastmile.microservice.details.service;

import com.appzoneltd.lastmile.microservice.details.dao.couchbase.RegistrationModel;
import com.appzoneltd.lastmile.microservice.details.dao.couchbase.RegistrationRepository;
import com.appzoneltd.lastmile.microservice.details.dao.entity.DriverVerificationEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.FreelancerDriverEntity;
import com.appzoneltd.lastmile.microservice.details.dao.entity.UsersEntity;
import com.appzoneltd.lastmile.microservice.details.dao.repos.*;
import com.appzoneltd.lastmile.microservice.details.dto.ActivateAccountModel;
import com.appzoneltd.lastmile.microservice.details.dto.FreelancerDriver;
import com.appzoneltd.lastmile.microservice.details.dto.MyPrincipal;
import com.appzoneltd.lastmile.microservice.details.service.utils.SMSEmailUtils;
import com.appzoneltd.lastmile.microservice.details.service.utils.VerificationCodeUtil;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class FreelanceDriverServiceImpl implements FreelanceDriverService {

    private final UsersJpaRepository userJpaRepository;
    private final MessageSource messageSource;
    private final CountryCodesJpaRepository countryCodesRepository;
    private final VehicleTypeJpaRepository vehicleTypeRepository;
    private final DriverVerificationRepository driverVerificationRepository;
    private final FreelancerDriverJpaRepository freelancerDriverRepository;
    private final CityJpaRepository cityRepository;
    private final RegistrationRepository registrationRepository;

    private final SMSEmailUtils smsEmailUtils;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Autowired
    private DiscoveryClient discoveryClient;

    public FreelanceDriverServiceImpl(UsersJpaRepository userJpaRepository, MessageSource messageSource,
                                      CountryCodesJpaRepository countryCodesRepository, VehicleTypeJpaRepository vehicleTypeRepository,
                                      DriverVerificationRepository driverVerificationRepository,
                                      FreelancerDriverJpaRepository freelancerDriverRepository, CityJpaRepository cityRepository,
                                      RegistrationRepository registrationRepository,
                                      SMSEmailUtils smsEmailUtils) {

        this.userJpaRepository = userJpaRepository;
        this.messageSource = messageSource;
        this.countryCodesRepository = countryCodesRepository;
        this.vehicleTypeRepository = vehicleTypeRepository;
        this.driverVerificationRepository = driverVerificationRepository;
        this.freelancerDriverRepository = freelancerDriverRepository;
        this.cityRepository = cityRepository;
        this.smsEmailUtils = smsEmailUtils;

        this.registrationRepository = registrationRepository;
    }


    @Override
    public Object saveFreelancerDriver(FreelancerDriver freelancerDriver) {
        Long ID = Utils.generateUUID();

        Map<String, String[]> messages = new HashMap<>();

        List<UsersEntity> allByPhone = userJpaRepository.findAllByPhoneAndUserTypeOrUserType(freelancerDriver.getPhone());
        boolean isMobileExist = allByPhone != null && !allByPhone.isEmpty();

        List<UsersEntity> allByEmail = userJpaRepository.findAllByEmail(freelancerDriver.getEmail().toLowerCase());
        boolean isEmailExist = allByEmail != null && !allByEmail.isEmpty();

        if (isMobileExist) {
            messages.put("PHONE", new String[]{messageSource.getMessage("user.phone.exists", null,
                    "user.phone.exists", LocaleContextHolder.getLocale())});
        }
        if (isEmailExist) {
            messages.put("EMAIL", new String[]{messageSource.getMessage("user.email.exists", null,
                    "user.email.exists", LocaleContextHolder.getLocale())});
        }

        if (!messages.isEmpty()) {
            return messages;
        } else {
            String code = VerificationCodeUtil.generateVerificationCode();

            UsersEntity usersEntity = userJpaRepository.save(new UsersEntity(ID,
                    freelancerDriver.getFirstName() + "  " + freelancerDriver.getLastName(),
                    freelancerDriver.getPassword(), freelancerDriver.getFirstName(), freelancerDriver.getLastName(),
                    countryCodesRepository.findOne(freelancerDriver.getCountryCodeId()), freelancerDriver.getPhone(),
                    freelancerDriver.getEmail().toLowerCase(), Boolean.FALSE, new Date(), "INACTIVE", "", 0L,
                    7L, freelancerDriver.getPersonalPhoto()));

            FreelancerDriverEntity freelancerDriverEntity = null;

            if (usersEntity != null) {

                freelancerDriverEntity = new FreelancerDriverEntity();
                freelancerDriverEntity.setId(ID);
                freelancerDriverEntity.setStatus("NEW");
                freelancerDriverEntity.setNationalIdImage(freelancerDriver.getImageId());
                freelancerDriverEntity.setModel(freelancerDriver.getModel());
                freelancerDriverEntity.setBrand(freelancerDriver.getBrand());
                //				freelancerDriverEntity.setCountry(countryRepository.findOne(freelancerDriver.getCountryId()));
                freelancerDriverEntity.setCity(cityRepository.findOne(freelancerDriver.getCityId()));
                freelancerDriverEntity
                        .setVehicleType(vehicleTypeRepository.findOne(freelancerDriver.getVehicleTypeId()));
                freelancerDriverEntity.setModelYear(freelancerDriver.getModelYear());
                freelancerDriverEntity.setDrivingLicenseId(freelancerDriver.getDrivingLicenseId());
                freelancerDriverRepository.save(freelancerDriverEntity);
            }

            if (freelancerDriverEntity != null) {

                //				driverVerificationRepository.save(new DriverVerificationEntity(ID, false, code));

                String fullNumber = "+" + usersEntity.getCountryCodes().getPhonecode() + usersEntity.getPhone();

                System.out.println("the phone code to send sms to::: " + fullNumber);

                //				smsEmailUtils.sendSms(fullNumber, messageSource.getMessage("verification.code.message", null, LocaleContextHolder.getLocale())
                //						+ ": " + code);
            }

            return freelancerDriverEntity;
        }
    }

    @Override
    public String activateFreelancerDriver(ActivateAccountModel tokenVerification)
            throws Exception {

        UsersEntity usersEntity = userJpaRepository.findByEmailOrPhone(tokenVerification.getPhone(),
                tokenVerification.getPhone());

        if (usersEntity == null) {

            throw new EntityNotFoundException("no driver found with this phone");
        }

        DriverVerificationEntity driverVerificationEntityEntity = driverVerificationRepository.findOne(usersEntity.getUserId());
        if (driverVerificationEntityEntity == null || driverVerificationEntityEntity.getVerified()) {
            throw new EntityNotFoundException("no verificcation info for that driver");
        }

        if (driverVerificationEntityEntity.getVerificationCode().equals(tokenVerification.getToken())) {

            //			dEntity.setVerified(true);
            //			driverVerificationRepository.save(dEntity);
            driverVerificationRepository.delete(driverVerificationEntityEntity.getId());

            usersEntity.setEnabled(true);
            usersEntity.setStatus("ACTIVE");
            userJpaRepository.save(usersEntity);

            return callLogin(usersEntity);


        } else {
            throw new EntityNotFoundException("invalid verfication code");
        }
    }

    @Override
    public void toggleFreelancerOnOff(Principal driverPrincipal, Boolean newOnOffStatus) {
        if (newOnOffStatus == null) {
            return;
        }

        if (newOnOffStatus.booleanValue()) {
            switchONFreelancerDriverToCouchbase(driverPrincipal);
        } else {
            switchOFFFreelancerDriverToCouchbase(driverPrincipal);
        }
    }

    @Async
    public String callLogin(UsersEntity usersEntity) throws Exception {

        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("DRIVERAUTHORIZATIONSERVER");

        if (serviceInstances != null && !serviceInstances.isEmpty()) {

            String URL = "http://DRIVER:DRIVER@" + serviceInstances.get(0).getHost() + ":" + serviceInstances.get(0).getPort()
                    + "/d/oauth/token?client_id=DRIVER&client_secret=DRIVER&grant_type=password"
                    + "&password=" + usersEntity.getPassword() + "&username=" + usersEntity.getPhone();


            System.out.println(URL);

            HttpClient httpclient = HttpClients.createDefault();
            HttpPost httppost = new HttpPost(URL);


            //Execute and get the response.
            HttpResponse response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                return IOUtils.toString(entity.getContent());

            }
            return "";
        } else {
            throw new Exception("couldn't get service instance");
        }

    }

    private void switchONFreelancerDriverToCouchbase(Principal driverPrincipal) {
        final Long userId = extractUserId(driverPrincipal.getName());
        UsersEntity driverAccountInfo = userJpaRepository.findOne(userId);

        if (driverAccountInfo != null &&
                new Long(7).equals(driverAccountInfo.getUserTypeId())) {


            RegistrationModel existingFreelancerCouchbaseModel = getDriverCouchbaseModel(driverAccountInfo.getUserId());

            if (existingFreelancerCouchbaseModel != null) {

                Date now = new Date();
                now.setYear(1970);
                now.setMonth(1);
                now.setDate(1);

                Date endOfDay = new Date();
                now.setYear(1970);
                now.setMonth(1);
                now.setDate(1);
                endOfDay.setHours(23);
                endOfDay.setMinutes(59);
                endOfDay.setSeconds(59);

                existingFreelancerCouchbaseModel.setWorkShiftFrom(now.toInstant().toEpochMilli());
                existingFreelancerCouchbaseModel.setWorkShiftTo(endOfDay.toInstant().toEpochMilli());

                registrationRepository.save(existingFreelancerCouchbaseModel);

            } else {

                RegistrationModel newFreelanceDriverModel = new RegistrationModel();

                newFreelanceDriverModel.set_ID(driverAccountInfo.getUserId());
                newFreelanceDriverModel.setDeviceId(null);
                newFreelanceDriverModel.setDriverId(driverAccountInfo.getUserId());
                newFreelanceDriverModel.setDriverName(driverAccountInfo.getUsername());
                newFreelanceDriverModel.setDriverNumber(driverAccountInfo.getPhone());
                newFreelanceDriverModel.setPurpose("ON_DEMAND_SERVICES");
                newFreelanceDriverModel.setUserType("FREELANCER_DRIVER");

                //!!!!
                newFreelanceDriverModel.setVehicleId(driverAccountInfo.getUserId());

                Date now = new Date();
                now.setYear(1970);
                now.setMonth(1);
                now.setDate(1);

                Date endOfDay = new Date();
                endOfDay.setYear(1970);
                endOfDay.setMonth(1);
                endOfDay.setDate(1);
                endOfDay.setHours(23);
                endOfDay.setMinutes(59);
                endOfDay.setSeconds(59);

                newFreelanceDriverModel.setWorkShiftFrom(now.toInstant().toEpochMilli());
                newFreelanceDriverModel.setWorkShiftTo(endOfDay.toInstant().toEpochMilli());

                newFreelanceDriverModel.set_class(RegistrationModel.class.getName());

                newFreelanceDriverModel.setVehicleStatus("available");
                newFreelanceDriverModel.setWorkShiftId(null);

                FreelancerDriverEntity driverDetails = freelancerDriverRepository.findOne(driverAccountInfo.getUserId());
                newFreelanceDriverModel.setCapacity(driverDetails.getWeight().intValue());
                newFreelanceDriverModel.setVehicleId(driverAccountInfo.getUserId());
                registrationRepository.save(newFreelanceDriverModel);
            }
        }
    }

    private void switchOFFFreelancerDriverToCouchbase(Principal driverPrincipal) {
        final Long userId = extractUserId(driverPrincipal.getName());
        UsersEntity driverAccountInfo = userJpaRepository.findOne(userId);

        if (driverAccountInfo != null &&
                new Long(7).equals(driverAccountInfo.getUserTypeId())) {

            RegistrationModel existingFreelancerToRemove = getDriverCouchbaseModel(driverAccountInfo.getUserId());

            if (existingFreelancerToRemove != null) {
                // add validation if the driver has active orders
                validateForCurrentActiveOrders();
                //then remove the model from couchbase
                registrationRepository.delete(existingFreelancerToRemove);
            }
        }

    }

    private Long extractUserId(String payload) {
        MyPrincipal myPrincipal = null;
        try {
            myPrincipal = objectMapper.readValue(payload, MyPrincipal.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myPrincipal != null ? myPrincipal.getUserId() : null;
    }

    private RegistrationModel getDriverCouchbaseModel(Long userId) {
        return registrationRepository.findOne(userId);
    }

    private void validateForCurrentActiveOrders() {

    }

}
