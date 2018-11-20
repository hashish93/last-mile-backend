package com.appzoneltd.lastmile.microservice.customer.service;

import com.appzoneltd.lastmile.microservice.customer.dao.entity.*;
import com.appzoneltd.lastmile.microservice.customer.dao.repository.*;
import com.appzoneltd.lastmile.microservice.customer.dto.*;
import com.appzoneltd.lastmile.microservice.customer.service.exception.InvalidPasswordMatchException;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.enums.UserType;
import com.appzoneltd.lastmile.microservice.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Sort;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author alaa.nabil
 */
@Service
public class CustomerServiceImp implements CustomerService {

    private final static String SMS_TOPIC = "SMS-NOTIFICATION";
    private final static String SMS_UNVERIFIED_NUMBER_TOPIC = "SMS-NOTIFICATION-UNREGISTERED";
    private final static String EMAIL_SERVICE = "EMAIL-SERVICE";
    private final CustomerRepositoryImp customerRepository;
    private final CustomerRepository customerJpaRepository;
    private final UsersRepository usersRepository;
    private final CountryCodesRepository countryCodesRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final StaticContentJpaRepository staticContentJpaRepository;
    private final CustomerVerificationRepository customerVerificationRepository;
    private final CustomerForgotPasswordRepository forgotPasswordRepository;
    private final MessageSource messageSource;
    private final KafkaTemplate<Long, String> kafkaTemplate;
    private final ObjectMapper mapper;
    private final DtoMapper dtoMapper;
    private final String fromEmail;
    private final String verifySubject;
    private final UserEmailVerificationJpaRepository userEmailVerificationRepository;
    private final EmailFromJpaRepository emailFromJpaRepository;
    private final TemplateEngine templateEngine;
    @Autowired
    private PrincipalService principalService;


    @Autowired
    public CustomerServiceImp(CustomerRepositoryImp customerRepository, CustomerRepository customerJpaRepository, UsersRepository usersRepository, CountryCodesRepository countryCodesRepository, CountryRepository countryRepository, CityRepository cityRepository, StaticContentJpaRepository staticContentJpaRepository, CustomerVerificationRepository customerVerificationRepository
            , CustomerForgotPasswordRepository forgotPasswordRepository, MessageSource messageSource, KafkaTemplate<Long, String> kafkaTemplate, DtoMapper dtoMapper, @Value("${email.from}") String fromEmail, @Value("${email.verify-subject}") String verifySubject, UserEmailVerificationJpaRepository userEmailVerificationRepository, EmailFromJpaRepository emailFromJpaRepository, TemplateEngine templateEngine) {
        this.customerRepository = customerRepository;
        this.customerJpaRepository = customerJpaRepository;
        this.usersRepository = usersRepository;
        this.countryCodesRepository = countryCodesRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.staticContentJpaRepository = staticContentJpaRepository;
        this.customerVerificationRepository = customerVerificationRepository;
        this.forgotPasswordRepository = forgotPasswordRepository;
        this.messageSource = messageSource;
        this.kafkaTemplate = kafkaTemplate;
        this.dtoMapper = dtoMapper;
        this.fromEmail = fromEmail;
        this.verifySubject = verifySubject;
        this.userEmailVerificationRepository = userEmailVerificationRepository;
        this.emailFromJpaRepository = emailFromJpaRepository;
        this.templateEngine = templateEngine;
        this.mapper = new ObjectMapper();
    }

