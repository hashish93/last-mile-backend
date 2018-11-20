package com.appzoneltd.lastmile.microservice.employee.service.employee;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.appzoneltd.lastmile.microservice.employee.dao.CountryCodesRepository;
import com.appzoneltd.lastmile.microservice.employee.dao.DriverRepository;
import com.appzoneltd.lastmile.microservice.employee.dao.DrivingLicenseTypeRepository;
import com.appzoneltd.lastmile.microservice.employee.dao.EmailFromJpaRepository;
import com.appzoneltd.lastmile.microservice.employee.dto.EmailNotifierModel;
import com.appzoneltd.lastmile.microservice.employee.entity.DriverEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.DrivingLicenseTypeEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.EmailFromEntity;
import com.appzoneltd.lastmile.microservice.employee.kafka.KafkaSender;
import com.appzoneltd.lastmile.microservice.employee.model.Driver;
import com.appzoneltd.lastmile.microservice.employee.model.Employee;
import com.appzoneltd.lastmile.microservice.employee.sms.SMSEmailUtils;
import com.appzoneltd.lastmile.microservice.employee.sms.UnRegisteredSmsModel;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class DriverService extends EmployeeAbstractService {

	@Autowired
	private DrivingLicenseTypeRepository drivingLicenseTypeRepository;

	@Autowired
	private DriverRepository driverRepository;

	@Autowired
	private SMSEmailUtils smsUtils;

	@Autowired
	private KafkaSender kafkaSender;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private Environment environment;

	@Autowired
	private EmailFromJpaRepository emailFromJpaRepository;

	@Autowired
	private CountryCodesRepository countryCodesRepository;

	@Override
	protected List<Message> saveOrUpdate(Employee employee, Principal principal) {
		employee = SetHubForEmployee(employee, principal);
		return saveUserData(employee);
	}

	public List<Message> saveOrUpdateDriver(Driver driver, Principal principal) throws JsonProcessingException {
		if (driver.getUserId() == null) {
			driver.setUserId(Utils.generateUUID());
		}
		driver.setUserTypeId(6L);
		List<Message> messages = saveOrUpdate(driver, principal);
		if (messages == null) {
			saveDriverData(driver);
		}

		return messages;
	}

	private void saveDriverData(Driver driver) throws JsonProcessingException {

		DriverEntity driverEntity = new DriverEntity();
		driverEntity.setId(driver.getUserId());
		driverEntity.setDrivingLicenseId(driver.getDrivingLicenseId());
		if (driver.getDrivingLicenseTypeId() != null) {
			DrivingLicenseTypeEntity drivingLicenseTypeEntity = drivingLicenseTypeRepository
					.findOne(driver.getDrivingLicenseTypeId());
			driverEntity.setDrivingLicenseType(drivingLicenseTypeEntity);
		}
		driverEntity.setDrivingLicenseExpDate(driver.getDrivingLicenseExpDate());

		driverRepository.save(driverEntity);

		sendEmail(driver.getEmail(), driver.getPhone(), driver.getPassword());
		sendSms(driver.getEmail(), driver.getPhone(), driver.getPassword(), driver.getCountryCodeId());

	}

	private void sendEmail(String email, String phoneNumber, String password) throws JsonProcessingException {

		List<EmailFromEntity> emails = (List<EmailFromEntity>) emailFromJpaRepository.findAll();
		String emailFrom = emails.get(0).getEmail();
//		if (emailFrom == null || emailFrom.isEmpty()) {
//			emailFrom = environment.getProperty("send_email.from");
//		}

		Context context = new Context();
		context.setVariable("UserName", phoneNumber + "  or  " + email);
		context.setVariable("Password", password);
		//context.setVariable("email_support", environment.getProperty("send_email.email_support"));

		String body = templateEngine.process("emailFrom", context);

		body = body.replaceAll("\\n", "");
		body = body.replaceAll("\\r", "");
		body = body.replaceAll("\"", "\'");

		EmailNotifierModel emailNotifierModel = new EmailNotifierModel();
		emailNotifierModel.setFrom(emailFrom);
		emailNotifierModel.setTo(email);
		emailNotifierModel.setSubject("LastMile Info");
		emailNotifierModel.setBody(body);

		kafkaSender.sendMessage("EMAIL-SERVICE",
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

		UnRegisteredSmsModel smsModel = new UnRegisteredSmsModel();

		smsModel.setMessage(smsMessageTemplate);
		smsModel.setPhoneNumber(phoneNumber.toString());

		smsUtils.sendSms(smsModel);

	}

}
