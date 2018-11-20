package com.appzoneltd.lastmile.microservice.freelancedriver.service;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.freelancedriver.dao.*;
import com.appzoneltd.lastmile.microservice.freelancedriver.dto.*;
import com.appzoneltd.lastmile.microservice.freelancedriver.model.*;
import com.appzoneltd.lastmile.microservice.freelancedriver.service.Utils.SMSEmailUtils;
import com.appzoneltd.lastmile.microservice.freelancedriver.service.kafka.KafkaSender;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class FreelanceDriverServiceImpl implements FreelanceDriverService {

    private static String redColor = "#62a1dc";
    private static String blueColor = "#b92a2a";
    private final UsersJpaRepository userJpaRepository;
    private final MessageSource messageSource;
    private final CountryCodesJpaRepository countryCodesRepository;
    private final FreelancerDriverJpaRepository freelancerDriverRepository;
    private final EmailFromJpaRepository emailFromJpaRepository;
    private final Mapper mapper;
    private final TemplateEngine templateEngine;
    private final SMSEmailUtils smsEmailUtils;
    private final ObjectMapper objectMapper;
    private final KafkaSender kafkaSender;
    private final Environment environment;
    private final DriverVerificationRepository driverVerificationRepository;

    @Autowired
    public FreelanceDriverServiceImpl(UsersJpaRepository userJpaRepository, MessageSource messageSource,
                                      CountryCodesJpaRepository countryCodesRepository, FreelancerDriverJpaRepository freelancerDriverRepository,
                                      SMSEmailUtils smsEmailUtils, Mapper mapper, TemplateEngine templateEngine,
                                      EmailFromJpaRepository emailFromJpaRepository, KafkaSender kafkaSender, ObjectMapper objectMapper,
                                      DriverVerificationRepository driverVerificationRepository, Environment environment) {

        this.userJpaRepository = userJpaRepository;
        this.messageSource = messageSource;
        this.countryCodesRepository = countryCodesRepository;
        this.freelancerDriverRepository = freelancerDriverRepository;
        this.smsEmailUtils = smsEmailUtils;
        this.mapper = mapper;
        this.templateEngine = templateEngine;
        this.emailFromJpaRepository = emailFromJpaRepository;
        this.kafkaSender = kafkaSender;
        this.objectMapper = objectMapper;
        this.driverVerificationRepository = driverVerificationRepository;
        this.environment = environment;
    }

    @Override
    public List<FreelancerDriverListInfoDto> getAllFreelancerDriver(int page, int maxResult, OrderBy orderBy) {

        if (page == 0)
            return mapper.mapToFreelancerDriversListPojos((List<FreelancerDriverEntity>) freelancerDriverRepository
                    .findAll(new Sort(Direction.fromString(orderBy.getOrderBy()), "created")));

        PageRequest page1 = new PageRequest(page - 1, maxResult, Direction.fromString(orderBy.getOrderBy()), "created");

        return mapper.mapToFreelancerDriversListPojos(freelancerDriverRepository.findAll(page1).getContent());
    }

    @Override
    public long countAllfreeLancerDriver() {
        return freelancerDriverRepository.count();
    }

    @Override
    public FreelancerDriverDto findFreelancerById(Long id) throws EntityNotFoundException {

        FreelancerDriverDto freelancerDriverDto = new FreelancerDriverDto();

        if (mapper.mapToFreelanceDriverDto(id) == null)
            throw new EntityNotFoundException("Driver Not Found");

        freelancerDriverDto = mapper.mapToFreelanceDriverDto(id);

        return freelancerDriverDto;
    }

    @Override
    public Object acceptFreelancerDriverInfo(FreelancerDriverDto freelancerDriverDto) throws EntityNotFoundException {

        FreelancerDriverEntity freelancerDriverEntity = freelancerDriverRepository
                .findOne(freelancerDriverDto.getUserId());
        UsersEntity user = userJpaRepository.findOne(freelancerDriverDto.getUserId());
        List<Message> message = checkDriverInfoExist(freelancerDriverDto);

        if (!message.isEmpty()) {

            return message;
        } else {

            if (freelancerDriverEntity == null)
                throw new EntityNotFoundException("Driver Not Found");

            mapper.mapToFreelancerDriverEntity(freelancerDriverDto);

            String verificationCode = Utils.codeGenerator().substring(0, 4);
            String smsMessageTemplate = "Verfication Code : " + verificationCode;

            sendSms(freelancerDriverDto.getPhone(), user.getCountryCodes().getId(), smsMessageTemplate);
            saveDriverVerficiationCode(freelancerDriverDto.getUserId(), user.getEmail(), verificationCode);

            return verificationCode;
        }

    }

    @Override
    public void rejectFreelancerDriverInfo(FreelancerDriverDto freelancerDriverDto) throws EntityNotFoundException {
        FreelancerDriverEntity freelancerDriverEntity = freelancerDriverRepository
                .findOne(freelancerDriverDto.getUserId());
        UsersEntity user = userJpaRepository.findOne(freelancerDriverDto.getUserId());
        if (freelancerDriverEntity == null)
            throw new EntityNotFoundException("Driver Not Found");

        freelancerDriverEntity.setStatus(FreelancerDriverstatus.REJECTED.name());
        freelancerDriverEntity.setRejectionReasonDescription(freelancerDriverDto.getRejectionReasonDescription());

        freelancerDriverRepository.save(freelancerDriverEntity);

        user.setPhone(freelancerDriverDto.getPhone());
        userJpaRepository.save(user);

        String smsMessageTemplate = "We are sorry to inform you that your request to become a driver has been rejected, for details about rejection reason , please contact 19000";
        sendSms(freelancerDriverDto.getPhone(), user.getCountryCodes().getId(), smsMessageTemplate);

    }

    @Override
    public void sendEmailWithMissingdoucments(FreelancerDriverDto freelancerDriverDto) throws JsonProcessingException {
        FreelancerDriverEntity freelancerDriverEntity = freelancerDriverRepository
                .findOne(freelancerDriverDto.getUserId());
        UsersEntity user = userJpaRepository.findOne(freelancerDriverDto.getUserId());

        String emailTo = freelancerDriverDto.getEmail();
        MissingDocumentUrl missingDocumentUrlMap = null;
        HashMap<String, MissingDocumentUrl> missingDocuments = missingDocumentsImagesUrl();


        StringBuilder docs = new StringBuilder();


        if (freelancerDriverDto.getIs_Birthcertificate_Exist() == false) {
            missingDocumentUrlMap = missingDocuments.get("birthCertificate");
            missingDocumentUrlMap.setColor(redColor);
            missingDocumentUrlMap.setImageUrl(environment.getProperty("missing_documents.birth_certificate_unchecked"));
            missingDocuments.put("birthCertificate", missingDocumentUrlMap);

            docs.append(MissingDocumentValues.is_Birthcertificate_Exist.getStatus());
            docs.append(" , ");

        }

        if (freelancerDriverDto.getIs_Criminalrecord_Exist() == false) {
            missingDocumentUrlMap = missingDocuments.get("criminalRecord");
            missingDocumentUrlMap.setColor(redColor);
            missingDocumentUrlMap.setImageUrl(environment.getProperty("missing_documents.criminalrecord_unchecked"));

            missingDocuments.put("criminalRecord", missingDocumentUrlMap);

            docs.append(MissingDocumentValues.is_Criminalrecord_Exist.getStatus());
            docs.append(" , ");

        }

        if (freelancerDriverDto.getIs_National_Id_Exist() == false) {
            missingDocumentUrlMap = missingDocuments.get("nationalId");
            missingDocumentUrlMap.setColor(redColor);
            missingDocumentUrlMap.setImageUrl(environment.getProperty("missing_documents.birth_certificate_unchecked"));

            missingDocuments.put("nationalId", missingDocumentUrlMap);

            docs.append(MissingDocumentValues.is_National_Id_Exist.getStatus());
            docs.append(" , ");

        }

        if (freelancerDriverDto.getIs_Vehicleownership_Id_Exist() == false) {
            missingDocumentUrlMap = missingDocuments.get("vehicleOwnershipId");
            missingDocumentUrlMap.setColor(redColor);
            missingDocumentUrlMap.setImageUrl(environment.getProperty("missing_documents.vehicleownership_id_unchecked"));

            missingDocuments.put("vehicleOwnershipId", missingDocumentUrlMap);

            docs.append(MissingDocumentValues.is_Vehicleownership_Id_Exist.getStatus());
            docs.append(" , ");

        }


        docs.deleteCharAt(docs.length() - 2);

        freelancerDriverEntity.setIs_Birthcertificate_Exist(freelancerDriverDto.getIs_Birthcertificate_Exist());
        freelancerDriverEntity.setIs_Criminalrecord_Exist(freelancerDriverDto.getIs_Criminalrecord_Exist());
        freelancerDriverEntity.setIs_National_id_exist(freelancerDriverDto.getIs_National_Id_Exist());
        freelancerDriverEntity.setIs_Vehicleownership_Id_Exist(freelancerDriverDto.getIs_Vehicleownership_Id_Exist());
        freelancerDriverEntity.setStatus(FreelancerDriverstatus.PENDING.name());
        user.setEmail(emailTo);

        freelancerDriverRepository.save(freelancerDriverEntity);
        userJpaRepository.save(user);


        sendEmail(emailTo, missingDocuments, docs.toString());

    }

    private HashMap<String, MissingDocumentUrl> missingDocumentsImagesUrl() {
        HashMap<String, MissingDocumentUrl> missingDocuments = new HashMap<>();

        MissingDocumentUrl birthCertificate = new MissingDocumentUrl();
        birthCertificate.setColor(blueColor);
        birthCertificate.setImageUrl(environment.getProperty("missing_documents.birth_certificate_checked"));

        MissingDocumentUrl criminalRecord = new MissingDocumentUrl();
        criminalRecord.setColor(blueColor);
        criminalRecord.setImageUrl(environment.getProperty("missing_documents.criminalrecord_checked"));

        MissingDocumentUrl nationalId = new MissingDocumentUrl();
        nationalId.setColor(blueColor);
        nationalId.setImageUrl(environment.getProperty("missing_documents.national_id_checked"));

        MissingDocumentUrl vehicleownershipId = new MissingDocumentUrl();

        vehicleownershipId.setColor(blueColor);
        vehicleownershipId.setImageUrl(environment.getProperty("missing_documents.vehicleownership_id_checked"));

        missingDocuments.put("birthCertificate", birthCertificate);
        missingDocuments.put("criminalRecord", criminalRecord);
        missingDocuments.put("nationalId", nationalId);
        missingDocuments.put("vehicleOwnershipId", vehicleownershipId);

        return missingDocuments;

    }

    private void sendEmail(String emailTo, HashMap<String, MissingDocumentUrl> missingDocuments, String documents) throws JsonProcessingException {

        List<EmailFromEntity> emails = (List<EmailFromEntity>) emailFromJpaRepository.findAll();
        String emailFrom = emails.get(0).getEmail();

        Context context = new Context();


        System.out.println(">>>>>>>>>>>>>>> national Id " + missingDocuments.get("nationalId").getImageUrl());
        System.out.println(">>>>>>>>>>>>>>> vehicleOwnershipId " + missingDocuments.get("vehicleOwnershipId").getImageUrl());
        System.out.println(">>>>>>>>>>>>>>> criminalRecord " + missingDocuments.get("criminalRecord").getImageUrl());
        System.out.println(">>>>>>>>>>>>>>> birthCertificate " + missingDocuments.get("birthCertificate").getImageUrl());

        context.setVariable("nationalId", missingDocuments.get("nationalId").getImageUrl());
        context.setVariable("vehicleOwnershipId", missingDocuments.get("vehicleOwnershipId").getImageUrl());
        context.setVariable("criminalRecord", missingDocuments.get("criminalRecord").getImageUrl());
        context.setVariable("birthCertificate", missingDocuments.get("birthCertificate").getImageUrl());


//		context.setVariable("nationalIdColor", missingDocuments.get("nationalId").getColor());
//		context.setVariable("vehicleOwnershipIdColor", missingDocuments.get("vehicleOwnershipId").getColor());
//		context.setVariable("criminalRecordColor", missingDocuments.get("criminalRecord").getColor());
//		context.setVariable("birthCertificate", missingDocuments.get("birthCertificate").getColor());


        context.setVariable("missingDocs", documents);


        String body = templateEngine.process("DocumentsEmailTemplate", context);

        // Remove ALL Carriage Return from String
        body = body.replaceAll("\\n", "");
        body = body.replaceAll("\\r", "");
        body = body.replaceAll("\"", "\\'");

        // Push Email Object To kafka topic
        EmailNotifierModel emailNotifierModel = new EmailNotifierModel();
        emailNotifierModel.setFrom(emailFrom);
        emailNotifierModel.setTo(emailTo);
        emailNotifierModel.setSubject("Missing Documents");
        emailNotifierModel.setBody(body);
        kafkaSender.send("EMAIL-SERVICE",
                objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emailNotifierModel));

    }

    @Override
    public List<FreelancerDriverstatus> getFreelancerStatus() {
        List<FreelancerDriverstatus> freelancerDriverstatusList = Arrays.asList(FreelancerDriverstatus.values());
        return freelancerDriverstatusList;
    }

    @Override
    public List<FreelancerDriverListInfoDto> filter(FreelanceFilterParameter endPointParameter) {
        if (endPointParameter.getPage() == 0)
            return mapper.mapToFreelancerDriversListPojos(freelancerDriverRepository
                    .findAllAndSort(endPointParameter.getDriverName() != null ? endPointParameter.getDriverName().toLowerCase() : ""
                            , endPointParameter.getCity() != null ? endPointParameter.getCity().toLowerCase() : "", endPointParameter.getMobileNumber() != null ? endPointParameter.getMobileNumber().toLowerCase() : ""
                            , endPointParameter.getBrand() != null ? endPointParameter.getBrand().toLowerCase() : "", endPointParameter.getModel() != null ? endPointParameter.getModel().toLowerCase() : ""
                            , endPointParameter.getStatus() != null ? endPointParameter.getStatus().toLowerCase() : ""
                            , new Sort(Direction.fromString(endPointParameter.getOrderBy().getOrderBy()), "created")));

        PageRequest page1 = new PageRequest(endPointParameter.getPage() - 1, endPointParameter.getMaxResult(), Direction.fromString(endPointParameter.getOrderBy().getOrderBy()), "created");

        return mapper.mapToFreelancerDriversListPojos(freelancerDriverRepository.findAllPageable(endPointParameter.getDriverName() != null ? endPointParameter.getDriverName().toLowerCase() : ""
                , endPointParameter.getCity() != null ? endPointParameter.getCity().toLowerCase() : "", endPointParameter.getMobileNumber() != null ? endPointParameter.getMobileNumber().toLowerCase() : ""
                , endPointParameter.getBrand() != null ? endPointParameter.getBrand().toLowerCase() : "", endPointParameter.getModel() != null ? endPointParameter.getModel().toLowerCase() : ""
                , endPointParameter.getStatus() != null ? endPointParameter.getStatus().toLowerCase() : "", page1));
    }

    private List<Message> checkDriverInfoExist(FreelancerDriverDto freelancerDriverDto) {
        List<Message> messages = new ArrayList<Message>();
        UsersEntity user = userJpaRepository.findOne(freelancerDriverDto.getUserId());
        FreelancerDriverEntity freelancerDriverEntity = freelancerDriverRepository
                .findOne(freelancerDriverDto.getUserId());

        if (!freelancerDriverDto.getEmail().toLowerCase().equals(user.getEmail())) {
            List<UsersEntity> isEmailExist = userJpaRepository.findAllByEmail(freelancerDriverDto.getEmail());
            if (!isEmailExist.isEmpty()) {
                messages.add(new Message(MessageType.ERROR, "email",
                        messageSource.getMessage("driver.email.emailexist", null, LocaleContextHolder.getLocale())));

            }

        }

        if (!freelancerDriverDto.getPhone().equals(user.getPhone())) {
            List<UsersEntity> isPhoneExist = userJpaRepository.findAllByPhone(freelancerDriverDto.getPhone());

            if (!isPhoneExist.isEmpty()) {
                messages.add(new Message(MessageType.ERROR, "phone",
                        messageSource.getMessage("driver.phone.phoneexist", null, LocaleContextHolder.getLocale())));

            }

        }

        if (!freelancerDriverDto.getNationalId().equals(freelancerDriverEntity.getNationalId())) {
            List<FreelancerDriverEntity> isNationalIdExist = freelancerDriverRepository
                    .findAllByNationalId(freelancerDriverDto.getNationalId());

            if (!isNationalIdExist.isEmpty()) {
                messages.add(new Message(MessageType.ERROR, "nationalId", messageSource
                        .getMessage("driver.nationalid.nationalidexist", null, LocaleContextHolder.getLocale())));

            }

        }

        if (!freelancerDriverDto.getDrivingLicenseId().equals(freelancerDriverEntity.getDrivingLicenseId())) {
            List<FreelancerDriverEntity> isDrivingLicenseIdExist = freelancerDriverRepository
                    .findAllByDrivingLicenseId(freelancerDriverDto.getDrivingLicenseId());

            if (!isDrivingLicenseIdExist.isEmpty()) {
                messages.add(new Message(MessageType.ERROR, "DrivingLicenseId", messageSource.getMessage(
                        "driver.drivingLicenseid.drivinglicenseidexist", null, LocaleContextHolder.getLocale())));

            }

        }

        return messages;
    }

    private void sendSms(String phoneNum, Long countryCodeId, String smsMessageTemplate) {

        String countryCode = countryCodesRepository.findOne(countryCodeId).getPhonecode().toString();
        StringBuilder phoneNumber = new StringBuilder();
        phoneNumber.append("+");
        phoneNumber.append(countryCode);
        phoneNumber.append(phoneNum);

        UnRegisteredSmsModel smsModel = new UnRegisteredSmsModel();

        smsModel.setMessage(smsMessageTemplate);
        smsModel.setPhoneNumber(phoneNumber.toString());

        smsEmailUtils.sendSms(smsModel);

    }

    private void saveDriverVerficiationCode(Long id, String email, String verificationCode) {

        DriverVerificationEntity entity = new DriverVerificationEntity();
        entity.setId(id);
        entity.setVerificationCode(verificationCode);
        entity.setVerified(false);

        driverVerificationRepository.save(entity);
    }

}