    @Override
    public Object saveCustomer(Customer customer) throws JsonProcessingException {
        Long ID = Utils.generateUUID();
        customer.setUserId(ID);
        customer.setUserType(UserType.CUSTOMER);
        customer.setUserTypeId(8L);

        Map<String, String[]> messages = new HashMap<>();

//		boolean isUserNameExist = customerRepository.isAlreadyExists("lastmile_authorization_server.users", "username",
//				customer.getUsername());

        boolean isMobileExist = customerRepository.isAlreadyExists("lastmile_authorization_server.users", "phone",
                customer.getPhone());

        if (!isMobileExist) {
            if (customer.getPhone() != null && customer.getPhone().length() > 2 && customer.getPhone().charAt(0) == '0') {
                String mobileExcludeZero = customer.getPhone().substring(1);
                System.out.println("$$$ Mobile Number " + mobileExcludeZero);
                isMobileExist = customerRepository.isAlreadyExists("lastmile_authorization_server.users", "phone",
                        mobileExcludeZero);
            }
        }

        if (!isMobileExist) {
            if (customer.getPhone() != null && customer.getPhone().length() > 2 && customer.getPhone().charAt(0) != '0') {
                String mobileAppendZero = "0" + customer.getPhone();
                System.out.println("$$$ Mobile Number " + mobileAppendZero);
                isMobileExist = customerRepository.isAlreadyExists("lastmile_authorization_server.users", "phone",
                        mobileAppendZero);
            }
        }

        boolean isEmailExist = customerRepository.isAlreadyExists("lastmile_authorization_server.users", "email",
                customer.getEmail().toLowerCase());

//		if (isUserNameExist) {
//			messages.add(new Message(MessageType.ERROR, "User Name", messageSource.getMessage("user.username.exists",
//					null, "user.username.exists", LocaleContextHolder.getLocale())));
//		}

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
            String code = generateVerificationCode();
            int result = customerRepository.createCustomer(customer);
            if (result == 1) {
                customerVerificationRepository.save(new CustomerVerificationEntity(ID, false, code));
                sendSms(ID, messageSource.getMessage("verification.code.message", null, LocaleContextHolder.getLocale()) + code);
            }
            return result;
        }
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

    public void sendSms(Long customerId, String message) throws JsonProcessingException {
        Long[] ids = {customerId};
        kafkaTemplate.send(SMS_TOPIC, mapper.writeValueAsString(new SmsModel(ids, "CUSTOMER", message)));
    }

    @Override
    public Object updateCustomer(Customer customer) throws DuplicatedKeyException, EntityNotFoundException {
        isCustomerExistsAndActive(customer.getUserId());
        List<Message> messages = new ArrayList<>();

        CustomerEntity oldCustomer = customerJpaRepository.findOne(customer.getUserId());

//        if (!customer.getUsername().equals(oldCustomer.getUsersEntity().getUsername())) {
//            boolean isUserNameExist = customerRepository.isAlreadyExists("lastmile_authorization_server.users",
//                    "username", customer.getUsername());
//            if (isUserNameExist) {
//                messages.add(new Message(MessageType.ERROR, "User Name", messageSource.getMessage(
//                        "user.username.exists", null, "user.username.exists", LocaleContextHolder.getLocale())));
//            }
//        }

        if (!customer.getEmail().toLowerCase().equals(oldCustomer.getUsersEntity().getEmail().toLowerCase())) {

            boolean isEmailExist = customerRepository.isAlreadyExists("lastmile_authorization_server.users", "email",
                    customer.getEmail().toLowerCase());
            if (isEmailExist) {
                messages.add(new Message(MessageType.ERROR, "Email", messageSource.getMessage("user.email.exists", null,
                        "user.email.exists", LocaleContextHolder.getLocale())));
            }
        }

//        if (!customer.getPhone().equals(oldCustomer.getUsersEntity().getPhone())) {
//
//            boolean isMobileExist = customerRepository.isAlreadyExists("lastmile_authorization_server.users", "phone",
//                    customer.getPhone());
//
//            if (isMobileExist) {
//                messages.add(new Message(MessageType.ERROR, "Phone", messageSource.getMessage("user.phone.exists", null,
//                        "user.phone.exists", LocaleContextHolder.getLocale())));
//            }
//        }

        if (!messages.isEmpty()) {
            return messages;
        } else {
            UsersEntity usersEntity = oldCustomer.getUsersEntity();

            CountryCodesEntity countryCodesEntity = countryCodesRepository.findOne(customer.getCountryCodeId());

            usersEntity.setUsername(customer.getFirstName() + " " + customer.getLastName());
            usersEntity.setFirstname(customer.getFirstName());
            usersEntity.setLastname(customer.getLastName());
//            usersEntity.setCountryCodeId(customer.getCountryCodeId() != null ? countryCodesEntity != null ? countryCodesEntity.getId() : null : null);
//            usersEntity.setPhone(customer.getPhone());
//            usersEntity.setEmail(customer.getEmail());
            usersEntity.setStaticContent(customer.getPersonalPhoto() != null ? staticContentJpaRepository.findOne(customer.getPersonalPhoto()) : null);

            oldCustomer.setGender(customer.getGender());
            oldCustomer.setBirthdate(customer.getBirthdate());
//            oldCustomer.setCountryCodes(customer.getCountryCodeId() != null ? countryCodesEntity : null);
            oldCustomer.setCountry(customer.getCountryId() != null ? countryRepository.findOne(customer.getCountryId()) : null);
            oldCustomer.setCity(customer.getCityId() != null ? cityRepository.findOne(customer.getCityId()) : null);

            CustomerEntity saved = customerJpaRepository.save(oldCustomer);
            if (saved == null)
                throw new EntityNotFoundException("customer.notfound");
            else
                return saved;
        }
    }

