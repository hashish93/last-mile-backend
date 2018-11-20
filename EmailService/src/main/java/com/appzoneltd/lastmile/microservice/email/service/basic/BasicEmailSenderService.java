package com.appzoneltd.lastmile.microservice.email.service.basic;

import com.appzoneltd.lastmile.microservice.email.exception.MailSendingFailureException;

public interface BasicEmailSenderService {

	public void sendEmail(String emailJson) throws MailSendingFailureException ;
}
