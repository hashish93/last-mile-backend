package com.appzoneltd.lastmile.microservice.email.service.impl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.beans.factory.config.AbstractFactoryBean;

import com.appzoneltd.lastmile.microservice.email.exception.MailSendingFailureException;
import com.appzoneltd.lastmile.microservice.email.exception.MissingEmailFieldsException;
import com.appzoneltd.lastmile.microservice.email.model.EmailTemplate;
import com.appzoneltd.lastmile.microservice.email.service.basic.BasicEmailSenderService;
import com.appzoneltd.lastmile.microservice.email.service.sender.util.basic.BasicSenderUtil;
import com.google.gson.Gson;

public class EmailSenderServiceImpl implements BasicEmailSenderService {

	protected ExecutorService sendersExecuter;
	protected AbstractFactoryBean<BasicSenderUtil> senderUtilsCreationFactory;
	private Gson gson;
	
	public EmailSenderServiceImpl(AbstractFactoryBean<BasicSenderUtil> inputUtilsFactory,int maxThreadsCount) {
		gson = new Gson();
		this.senderUtilsCreationFactory = inputUtilsFactory;
		initExecutersThreadPool(maxThreadsCount);
	}
	
	private void initExecutersThreadPool(int maxThreadsCount) {
		
		sendersExecuter = Executors.newFixedThreadPool(maxThreadsCount);
	}

	@Override
	public void sendEmail(String emailJson) throws MailSendingFailureException {
		
		EmailTemplate validatedEmailObject = null;
		try {
			
			validatedEmailObject = validateEmailInput(emailJson);
			
			BasicSenderUtil currentSenderThread = this.senderUtilsCreationFactory.getObject();
			
			
			currentSenderThread.setEmailObject(validatedEmailObject);
			
			sendersExecuter.submit(currentSenderThread);
			
		} catch (MissingEmailFieldsException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logEmailSendingToAudit();
		
		
	}
	
	protected void logEmailSendingToAudit() {
		System.out.println("will log the the email process");
	}


	protected EmailTemplate validateEmailInput(String mailJson) throws MissingEmailFieldsException {
		return gson.fromJson(mailJson, EmailTemplate.class);
	}
}