    private void isCustomerExistsAndActive(Long customerId) throws EntityNotFoundException {
        if (!customerRepository.isAlreadyExists("lastmile_authorization_server.users", "user_id", customerId)) {
            throw new EntityNotFoundException("customer.notfound");
        }
    }

    @Override
    public int deactivateCustomer(Long customerId) throws EntityNotFoundException {
        isCustomerExistsAndActive(customerId);
        return customerRepository.deactivate(customerId);
    }

    @Override
    public CustomerInfo findCustomerById(Long customerId) throws EntityNotFoundException {
        isCustomerExistsAndActive(customerId);
        return dtoMapper.mapToCustomer(customerJpaRepository.findOne(customerId));
    }

    @Override
    public CustomerInfo findCustomerById(Principal principal) throws EntityNotFoundException {
        return findCustomerById(principalService.getUserId(principal));
//        return findCustomerById(customerRepository.getAuthonticatedUserId(principal.getName()));
    }

    @Override
    public List<CustomerInfo> findAllCustomers(Sort sort) {
        return ((List<CustomerEntity>) customerJpaRepository.findAll(sort)).stream()
                .map(dtoMapper::mapToCustomer).collect(Collectors.toList());
    }

    @Override
    public List<CustomerInfo> findAllCustomersByPageAndOffset(PagingParameters pagingParameters) {
        return customerJpaRepository.findAll(pagingParameters).getContent().stream()
                .map(dtoMapper::mapToCustomer).collect(Collectors.toList());
    }

    @Override
    public Boolean verify(Principal principal, String code) {
        CustomerVerificationEntity customerVerificationEntity = customerVerificationRepository.findOne(principalService.getUserId(principal));
        if (code == null)
            return false;

        if (customerVerificationEntity.getVerificationCode().equalsIgnoreCase(code)) {
            customerVerificationEntity.setVerified(true);
            customerVerificationRepository.save(customerVerificationEntity);
            return true;
        }
        return false;
    }

    @Override
    public Boolean changeNumber(Principal principal, UnverifiedNumber unverifiedNumber) throws EntityNotFoundException, JsonProcessingException {
        CustomerEntity customerEntity = customerJpaRepository.findOne(principalService.getUserId(principal));
        if (customerEntity == null)
            throw new EntityNotFoundException("customer.notfound");

        if (!usersRepository.findAllByPhone(unverifiedNumber.getPhone()).isEmpty())
            throw new EntityNotFoundException("user.phone.exists");

        UsersEntity usersEntity = usersRepository.findOne(customerEntity.getCustomerId());
        CountryCodesEntity countryCodesEntity = countryCodesRepository.findOne(unverifiedNumber.getCountryCode());
        customerEntity.setCountryCodes(countryCodesEntity);
        usersEntity.setPhone(unverifiedNumber.getPhone());
        usersEntity.setCountryCodeId(countryCodesEntity.getId());

        customerJpaRepository.save(customerEntity);
        usersRepository.save(usersEntity);

        String code = generateVerificationCode();
        CustomerVerificationEntity customerVerificationEntity = customerVerificationRepository.findOne(customerEntity.getCustomerId());
        if (!customerVerificationEntity.getVerified()) {
            customerVerificationEntity.setVerificationCode(code);
            customerVerificationRepository.save(customerVerificationEntity);
            sendSms(customerEntity.getCustomerId(), messageSource.getMessage("verification.code.message", null, LocaleContextHolder.getLocale()) + code);
        }

        return true;
    }

