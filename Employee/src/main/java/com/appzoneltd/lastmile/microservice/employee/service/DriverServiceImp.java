package com.appzoneltd.lastmile.microservice.employee.service;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.appzoneltd.lastmile.microservice.employee.dao.CountryCodesRepository;
import com.appzoneltd.lastmile.microservice.employee.dao.Driver;
import com.appzoneltd.lastmile.microservice.employee.dao.DriverInfo;
import com.appzoneltd.lastmile.microservice.employee.dao.DriverRepositoryImp;
import com.appzoneltd.lastmile.microservice.employee.dao.EmailFromEntity;
import com.appzoneltd.lastmile.microservice.employee.dao.EmailFromJpaRepository;
import com.appzoneltd.lastmile.microservice.employee.kafka.EmailNotifierModel;
import com.appzoneltd.lastmile.microservice.employee.sms.SMSModel;
import com.appzoneltd.lastmile.microservice.employee.sms.SMSService;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.modelobjects.User;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DriverServiceImp implements DriverService {

	private final DriverRepositoryImp driverRepositoryImp;
	private final MessageSource messageSource;
	private final KafkaTemplate<Long, String> kafkaTemplate;
	private final ObjectMapper mapper;
	private final TemplateEngine templateEngine;
	private final ObjectMapper objectMapper;
	private final SMSService smsService;
	private final Environment environment;
	private final CountryCodesRepository countryCodesRepository;
	private final EmailFromJpaRepository emailFromJpaRepository;

	@Autowired
	public DriverServiceImp(DriverRepositoryImp driverRepositoryImp, MessageSource messageSource,
			KafkaTemplate<Long, String> kafkaTemplate, TemplateEngine templateEngine, ObjectMapper objectMapper,
			SMSService smsService, Environment environment, CountryCodesRepository countryCodesRepository,
			EmailFromJpaRepository emailFromJpaRepository) {
		this.driverRepositoryImp = driverRepositoryImp;
		this.messageSource = messageSource;
		this.kafkaTemplate = kafkaTemplate;
		this.mapper = new ObjectMapper();
		this.templateEngine = templateEngine;
		this.objectMapper = objectMapper;
		this.smsService = smsService;
		this.environment = environment;
		this.countryCodesRepository = countryCodesRepository;
		this.emailFromJpaRepository = emailFromJpaRepository;

	}

	@Override
	public Object saveDriver(Driver driver) throws JsonProcessingException {
		Long employeeID = Utils.generateUUID();
		driver.setUserId(employeeID);
		List<Message> messages = new ArrayList<Message>();

		boolean isNationalIdExist = driverRepositoryImp.isAlreadyExistsWithoutStatus("lastmile_core.driver",
				"national_id", driver.getNationalId());

		boolean isNationalIdExistInEmployee = driverRepositoryImp.isAlreadyExistsWithoutStatus(
				"lastmile_core.back_office_employee", "national_id", driver.getNationalId());

		boolean isEmailExist = driverRepositoryImp.isAlreadyExistsWithoutStatus("lastmile_authorization_server.users",
				"email", driver.getEmail().toLowerCase());
		boolean isMobileExist = driverRepositoryImp.isAlreadyExistsWithoutStatus("lastmile_authorization_server.users",
				"phone", driver.getPhone());
		boolean isDrivingLicenseIdExist = driverRepositoryImp.isAlreadyExistsWithoutStatus("lastmile_core.driver",
				"driving_license_id", driver.getDrivingLicenseId());

		if (isNationalIdExist || isNationalIdExistInEmployee) {
			messages.add(new Message(MessageType.ERROR, "nationalId",
					messageSource.getMessage("employee.nationalid.exist", null, LocaleContextHolder.getLocale())));
		}
		if (isEmailExist) {
			messages.add(new Message(MessageType.ERROR, "email",
					messageSource.getMessage("employee.email.emailexist", null, LocaleContextHolder.getLocale())));
		}
		if (isMobileExist) {
			messages.add(new Message(MessageType.ERROR, "mobile",
					messageSource.getMessage("employee.mobile.mobileexist", null, LocaleContextHolder.getLocale())));
		}
		if (isDrivingLicenseIdExist)
			messages.add(new Message(MessageType.ERROR, "drivingLicenseId", messageSource
					.getMessage("employee.driving.licenseidexist", null, LocaleContextHolder.getLocale())));

		if (!messages.isEmpty()) {
			return messages;
		} else {
			Integer result = driverRepositoryImp.createDriver(driver);
			if (result == 1) {
				sendEmail(driver.getEmail(), driver.getPhone(), driver.getPassword());
				sendSms(driver.getEmail(), driver.getPhone(), driver.getPassword(), driver.getCountryCodeId());
				return employeeID;
			} else {
				return null;
			} // end if-Else
		}
	}

	@Override
	public Object updateDriver(Driver driver) throws EntityNotFoundException {
		isCustomerExistsAndActive(driver.getUserId());
		List<Message> messages = new ArrayList<Message>();

		Driver oldDriver = driverRepositoryImp.queryforObject(driver.getUserId());

		if (!driver.getNationalId().equals(oldDriver.getNationalId())) {
			boolean isNationalIdExist = driverRepositoryImp.isAlreadyExistsWithoutStatus("lastmile_core.driver",
					"national_id", driver.getNationalId());
			boolean isNationalIdExistInEmployee = driverRepositoryImp.isAlreadyExistsWithoutStatus(
					"lastmile_core.back_office_employee", "national_id", driver.getNationalId());
			if (isNationalIdExist || isNationalIdExistInEmployee) {
				messages.add(new Message(MessageType.ERROR, "nationalId",
						messageSource.getMessage("employee.nationalid.exist", null, LocaleContextHolder.getLocale())));
			}
		}

		if (!driver.getEmail().toLowerCase().equals(oldDriver.getEmail().toLowerCase())) {
			boolean isEmailExist = driverRepositoryImp
					.isAlreadyExistsWithoutStatus("lastmile_authorization_server.users", "email", driver.getEmail());
			if (isEmailExist) {
				messages.add(new Message(MessageType.ERROR, "email",
						messageSource.getMessage("employee.email.emailexist", null, LocaleContextHolder.getLocale())));
			}
		}

		if (!driver.getPhone().equals(oldDriver.getPhone())) {
			boolean isMobileExist = driverRepositoryImp
					.isAlreadyExistsWithoutStatus("lastmile_authorization_server.users", "phone", driver.getPhone());
			if (isMobileExist) {
				messages.add(new Message(MessageType.ERROR, "mobile", messageSource
						.getMessage("employee.mobile.mobileexist", null, LocaleContextHolder.getLocale())));
			}

		}

		if (!driver.getDrivingLicenseId().equals(oldDriver.getDrivingLicenseId())) {
			boolean isDrivingLicenseIdExist = driverRepositoryImp.isAlreadyExistsWithoutStatus("lastmile_core.driver",
					"driving_license_id", driver.getDrivingLicenseId());
			if (isDrivingLicenseIdExist) {
				messages.add(new Message(MessageType.ERROR, "drivingLicenseId", messageSource
						.getMessage("employee.driving.licenseidexist", null, LocaleContextHolder.getLocale())));
			}

		}
		if (!messages.isEmpty()) {
			return messages;
		}

		else {
			return driverRepositoryImp.updateDriver(driver);
		}

	}

	@Override
	public Driver findDriverById(Long driverId) {
		return driverRepositoryImp.queryforObject(driverId);
	}

	@Override
	public List<DriverInfo> findAllDriversByPage(int pageCount, int pageNum, OrderBy orderType) {
		return driverRepositoryImp.queryWithPaging(pageNum, pageCount, orderType);
	}

	/*
	 * check Driver Related To Active Vehicle
	 */

	@Override
	public boolean checkDriverRelatedToActiveVehicle(Long driverId) {

		boolean chkDriverRelatedToActiveVehicle = false;
		Long result = driverRepositoryImp.countDriverRelatedToActiveVehicle(driverId);
		if (result != 0) {
			chkDriverRelatedToActiveVehicle = true;
		}
		return chkDriverRelatedToActiveVehicle;
	}

	@Override
	public int markDeleteDriver(Long driverId) throws JsonProcessingException {
		User driver = findDriverById(driverId);
		kafkaTemplate.send("DELETE_DRIVER_TOKEN", mapper.writeValueAsString(driver));
		return driverRepositoryImp.deactivate(driverId);
	}

	@Override
	public int countAllDrivers() {
		return driverRepositoryImp.count();
	}

	private void isCustomerExistsAndActive(Long customerId) throws EntityNotFoundException {
		if (!driverRepositoryImp.isAlreadyExists("lastmile_authorization_server.users", "user_id", customerId)) {
			throw new EntityNotFoundException("employee.notfound");
		}
	}

	@Override
	public Driver getDriverProfileByPrincipal(Principal principal) {
		Long employeeId = driverRepositoryImp.getDriverId(principal.getName());
		Driver driver = findDriverById(employeeId);
		return driver;
	}

	private void sendEmail(String email, String phoneNumber, String password) throws JsonProcessingException {
		
		List<EmailFromEntity> emails = (List<EmailFromEntity>) emailFromJpaRepository.findAll();
		String emailFrom = emails.get(0).getEmail();
		if (emailFrom == null || emailFrom.isEmpty()) {
			 emailFrom = environment.getProperty("send_email.from");
		}

		Context context = new Context();
		context.setVariable("UserName", phoneNumber + "  or  " + email);
		context.setVariable("Password", password);
		context.setVariable("email_support", environment.getProperty("send_email.email_support"));

		String body = templateEngine.process("emailForm", context);

		body = body.replaceAll("\\n", "");
		body = body.replaceAll("\\r", "");
		body = body.replaceAll("\"", "\'");

		EmailNotifierModel emailNotifierModel = new EmailNotifierModel();
		emailNotifierModel.setFrom(emailFrom);
		emailNotifierModel.setTo(email);
		emailNotifierModel.setSubject("LastMile Info");
		emailNotifierModel.setBody(body);

		kafkaTemplate.send("EMAIL-SERVICE",
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emailNotifierModel));

	}

	private void sendSms(String email, String phoneNum, String password, Long countryCodeId) {

		String countryCode = countryCodesRepository.findOne(countryCodeId).getPhonecode().toString();
		StringBuilder phoneNumber = new StringBuilder();
		phoneNumber.append("+");
		phoneNumber.append(countryCode);
		phoneNumber.append(phoneNum);

		String smsMessageTemplate = "Congratulations ! Now you can access LM Driver App using these credentials; Username : "
				+ email + " or " + phoneNum + " , " + " Password : " + password + " ";

		SMSModel smsModel = new SMSModel();

		smsModel.setMessage(smsMessageTemplate);
		smsModel.setPhoneNumber(phoneNumber.toString());

		smsService.sendSMS(smsModel);

	}
}
