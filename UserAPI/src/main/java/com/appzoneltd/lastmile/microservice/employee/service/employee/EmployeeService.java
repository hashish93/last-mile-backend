package com.appzoneltd.lastmile.microservice.employee.service.employee;

import com.appzoneltd.lastmile.microservice.employee.dao.CountryCodesRepository;
import com.appzoneltd.lastmile.microservice.employee.dao.EmailFromJpaRepository;
import com.appzoneltd.lastmile.microservice.employee.dao.UserEmailVerificationJpaRepository;
import com.appzoneltd.lastmile.microservice.employee.dao.UserRepository;
import com.appzoneltd.lastmile.microservice.employee.dto.EmailNotifierModel;
import com.appzoneltd.lastmile.microservice.employee.dto.UserProfileDTO;
import com.appzoneltd.lastmile.microservice.employee.entity.*;
import com.appzoneltd.lastmile.microservice.employee.kafka.KafkaSender;
import com.appzoneltd.lastmile.microservice.employee.model.Driver;
import com.appzoneltd.lastmile.microservice.employee.model.Employee;
import com.appzoneltd.lastmile.microservice.employee.model.Parameter;
import com.appzoneltd.lastmile.microservice.employee.service.BuildingService;
import com.appzoneltd.lastmile.microservice.employee.service.PrincipalService;
import com.appzoneltd.lastmile.microservice.employee.service.UserService;
import com.appzoneltd.lastmile.microservice.employee.service.UserTypeService;
import com.appzoneltd.lastmile.microservice.employee.transformer.UserTransformer;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class EmployeeService {

    @Autowired
    private UserTypeService userTypeService;

    @Autowired
    private OperationBackOfficeService operationBackOfficeService;

    @Autowired
    private HubAdminService hubAdminService;

    @Autowired
    private SupervisorService supervisorService;

    @Autowired
    private FreeLancerAdminService freeLancerAdminService;

    @Autowired
    private BuildingService buildingService;

    @Autowired
    private PrincipalService principalService;

    @Autowired
    private DriverService driverService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private CountryCodesRepository countryCodesRepository;

    @Autowired
    private UserEmailVerificationJpaRepository userEmailVerificationRepository;

    @Autowired
    private KafkaSender kafkaSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private EmailFromJpaRepository emailFromJpaRepository;

    @Value("${send_email.change-email-subject}")
    private String newEmailSubject;

    @Value("${send_email.from}")
    private String fromEmail;

    @Value("${send_email.change-email-prefix}")
    private String bodyPrefix;

    private ObjectMapper mapper = new ObjectMapper();

    public List<Message> saveOrUpdateEmployee(Employee employee, Principal principal) {
        UserTypeEntity userTypeEntity = userTypeService.getUserTypeEntity(employee.getUserTypeId());
        List<Message> messages = null;
        if (userTypeEntity != null) {
            String userTypeName = userTypeEntity.getName();
            switch (userTypeName) {
                case "FREELANCER_ADMIN":
                    messages = freeLancerAdminService.saveOrUpdate(employee, principal);
                    break;

                case "HUB_ADMIN":
                    messages = hubAdminService.saveOrUpdate(employee, principal);
                    break;

                case "SUPERVISOR":
                    messages = supervisorService.saveOrUpdate(employee, principal);

                    break;

                case "OPERATION_BACKOFFICE":
                    messages = operationBackOfficeService.saveOrUpdate(employee, principal);
                    break;

                default:
                    break;
            }
        }
        return messages;
    }

    public List<Message> saveOrUpdateDriver(Driver driver, Principal principal) throws JsonProcessingException {
        return driverService.saveOrUpdateDriver(driver, principal);
    }

    public List<Employee> getAllDrivers(Parameter parameter, Principal principal) {
        PageRequest pageRequest = null;
        List<Employee> employees = new ArrayList<>();
        if (parameter.getPage() != 0) {
            pageRequest = new PageRequest(parameter.getPage() - 1, parameter.getMaxResult(),
                    Sort.Direction.fromString(parameter.getOrderBy().getOrderBy()), "created");
        }

        List<Long> hubs = new ArrayList<>();

        if (principalService.isSuperAdmin(principal)) {
            List<BuildingEntity> buildingEntities = buildingService.getAllBuilding();
            for (BuildingEntity buildingEntity : buildingEntities) {
                hubs.add(buildingEntity.getBuildingId());
            }
        } else {
            hubs = principalService.getHubs(principal);
        }
        List<UserEntity> userEntities = userRepository.getAllDrivers(hubs, pageRequest);
        if (userEntities != null) {
            employees = UserTransformer.getEmployeesFromUserEntities(userEntities);
        }
        return employees;
    }

    public List<Employee> getAllDriversForHub(Parameter parameter, Principal principal) {
        PageRequest pageRequest = null;
        List<Employee> employees = new ArrayList<>();
        if (parameter.getPage() != 0) {
            pageRequest = new PageRequest(parameter.getPage() - 1, parameter.getMaxResult(),
                    Sort.Direction.fromString(parameter.getOrderBy().getOrderBy()), "created");
        }

        List<Long> hubs = new ArrayList<>();

        if (principalService.isSuperAdmin(principal) || principalService.isSuperVisor(principal)) {
            if (parameter.getHubId() != null) {
                hubs.add(parameter.getHubId());
            }
        } else {
            hubs = principalService.getHubs(principal);
        }
        if (hubs.size() > 0) {
            List<UserEntity> userEntities = userRepository.getAllDrivers(hubs, pageRequest);
            if (userEntities != null) {
                employees = UserTransformer.getEmployeesFromUserEntities(userEntities);
            }
        }

        return employees;
    }

    public UserProfileDTO getEmployeeByPrincipal(Principal principal) {
        Long userId = principalService.getUserId(principal);
        Employee employee = userService.getEmployeeById(userId);
        UserProfileDTO userProfileDTO = null;
        if (employee != null) {
            userProfileDTO = getUserProfileDtoFromEmployee(employee);
        }
        return userProfileDTO;
    }

    private UserProfileDTO getUserProfileDtoFromEmployee(Employee employee) {
        UserProfileDTO userProfileDTO = null;
        if (employee != null) {
            userProfileDTO = new UserProfileDTO();
            userProfileDTO.setUserId(employee.getUserId());
            userProfileDTO.setFirstName(employee.getFirstName());
            userProfileDTO.setLastName(employee.getLastName());
            userProfileDTO.setEmail(employee.getEmail());
            userProfileDTO.setNationalId(employee.getNationalId());
            userProfileDTO.setPhoneNumber(employee.getPhone());
            userProfileDTO.setImageId(employee.getPersonalPhotoId());
        }
        return userProfileDTO;
    }

    private boolean checkEmailExists(Employee employee) {
        UserEntity userEntity = userRepository.checkEmailExists(employee.getUserId(), employee.getEmail());
        return userEntity != null;
    }

    public Object updateEmployeeProfile(UserProfileDTO userProfileDTO) {
        UserEntity userEntity = userService.getUserById(userProfileDTO.getUserId());
        List<Message> messages = new ArrayList<Message>();
        Employee employee = UserTransformer.getEmployeeFromUserProfileDTO(userProfileDTO);


        if (!userProfileDTO.getPhoneNumber().equals(userEntity.getPhone())) {
            boolean isMobileExist = checkPhoneExists(employee);
            if (isMobileExist) {
                messages.add(new Message(MessageType.ERROR, "mobile", messageSource
                        .getMessage("employee.mobile.mobileexist", null, LocaleContextHolder.getLocale())));
            }

        }

        if (!messages.isEmpty()) {
            return messages;
        } else {
            userEntity.setFirstname(userProfileDTO.getFirstName());
            userEntity.setLastname(userProfileDTO.getLastName());

            if (userProfileDTO.getCountryCodeId() != null) {
                CountryCodesEntity countryCodesEntity = countryCodesRepository.findOne(userProfileDTO.getCountryCodeId());
                userEntity.setCountryCodes(countryCodesEntity);
            }

            userEntity.setPhone(userProfileDTO.getPhoneNumber());
            userEntity.setPersonalPhotoId(userProfileDTO.getImageId());
            userEntity.setUsername(userProfileDTO.getUserName());

            return userRepository.save(userEntity);

        }

    }

    private boolean checkPhoneExists(Employee employee) {
        UserEntity userEntity = userRepository.checkPhoneExists(employee.getUserId(), employee.getPhone());
        return userEntity != null;
    }

    public List<Message> updateUserPassword(UserProfileDTO userProfileDTO) {
        List<Message> messages = new ArrayList<Message>();
        UserEntity userEntity = userService.getUserById(userProfileDTO.getUserId());

        if (!userEntity.getPassword().equals(userProfileDTO.getOldPassword())) {
            messages.add(new Message(MessageType.ERROR, "oldPassword",
                    messageSource.getMessage("employee.update.oldpassword", null, LocaleContextHolder.getLocale())));
        }

        if (!userProfileDTO.getNewPassword().equalsIgnoreCase(userProfileDTO.getRepeatedPassword())) {
            messages.add(new Message(MessageType.ERROR, "newPassword",
                    messageSource.getMessage("employee.update.newpassword", null, LocaleContextHolder.getLocale())));
        }

        if (messages.isEmpty()) {
            userEntity.setPassword(userProfileDTO.getNewPassword());
            userRepository.save(userEntity);

        }

        return messages;
    }

    public Boolean updateEmail(String email, Principal principal) throws EntityNotFoundException, JsonProcessingException, DuplicatedKeyException {
        final Long userId = principalService.getUserId(principal);
        final UserEntity usersEntity = userRepository.findOne(userId);
        if (usersEntity == null)
            throw new EntityNotFoundException("notfound");


        if (email == null || "".equalsIgnoreCase(email)) {
            return false;
        }

        if (!userRepository.findAllByEmail(email).isEmpty())
            throw new DuplicatedKeyException("user.email.exists", "EMAIL", "");

        String code = generateVerificationCode();
        UserEmailVerificationEntity userEmailVerificationEntity = userEmailVerificationRepository.findOne(usersEntity.getUserId());

        if (userEmailVerificationEntity == null)
            userEmailVerificationEntity = new UserEmailVerificationEntity();

        userEmailVerificationEntity.setId(usersEntity.getUserId());
        userEmailVerificationEntity.setIsVerified(false);
        userEmailVerificationEntity.setEmail(email);
        userEmailVerificationEntity.setVerificationCode(code);
        userEmailVerificationEntity.setUsers(usersEntity);

        EmailNotifierModel emailNotifierModel = new EmailNotifierModel();
        emailNotifierModel.setFrom(fromEmail);
        emailNotifierModel.setTo(email);
        emailNotifierModel.setSubject(newEmailSubject);
        emailNotifierModel.setBody(bodyPrefix + code);


        userEmailVerificationRepository.save(userEmailVerificationEntity);
        this.sendChangeEmailTemplate(usersEntity.getFirstname(), email, newEmailSubject, bodyPrefix + code);
        return true;
    }

    private String generateVerificationCode() {
        final int[] numbers = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            stringBuilder.append(numbers[random.nextInt(10)]);
        }
        return stringBuilder.toString();
    }

    public void sendChangeEmailTemplate(String userName, String email, String subject, String bodyMsg) throws JsonProcessingException {

        List<EmailFromEntity> emails = (List<EmailFromEntity>) emailFromJpaRepository.findAll();
        String emailFrom = emails.get(0).getEmail();

        Context context = new Context();
        context.setVariable("userName", userName);
        context.setVariable("body", bodyMsg);

        String body = templateEngine.process("EmailChangeConfirmation", context);

        body = body.replaceAll("\\n", "");
        body = body.replaceAll("\\r", "");
        body = body.replaceAll("\"", "\'");

        EmailNotifierModel emailNotifierModel = new EmailNotifierModel();
        emailNotifierModel.setFrom(emailFrom);
        emailNotifierModel.setTo(email);
        emailNotifierModel.setSubject(subject);
        emailNotifierModel.setBody(body);

        kafkaSender.sendMessage("EMAIL-SERVICE",
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emailNotifierModel));
    }

    public Boolean verifyUpdatedEmail(String code) {
        if (code == null || "".equalsIgnoreCase(code)) {
            return false;
        }

        List<UserEmailVerificationEntity> userEmailVerificationEntities = userEmailVerificationRepository.findAllByVerificationCodeAndIsVerified(code, false);

        if (userEmailVerificationEntities != null && userEmailVerificationEntities.size() == 1) {
            UserEmailVerificationEntity userEmailVerificationEntity = userEmailVerificationEntities.get(0);

            UserEntity usersEntity = userRepository.findOne(userEmailVerificationEntity.getId());
            usersEntity.setEmail(userEmailVerificationEntity.getEmail());
            userRepository.save(usersEntity);

            userEmailVerificationEntity.setIsVerified(true);
            userEmailVerificationRepository.save(userEmailVerificationEntity);
            return true;
        }
        return false;
    }


}