    @Override
    public Boolean resendVerificationCode(Principal principal) throws EntityNotFoundException, JsonProcessingException {
        Long id = principalService.getUserId(principal);
        if (id == null)
            throw new EntityNotFoundException("customer.notfound");

        CustomerVerificationEntity customerVerificationEntity = customerVerificationRepository.findOne(id);
        if (customerVerificationEntity.getVerified())
            return false;

        String code = this.generateVerificationCode();
        customerVerificationEntity.setVerificationCode(code);
        if (customerVerificationRepository.save(customerVerificationEntity) != null) {
            sendSms(id, messageSource.getMessage("verification.code.message", null, LocaleContextHolder.getLocale()) + code);
            return true;
        }
        return false;
    }

    @Override
    public int countAllCustomers() {
        return ((List<CustomerEntity>) customerJpaRepository.findAll()).size();
    }

    @Override
    public UsersEntity changePassword(Principal principal, ChangePasswordModel changePasswordModel) throws EntityNotFoundException, InvalidOldPassword {
        UsersEntity usersEntity = principalService.getUserEntity(principal);
        if (usersEntity == null)
            throw new EntityNotFoundException("customer.notfound");

        if (!usersEntity.getPassword().equals(changePasswordModel.getOldPassword()))
            throw new InvalidOldPassword(messageSource.getMessage("invalid.old.password", null, LocaleContextHolder.getLocale()));

        usersEntity.setPassword(changePasswordModel.getNewPassword());

        return usersRepository.save(usersEntity);
    }

    @Override
    public Boolean forgotPassword(UnverifiedNumber unverifiedNumber) throws EntityNotFoundException, JsonProcessingException {
        List<UsersEntity> usersEntities = usersRepository.findAllByPhone(unverifiedNumber.getPhone());
        if (usersEntities == null || usersEntities.isEmpty())
            throw new EntityNotFoundException("customer.notfound");

        String token = generateVerificationCode();
        CustomerForgotPasswordEntity customerForgotPasswordEntity = new CustomerForgotPasswordEntity();
        customerForgotPasswordEntity.setCustomerId(usersEntities.get(0).getUserId());
        customerForgotPasswordEntity.setToken(token);
        customerForgotPasswordEntity.setAttempts(3);
        customerForgotPasswordEntity.setCreated(new Date());

        if (forgotPasswordRepository.save(customerForgotPasswordEntity) != null) {
            sendSms(usersEntities.get(0).getUserId(), messageSource.getMessage("forgot.password.code.message", null, LocaleContextHolder.getLocale()) + token);
            return true;
        }
        return false;
    }

    @Override
    public boolean forgotPasswordVerifyToken(ForgetPasswordVerifyToken forgetPasswordVerifyToken) throws EntityNotFoundException, ExceededNumberOfAttempts, ForgotPasswordTokenExpired {
        List<UsersEntity> usersEntities = usersRepository.findAllByPhone(forgetPasswordVerifyToken.getPhone());
        if (usersEntities == null || usersEntities.isEmpty())
            throw new EntityNotFoundException("customer.notfound");

        CustomerForgotPasswordEntity customerForgotPasswordEntity = forgotPasswordRepository.findOne(usersEntities.get(0).getUserId());

        if (customerForgotPasswordEntity == null)
            return false;

        if (0 >= customerForgotPasswordEntity.getAttempts()) {
            customerForgotPasswordEntity.setAttempts(customerForgotPasswordEntity.getAttempts() - 1);
            forgotPasswordRepository.save(customerForgotPasswordEntity);
            throw new ExceededNumberOfAttempts(messageSource.getMessage("attempts.exceeds", null, LocaleContextHolder.getLocale()));
        }

        LocalTime localTime = LocalTime.now();
        LocalTime savedTime = LocalTime.of(customerForgotPasswordEntity.getCreated().getHours(), customerForgotPasswordEntity.getCreated().getMinutes(), customerForgotPasswordEntity.getCreated().getSeconds());
//                .ofInstant(customerForgotPasswordEntity.getCreated().toInstant(), ZoneId.systemDefault());
        Duration duration = Duration.between(savedTime, localTime);
        Duration _5min = Duration.ofMinutes(5);
        if (duration.compareTo(_5min) >= 0)
            throw new ForgotPasswordTokenExpired(messageSource.getMessage("forgot.password.token.expired", null, LocaleContextHolder.getLocale()));

        if (!"".equalsIgnoreCase(forgetPasswordVerifyToken.getToken()) && customerForgotPasswordEntity.getToken().equalsIgnoreCase(forgetPasswordVerifyToken.getToken()))
            return true;
        else {
            customerForgotPasswordEntity.setAttempts(customerForgotPasswordEntity.getAttempts() - 1);
            forgotPasswordRepository.save(customerForgotPasswordEntity);
        }

        return false;
    }

