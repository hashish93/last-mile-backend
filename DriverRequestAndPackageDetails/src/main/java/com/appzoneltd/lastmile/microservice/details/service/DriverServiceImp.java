package com.appzoneltd.lastmile.microservice.details.service;

import com.appzoneltd.lastmile.microservice.details.dao.entity.*;
import com.appzoneltd.lastmile.microservice.details.dao.repos.*;
import com.appzoneltd.lastmile.microservice.details.dto.*;
import com.appzoneltd.lastmile.microservice.details.service.exception.InvalidNewPasswordException;
import com.appzoneltd.lastmile.microservice.details.service.utils.SMSEmailUtils;
import com.appzoneltd.lastmile.microservice.details.service.utils.VerificationCodeUtil;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.couchbase.client.java.query.N1qlQuery;
import com.couchbase.client.java.query.N1qlQueryRow;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.couchbase.core.CouchbaseTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Principal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DriverServiceImp implements DriverService {

    private final UsersJpaRepository userJpaRepository;
    private final ActiveVehicleJpaRepository activeVehicleJpaRepository;

    private final MessageSource messageSource;
    // private final KafkaTemplate<Long, String> kafkaTemplate;
    private final CouchbaseTemplate couchbaseTemplate;

    private final DriverVerificationRepository driverVerificationRepository;
    private final UserEmailVerificationJpaRepository userEmailVerificationRepository;
    private final String fromEmail;
    private final String verifySubject;
    private final DriverForgotPasswordJpaRepository driverForgotPasswordJpaRepository;
    private final FreelancerDriverJpaRepository freelancerDriverRepository;
    private final SMSEmailUtils smsEmailUtils;

    private final CarsBrandsJpaRepository carsBrandsRepository;
    private final CarsModelsJpaRepository carsModelsRepository;
    private final com.fasterxml.jackson.databind.ObjectMapper objectMapper;
    private final Long corporateDriverLongValue;
    private final Long freelancerDriverLongValue;

    @Autowired
    public DriverServiceImp(UsersJpaRepository userJpaRepository, ActiveVehicleJpaRepository activeVehicleJpaRepository,
                            SMSEmailUtils sMSUtils, StaticContentJpaRepository staticContentRepository,
                            DriverVerificationRepository driverVerificationRepository, MessageSource messageSource,
                            DriverForgotPasswordJpaRepository driverForgotPasswordJpaRepository, CouchbaseTemplate couchbaseTemplate,
                            UserEmailVerificationJpaRepository userEmailVerificationRepository,
                            FreelancerDriverJpaRepository freelancerDriverRepository, CarsBrandsJpaRepository carsBrandsRepository,
                            CarsModelsJpaRepository carsModelsRepository, @Value("${email.from}") String fromEmail,
                            @Value("${email.verify-subject}") String verifySubject) {
        this.userJpaRepository = userJpaRepository;
        this.activeVehicleJpaRepository = activeVehicleJpaRepository;

        this.smsEmailUtils = sMSUtils;

        this.messageSource = messageSource;
        // this.kafkaTemplate = kafkaTemplate;
        this.couchbaseTemplate = couchbaseTemplate;
        this.userEmailVerificationRepository = userEmailVerificationRepository;

        this.driverVerificationRepository = driverVerificationRepository;
        this.fromEmail = fromEmail;
        this.verifySubject = verifySubject;


        this.driverForgotPasswordJpaRepository = driverForgotPasswordJpaRepository;
        this.freelancerDriverRepository = freelancerDriverRepository;
        this.carsBrandsRepository = carsBrandsRepository;
        this.carsModelsRepository = carsModelsRepository;
        objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();
        corporateDriverLongValue = 6L;
        freelancerDriverLongValue = 7L;
    }

    @Override
    public DriverProfileDTO getDriverProfileByPrincipal(Principal principal) throws EntityNotFoundException {
        ActiveVehicleEntity activeVehicleEntity = null;
        MyPrincipal myPrincipal = null;
        try {
            myPrincipal = objectMapper.readValue(principal.getName(), MyPrincipal.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsersEntity userEntity = null;
        if (myPrincipal != null) {
            userEntity = userJpaRepository.findOne(myPrincipal.getUserId());
        }

        if (userEntity == null) {
            throw new EntityNotFoundException("INVALID DRIVER");
        }

//		FreelancerDriverEntity freelanceDriver = freelancerDriverRepository.findOne(userEntity.getUserId());

        if (corporateDriverLongValue.equals(userEntity.getUserTypeId())
                || freelancerDriverLongValue.equals(userEntity.getUserTypeId())) {

            //

            // DriverEntity driverEntity =
            // driverJpaRepository.findOne(userEntity.getUserId());

            Long activeVehicleId = getActiveVehicleId(userEntity.getUserId(), getNow());

            if (activeVehicleId != null)
                activeVehicleEntity = activeVehicleJpaRepository.findOne(activeVehicleId);

            DriverProfileDTO driverProfileDTO = new DriverProfileDTO();
            driverProfileDTO.setDriverId(userEntity.getUserId());
            driverProfileDTO.setFirstName(userEntity.getFirstname());
            driverProfileDTO.setLastName(userEntity.getLastname());
            driverProfileDTO.setEmail(userEntity.getEmail());
            driverProfileDTO.setPhoneNumber(userEntity.getPhone());
            driverProfileDTO.setImageId(userEntity.getPersonalPhotoId());
            // driverProfileDTO.setNationalId(driverEntity.getNationalId());

            driverProfileDTO.setDriverType(myPrincipal.getUserType());

            driverProfileDTO.setActiveVehicleId(activeVehicleId);
            driverProfileDTO.setWorkShiftFrom(activeVehicleEntity != null
                    ? LocalTime.of(activeVehicleEntity.getWorkShift().getDateFrom().getHours(),
                    activeVehicleEntity.getWorkShift().getDateFrom().getMinutes())
                    : null);
            driverProfileDTO.setWorkShiftTo(activeVehicleEntity != null
                    ? LocalTime.of(activeVehicleEntity.getWorkShift().getDateTo().getHours(),
                    activeVehicleEntity.getWorkShift().getDateTo().getMinutes())
                    : null);
            driverProfileDTO.setHubName(activeVehicleEntity != null
                    ? activeVehicleEntity.getVehicle().getBuilding().getBuildingname() : null);
            driverProfileDTO.setHubLongitude(
                    activeVehicleEntity != null ? activeVehicleEntity.getVehicle().getBuilding().getLongitude() : null);
            driverProfileDTO.setHubLatitude(
                    activeVehicleEntity != null ? activeVehicleEntity.getVehicle().getBuilding().getLatitude() : null);
            driverProfileDTO.setHubCountryCode(activeVehicleEntity != null
                    ? activeVehicleEntity.getVehicle().getBuilding().getCountryCodes().getPhonecode().toString()
                    : null);
            driverProfileDTO.setHubPhoneNumber(activeVehicleEntity != null
                    ? activeVehicleEntity.getVehicle().getBuilding().getPhoneNumber() : null);

            driverProfileDTO.setPurpose(activeVehicleEntity != null ? activeVehicleEntity.getVehicle().getPurpose() : null);

            if (activeVehicleEntity != null) {
                driverProfileDTO.setPlateNumber(activeVehicleEntity.getVehicle().getPlate());
                driverProfileDTO.setVehicleBrand(activeVehicleEntity.getVehicle().getBrand());
                driverProfileDTO.setVehicleModel(activeVehicleEntity.getVehicle().getModel());

            }

            if (freelancerDriverLongValue.equals(userEntity.getUserTypeId())) {

                FreelancerDriverEntity freelancerDriverEntity = freelancerDriverRepository
                        .findOne(userEntity.getUserId());

//                final DriverVerificationEntity driverVerificationEntity = driverVerificationRepository.findOne(freelancerDriverEntity.getId());

                driverProfileDTO.setFreelanceCityToDriveIn(freelancerDriverEntity.getCity().getNameEn());

                driverProfileDTO.setPlateNumber(freelancerDriverEntity.getPlate());

                driverProfileDTO.setVehicleBrand(
                        carsBrandsRepository.findOne(freelancerDriverEntity.getBrand()).getBrandName());

                driverProfileDTO.setVehicleModel(
                        carsModelsRepository.findOne(
                                freelancerDriverEntity.getModel()).getModelName());

                driverProfileDTO.setVehicleModelYear(freelancerDriverEntity.getModelYear());
                driverProfileDTO.setPurpose(freelancerDriverEntity.getPurpose());
//                driverProfileDTO.setVerified(driverVerificationEntity.getVerified());

            }
            return driverProfileDTO;
        } else {
            throw new EntityNotFoundException("NOT A DRIVER DRIVER " + userEntity.getUserTypeId());
        }

    }


    @Override
    public Boolean forgotPassword(UnverifiedNumber unverifiedNumber)
            throws EntityNotFoundException, JsonProcessingException {
        List<UsersEntity> usersEntities = userJpaRepository.findAllByPhoneAndUserTypeOrUserType(unverifiedNumber.getPhone());
        if (usersEntities == null || usersEntities.isEmpty())
            throw new EntityNotFoundException("DRIVER NOT FOUND");

        String token = VerificationCodeUtil.generateVerificationCode();

        DriverForgotPasswordEntity driverForgotPasswordEntity = new DriverForgotPasswordEntity();
        driverForgotPasswordEntity.setDriverId(usersEntities.get(0).getUserId());
        driverForgotPasswordEntity.setUsers(usersEntities.get(0));
        driverForgotPasswordEntity.setToken(token);
        driverForgotPasswordEntity.setAttempts(3);
        driverForgotPasswordEntity.setCreated(new Date());

        if (driverForgotPasswordJpaRepository.save(driverForgotPasswordEntity) != null) {

            smsEmailUtils.sendSmsById(usersEntities.get(0).getUserId(),
                    messageSource.getMessage("forgot.password.code.message", null, LocaleContextHolder.getLocale())
                            + token);

            return true;
        }
        return false;
    }

    @Override
    public boolean forgotPasswordVerifyToken(ForgetPasswordVerifyToken forgetPasswordVerifyToken)
            throws EntityNotFoundException, ExceededNumberOfAttempts, ForgotPasswordTokenExpired {
        List<UsersEntity> usersEntities = userJpaRepository.findAllByPhoneAndUserTypeOrUserType(forgetPasswordVerifyToken.getPhone());
        if (usersEntities == null || usersEntities.isEmpty())
            throw new EntityNotFoundException("DRIVER NOT FOUND");

        DriverForgotPasswordEntity driverForgotPasswordEntity = driverForgotPasswordJpaRepository
                .findOne(usersEntities.get(0).getUserId());

        if (driverForgotPasswordEntity == null)
            return false;

        if (0 >= driverForgotPasswordEntity.getAttempts()) {
            driverForgotPasswordEntity.setAttempts(driverForgotPasswordEntity.getAttempts() - 1);
            driverForgotPasswordJpaRepository.save(driverForgotPasswordEntity);
            throw new ExceededNumberOfAttempts(
                    messageSource.getMessage("attempts.exceeds", null, LocaleContextHolder.getLocale()));
        }

        LocalTime localTime = LocalTime.now();
        LocalTime savedTime = LocalTime.of(driverForgotPasswordEntity.getCreated().getHours(),
                driverForgotPasswordEntity.getCreated().getMinutes(),
                driverForgotPasswordEntity.getCreated().getSeconds());
        // .ofInstant(customerForgotPasswordEntity.getCreated().toInstant(),
        // ZoneId.systemDefault());
        Duration duration = Duration.between(savedTime, localTime);
        Duration _5min = Duration.ofMinutes(5);
        if (duration.compareTo(_5min) >= 0)
            throw new ForgotPasswordTokenExpired(
                    messageSource.getMessage("forgot.password.token.expired", null, LocaleContextHolder.getLocale()));

        if (!"".equalsIgnoreCase(forgetPasswordVerifyToken.getToken())
                && driverForgotPasswordEntity.getToken().equalsIgnoreCase(forgetPasswordVerifyToken.getToken()))
            return true;
        else {
            driverForgotPasswordEntity.setAttempts(driverForgotPasswordEntity.getAttempts() - 1);
            driverForgotPasswordJpaRepository.save(driverForgotPasswordEntity);
        }

        return false;
    }


    @Override
    public boolean changeCurrentPassword(Principal principal, NewPassword newPassword) throws EntityNotFoundException {

        // List<UsersEntity> usersEntities =
        // userJpaRepository.findAllByPhone(newPassword.getPhone());
        final Long userId = extractUserId(principal.getName());
        UsersEntity currentDriverUserEntity = userJpaRepository.findOne(userId);

        if (currentDriverUserEntity == null) {
            throw new EntityNotFoundException("DRIVER NOT FOUND");
        }

        // if
        // (!currentDriverUserEntity.getUserType().equals(UserType.DRIVER.getValue())
        // ||
        // !currentDriverUserEntity.getUserType().equals(UserType.FREELANCER_DRIVER.getValue()))
        // {
        //
        // throw new EntityNotFoundException("DRIVER NOT FOUND");
        // }

        if (!currentDriverUserEntity.getPassword().equals(newPassword.getOldPassword())) {
            throw new EntityNotFoundException("Old password is invalid");
        }
        // else
        currentDriverUserEntity.setPassword(newPassword.getNewPassword());

        userJpaRepository.save(currentDriverUserEntity);

        return true;
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

    @Override
    public void updateProfileImageByPrincipal(Principal principal, Long newImageId) throws EntityNotFoundException {
        MyPrincipal myPrincipal = null;
        try {
            myPrincipal = objectMapper.readValue(principal.getName(), MyPrincipal.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UsersEntity userEntity = null;
        if (myPrincipal != null) {
            userEntity = userJpaRepository.findOne(myPrincipal.getUserId());
        }
        if (userEntity == null) {
            throw new EntityNotFoundException("INVALID DRIVER");
        } // else

        userEntity.setPersonalPhotoId(newImageId);

        userJpaRepository.save(userEntity);

    }

    @Override
    public Object verifyDriverPhone(Principal principal, String phone)
            throws EntityNotFoundException, JsonProcessingException {

        // String SMS_TOPIC = "SMS-NOTIFICATION-UNREGISTERED";

        MyPrincipal myPrincipal = null;
        try {
            myPrincipal = objectMapper.readValue(principal.getName(), MyPrincipal.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String, String[]> messages = new HashMap<>();

        String code = VerificationCodeUtil.generateVerificationCode().substring(0, 4);

        UsersEntity driverProfile = null;
        if (myPrincipal != null) {
            driverProfile = userJpaRepository.findOne(myPrincipal.getUserId());
        }
        if (driverProfile == null) {
            throw new EntityNotFoundException("DRIVER NOT FOUND");
        }

        List<UsersEntity> allByPhone = userJpaRepository.findAllByPhoneAndUserTypeOrUserType(phone);
        boolean isMobileExist = allByPhone != null && !allByPhone.isEmpty();

        if (isMobileExist) {
            messages.put("PHONE", new String[]{messageSource.getMessage("user.phone.exists", null,
                    "user.phone.exists", LocaleContextHolder.getLocale())});
        }

        if (!messages.isEmpty()) {
            return messages;
        } else {
            StringBuilder phoneNumber = new StringBuilder();
            phoneNumber.append("+");
            phoneNumber.append(driverProfile.getCountryCodes().getPhonecode());
            phoneNumber.append(phone);

            String smsMessageTemplate = "Verfication Code " + code;

            UnRegisteredSmsModel model = new UnRegisteredSmsModel();
            model.setPhoneNumber(phoneNumber.toString());
            model.setMessage(smsMessageTemplate);

            smsEmailUtils.sendSms(model);

            driverVerificationRepository.save(new DriverVerificationEntity(driverProfile.getUserId(), true, code));
        }

        return code;
    }

    @Override
    public boolean updateDriverPhone(Principal principal, String phone, String verificationCode)
            throws EntityNotFoundException {

        MyPrincipal myPrincipal = null;
        try {
            myPrincipal = objectMapper.readValue(principal.getName(), MyPrincipal.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        UsersEntity driverProfile = null;
        if (myPrincipal != null) {
            driverProfile = userJpaRepository.findOne(myPrincipal.getUserId());
        }
        if (driverProfile == null) {
            throw new EntityNotFoundException("DRIVER NOT FOUND");
        }

        DriverVerificationEntity verificationEntity = driverVerificationRepository.findOne(driverProfile.getUserId());
        if (verificationEntity == null) {
            throw new EntityNotFoundException("no verification code info for that driver");
        }

        if (verificationEntity.getVerificationCode().equalsIgnoreCase(verificationCode)) {
            driverProfile.setPhone(phone);
            userJpaRepository.save(driverProfile);
            driverVerificationRepository.delete(driverProfile.getUserId());

            return true;

        }

        return false;

    }

    @Override
    public boolean handleEmailChange(ChangeEmailModel changeEmailModel, Principal principal)
            throws EntityNotFoundException, JsonProcessingException {
        MyPrincipal myPrincipal = null;
        try {
            myPrincipal = objectMapper.readValue(principal.getName(), MyPrincipal.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        UsersEntity usersEntity = null;
        if (myPrincipal != null) {
            usersEntity = userJpaRepository.findOne(myPrincipal.getUserId());
        }
        if (usersEntity == null)
            throw new EntityNotFoundException("DRIVER NOT FOUND");

        if (!changeEmailModel.getPassword().equals(usersEntity.getPassword()))
            throw new EntityNotFoundException("invalid.password");

        if (changeEmailModel.getNewEmail() == null || "".equalsIgnoreCase(changeEmailModel.getNewEmail())) {
            return false;
        }

        if (!userJpaRepository.findAllByEmail(changeEmailModel.getNewEmail()).isEmpty())
            throw new EntityNotFoundException("user.email.exists");

        String code = VerificationCodeUtil.generateVerificationCode();
        UserEmailVerificationEntity userEmailVerificationEntity = userEmailVerificationRepository
                .findOne(usersEntity.getUserId());

        if (userEmailVerificationEntity == null)
            userEmailVerificationEntity = new UserEmailVerificationEntity();

        userEmailVerificationEntity.setId(usersEntity.getUserId());
        userEmailVerificationEntity.setIsVerified(false);
        userEmailVerificationEntity.setEmail(changeEmailModel.getNewEmail());
        userEmailVerificationEntity.setVerificationCode(code);
        userEmailVerificationEntity.setUsers(usersEntity);

        userEmailVerificationRepository.save(userEmailVerificationEntity);


        smsEmailUtils.sendChangeEmailTemplate(usersEntity.getFirstname(), changeEmailModel.getNewEmail(), verifySubject, getBodyPrefix() + " " + code);

        return true;
    }

    private String getBodyPrefix() {
        return messageSource.getMessage("mail.verify.prefix", null,
                "mail.verify.prefix", LocaleContextHolder.getLocale());
    }

    @Override
    public Boolean verifyChangedEmail(String cipher, Principal principal) {
        if (cipher == null || "".equalsIgnoreCase(cipher)) {
            return false;
        }

        MyPrincipal myPrincipal = null;
        try {
            myPrincipal = objectMapper.readValue(principal.getName(), MyPrincipal.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<UserEmailVerificationEntity> userEmailVerificationEntities = userEmailVerificationRepository
                .findAllByVerificationCodeAndIsVerified(cipher, false);

        if (userEmailVerificationEntities != null && userEmailVerificationEntities.size() == 1) {
            UserEmailVerificationEntity userEmailVerificationEntity = userEmailVerificationEntities.get(0);

            if (myPrincipal == null || !myPrincipal.getUserId().equals(userEmailVerificationEntity.getId()))
                return false;

            UsersEntity usersEntity = userJpaRepository.findOne(userEmailVerificationEntity.getId());
            usersEntity.setEmail(userEmailVerificationEntity.getEmail());
            userJpaRepository.save(usersEntity);

            userEmailVerificationEntity.setIsVerified(true);
            userEmailVerificationRepository.save(userEmailVerificationEntity);
            return true;
        }
        return false;
    }

    @Override
    public void setNewPassworsd(String phone, String newPass) throws EntityNotFoundException, InvalidNewPasswordException {

        if (phone == null || phone.trim().isEmpty()) {
            throw new EntityNotFoundException("phone number not provided");
        }
        if (newPass == null || newPass.trim().isEmpty()) {
            throw new InvalidNewPasswordException();
        }
        List<UsersEntity> lista = userJpaRepository.findAllByPhoneAndUserTypeOrUserType(phone);

        if (lista == null || lista.isEmpty()) {
            throw new EntityNotFoundException("phone number not found");
        }

        UsersEntity currentUser = lista.get(0);

        currentUser.setPassword(newPass);

        userJpaRepository.save(currentUser);

    }

    private Long getActiveVehicleId(Long driverId, Date now) {
        List<N1qlQueryRow> n1qlQueryRows = couchbaseTemplate.queryN1QL(N1qlQuery
                .simple("SELECT META(vehicle_tracking).id FROM " + couchbaseTemplate.getCouchbaseBucket().name()
                        + " WHERE driverId=" + driverId + " AND workShiftFrom<=" + now.toInstant().toEpochMilli()
                        + " AND workShiftTo>=" + now.toInstant().toEpochMilli()))
                .allRows();

        if (n1qlQueryRows == null || n1qlQueryRows.isEmpty())
            return null;

        String id = n1qlQueryRows.get(0).value().getString("id");

        return !"".equalsIgnoreCase(id) ? Long.parseLong(id) : null;
    }

    private Date getNow() {
        Date now = new Date();
        now.setYear(1970);
        now.setMonth(1);
        now.setDate(1);
        now.setSeconds(1);
        return now;
    }


}
