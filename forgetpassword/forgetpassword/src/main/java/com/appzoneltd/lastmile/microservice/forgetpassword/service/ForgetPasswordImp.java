package com.appzoneltd.lastmile.microservice.forgetpassword.service;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.appzoneltd.lastmile.microservice.forgetpassword.dao.EmailFromEntity;
import com.appzoneltd.lastmile.microservice.forgetpassword.dao.EmailFromJpaRepository;
import com.appzoneltd.lastmile.microservice.forgetpassword.dao.ForgetPasswordRepository;
import com.appzoneltd.lastmile.microservice.forgetpassword.dao.UsersRepository;
import com.appzoneltd.lastmile.microservice.forgetpassword.entity.ForgetPasswordEntity;
import com.appzoneltd.lastmile.microservice.forgetpassword.entity.UsersEntity;
import com.appzoneltd.lastmile.microservice.forgetpassword.kafka.EmailNotifierModel;
import com.appzoneltd.lastmile.microservice.forgetpassword.kafka.EmailSenderProducer;
import com.appzoneltd.lastmile.microservice.forgetpassword.model.ValidationUrl;
import com.appzoneltd.lastmile.microservice.utility.Utils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ForgetPasswordImp implements ForgetPassword {

	private final UsersRepository usersRepository;
	private final ForgetPasswordRepository forgetPasswordRepository;
	private final EmailFromJpaRepository emailFromJpaRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private Environment environment;

	@Autowired
	private EmailSenderProducer emailSenderProducer;

	@Autowired
	public ForgetPasswordImp(UsersRepository usersRepository, ForgetPasswordRepository forgetPasswordRepository,
			EmailFromJpaRepository emailFromJpaRepository) {
		this.usersRepository = usersRepository;
		this.forgetPasswordRepository = forgetPasswordRepository;
		this.emailFromJpaRepository = emailFromJpaRepository;

	}

	
	@Override
	public Boolean resetNewPassword(String email) throws JsonProcessingException {
		boolean chkUserEmailExist = false;
		UsersEntity userEntity = usersRepository.findByEmail(email);
		if (userEntity != null) {
			if (userEntity.getUserType() != null) {
				if (!userEntity.getEmail().isEmpty()
						&& ("OPERATION_BACKOFFICE".equalsIgnoreCase(userEntity.getUserType().getName())
								|| "HUB_ADMIN".equalsIgnoreCase(userEntity.getUserType().getName())
								|| "SUPERVISOR".equalsIgnoreCase(userEntity.getUserType().getName())
								|| "FREELANCER_ADMIN".equalsIgnoreCase(userEntity.getUserType().getName()))
						&& "ACTIVE".equalsIgnoreCase(userEntity.getStatus())) {
					chkUserEmailExist = true;
					String codeGenerated = saveCodeGenerated(email);
					sendEmailToResetPassword(email, codeGenerated);
				}
			}

		}
		return chkUserEmailExist;
	}

	private String saveCodeGenerated(String email) {
		String codeGenerated = Utils.codeGenerator();
		UsersEntity userEntity = usersRepository.findByEmail(email);

		ForgetPasswordEntity forgetPasswordEntity = new ForgetPasswordEntity();
		forgetPasswordEntity.setId(Utils.generateUUID());
		forgetPasswordEntity.setUserId(userEntity.getUserId());
		forgetPasswordEntity.setCodeGenerated(codeGenerated);
		forgetPasswordEntity.setEmail(email);
		forgetPasswordEntity.setCreated(new Date());

		forgetPasswordRepository.save(forgetPasswordEntity);

		return codeGenerated;

	}

	private void sendEmailToResetPassword(String email, String codeGenerated) throws JsonProcessingException {

		String emailFrom = null;
		System.out.println("sendEmailToResetPassword ");
		String urlResetPassword = environment.getProperty("forget_password.url") + codeGenerated;
		List<EmailFromEntity> emails = (List<EmailFromEntity>) emailFromJpaRepository.findAll();
		System.out.println("email size " + emails.size());
		if (emails != null && emails.size() > 0) {
			emailFrom = emails.get(0).getEmail();

		}
		if (emailFrom == null || emailFrom.isEmpty()) {
			emailFrom = environment.getProperty("forget_password.from");
		}
		Context context = new Context();
		context.setVariable("title", "Reset Password");
		context.setVariable("link", urlResetPassword);
		String body = templateEngine.process("emailFrom", context);

		// Remove ALL Carriage Return from String
		body = body.replaceAll("\\n", "");
		body = body.replaceAll("\\r", "");
		body = body.replaceAll("\"", "\'");

		// Push Email Object To kafka topic
		EmailNotifierModel emailNotifierModel = new EmailNotifierModel();
		emailNotifierModel.setFrom(emailFrom);
		emailNotifierModel.setTo(email);
		emailNotifierModel.setSubject("Reset Password");
		emailNotifierModel.setBody(body);
		System.out.println("emailNotifierModel " + emailNotifierModel.toString());
		emailSenderProducer.sendMessage("EMAIL-SERVICE",
				objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(emailNotifierModel));

	}

	@Override
	public ValidationUrl validateGeneratedCode(String genratedCode) {

		ValidationUrl validationUrl = new ValidationUrl();
		validationUrl.setIsGeneratedCodeValid(false);
		validationUrl.setEmail("");

		ForgetPasswordEntity forgetPasswordEntity = forgetPasswordRepository.findByCodeGenerated(genratedCode);
		if (forgetPasswordEntity != null) {
			Date expireDate = addHoursToTime(forgetPasswordEntity.getCreated(), 5);

			if (!forgetPasswordEntity.getCodeGenerated().isEmpty()) {
				if (expireDate.after(new Date())) {
					validationUrl.setIsGeneratedCodeValid(true);
					validationUrl.setEmail(forgetPasswordEntity.getEmail());

				}

			}
		}

		return validationUrl;
	}

	@Override
	public Boolean resetNewPassword(String newPassword, String repeatedPassword, String email) {
		boolean validSavingPassword = false;
		if (newPassword.equalsIgnoreCase(repeatedPassword)) {
			UsersEntity usersEntity = usersRepository.findByEmail(email);
			usersEntity.setPassword(newPassword);
			usersRepository.save(usersEntity);
			validSavingPassword = true;
		}

		return validSavingPassword;
	}

	private Date addHoursToTime(Date date, int hours) {

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.HOUR, hours);
		// return
		return cal.getTime();
	}

}