    @Override
    public boolean addNewPassword(NewPassword newPassword) throws EntityNotFoundException {
        List<UsersEntity> usersEntities = usersRepository.findAllByPhone(newPassword.getPhone());
        if (usersEntities == null || usersEntities.isEmpty())
            throw new EntityNotFoundException("customer.notfound");

        usersEntities.get(0).setPassword(newPassword.getPassword());
        usersRepository.save(usersEntities.get(0));

        return true;
    }

    @Override
    public UsersEntity changePasswordUnVerifyOld(Principal principal,
                                                 NewChangePasswordModel changePasswordModel)
            throws EntityNotFoundException, InvalidPasswordMatchException {

        UsersEntity usersEntity = principalService.getUserEntity(principal);

        if (usersEntity == null) {
            throw new EntityNotFoundException("customer.notfound");
        }

        if (!changePasswordModel.getPassword().equals(changePasswordModel.getPasswordAgain())) {
            throw new InvalidPasswordMatchException();
        }

        usersEntity.setPassword(changePasswordModel.getPassword());

        return usersRepository.save(usersEntity);
    }

    @Override
    public boolean updateMobileNumber(UnverifiedNumber unverifiedNumber, Principal principal) throws EntityNotFoundException, JsonProcessingException {
        CustomerEntity customerEntity = customerJpaRepository.findOne(principalService.getUserId(principal));
        if (customerEntity == null)
            throw new EntityNotFoundException("customer.notfound");

        if (!usersRepository.findAllByPhone(unverifiedNumber.getPhone()).isEmpty())
            throw new EntityNotFoundException("user.phone.exists");

        CountryCodesEntity countryCodesEntity = countryCodesRepository.findOne(unverifiedNumber.getCountryCode());
//        UsersEntity usersEntity = usersRepository.findOne(customerEntity.getCustomerId());

//        customerEntity.setCountryCodes(countryCodesEntity);
//        usersEntity.setPhone(unverifiedNumber.getPhone());

//        customerJpaRepository.save(customerEntity);
//        usersRepository.save(usersEntity);

        String code = generateVerificationCode();
        CustomerVerificationEntity customerVerificationEntity = customerVerificationRepository.findOne(customerEntity.getCustomerId());

        customerVerificationEntity.setVerificationCode(code);
//        customerVerificationEntity.setVerified(false);
        customerVerificationRepository.save(customerVerificationEntity);
        sendSmsUnverifiedNumber(countryCodesEntity.getPhonecode().toString(), unverifiedNumber.getPhone(), messageSource.getMessage("verification.code.message", null, LocaleContextHolder.getLocale()) + code);
        return true;
    }

    public void sendSmsUnverifiedNumber(String countryCode, String number, String message) throws JsonProcessingException {
        kafkaTemplate.send(SMS_UNVERIFIED_NUMBER_TOPIC, mapper.writeValueAsString(new UnRegisteredModel(buildMobileNumber(countryCode, number), message)));
    }

    private String buildMobileNumber(String countryCode, String mobile) {
        if (countryCode.endsWith("0"))
            if (mobile.startsWith("0"))
                return "+" + countryCode + mobile.replaceFirst("0", "");
        return "+" + countryCode + mobile;
    }

    @Override
    public Boolean verifyUpdatedNumber(Principal principal, UnverifiedNumber unverifiedNumber) throws EntityNotFoundException {

        CustomerEntity customerEntity = customerJpaRepository.findOne(principalService.getUserId(principal));
        if (customerEntity == null)
            throw new EntityNotFoundException("customer.notfound");


        if (unverifiedNumber.getCode() == null) {
            return false;
        }

        CustomerVerificationEntity customerVerificationEntity = customerVerificationRepository.findOne(principalService.getUserId(principal));
        if (customerVerificationEntity.getVerificationCode().equalsIgnoreCase(unverifiedNumber.getCode())) {
            CountryCodesEntity countryCodesEntity = countryCodesRepository.findOne(unverifiedNumber.getCountryCode());
            UsersEntity usersEntity = usersRepository.findOne(customerEntity.getCustomerId());

            customerEntity.setCountryCodes(countryCodesEntity);
            usersEntity.setPhone(unverifiedNumber.getPhone());
            usersEntity.setCountryCodeId(countryCodesEntity.getId());

            customerJpaRepository.save(customerEntity);
            usersRepository.save(usersEntity);

            customerVerificationEntity.setVerified(true);
            customerVerificationRepository.save(customerVerificationEntity);
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateEmail(String email, String password, Principal principal) throws EntityNotFoundException, JsonProcessingException {
        UsersEntity usersEntity = principalService.getUserEntity(principal);
        if (usersEntity == null)
            throw new EntityNotFoundException("customer.notfound");

        if (!password.equals(usersEntity.getPassword()))
            throw new EntityNotFoundException("invalid.password");;

        if (email == null || "".equalsIgnoreCase(email)) {
            return false;
        }

        if (!usersRepository.findAllByEmail(email).isEmpty())
            throw new EntityNotFoundException("user.email.exists");

        String code = generateVerificationCode();
        UserEmailVerificationEntity userEmailVerificationEntity = userEmailVerificationRepository.findOne(usersEntity.getUserId());

        if (userEmailVerificationEntity == null)
            userEmailVerificationEntity = new UserEmailVerificationEntity();

        userEmailVerificationEntity.setId(usersEntity.getUserId());
        userEmailVerificationEntity.setIsVerified(false);
        userEmailVerificationEntity.setEmail(email);
        userEmailVerificationEntity.setVerificationCode(code);
        userEmailVerificationEntity.setUsers(usersEntity);

        userEmailVerificationRepository.save(userEmailVerificationEntity);
        this.sendChangeEmailConfirmationCode(usersEntity.getFirstname(), email, verifySubject, getBodyPrefix() + " " + code);
        return true;
    }

    @Override
    public Boolean verifyUpdatedEmail(String code, Principal principal) {
        if (code == null || "".equalsIgnoreCase(code)) {
            return false;
        }

        Long userId = principalService.getUserId(principal);

        List<UserEmailVerificationEntity> userEmailVerificationEntities = userEmailVerificationRepository.findAllByVerificationCodeAndIsVerified(code, false);

        if (userEmailVerificationEntities != null && userEmailVerificationEntities.size() == 1) {
            UserEmailVerificationEntity userEmailVerificationEntity = userEmailVerificationEntities.get(0);

            if (!userEmailVerificationEntity.getId().equals(userId))
                return false;

            UsersEntity usersEntity = usersRepository.findOne(userEmailVerificationEntity.getId());
            usersEntity.setEmail(userEmailVerificationEntity.getEmail());
            usersRepository.save(usersEntity);

            userEmailVerificationEntity.setIsVerified(true);
            userEmailVerificationRepository.save(userEmailVerificationEntity);
            return true;
        }
        return false;
    }

    private void sendChangeEmailConfirmationCode(String userName, String email, String subject, String bodyMsg) throws JsonProcessingException {

        List<EmailFromEntity> emails = (List<EmailFromEntity>) emailFromJpaRepository.findAll();
        String emailFrom = emails.get(0).getEmail();

        Context context = new Context();
        context.setVariable("userName", userName);
        context.setVariable("body", bodyMsg);

        String body = templateEngine.process("EmailChangeConfirmation", context);

        body = body.replaceAll("\\n", "");
        body = body.replaceAll("\\r", "");
        body = body.replaceAll("\"", "\'");

        EmailTemplate emailNotifierModel = new EmailTemplate();
        emailNotifierModel.setFrom(emailFrom);
        emailNotifierModel.setTo(email);
        emailNotifierModel.setSubject(subject);
        emailNotifierModel.setBody(body);

        kafkaTemplate.send(EMAIL_SERVICE,
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emailNotifierModel));
    }

    private String getBodyPrefix() {
        return messageSource.getMessage("mail.verify.prefix", null,
                "mail.verify.prefix", LocaleContextHolder.getLocale());
    }

    private UsersEntity getUser(java.lang.String principal) {
        return usersRepository.findByPhoneOrEmail(principal, principal);
    }
}
