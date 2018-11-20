package com.appzoneltd.lastmile.microservice.email.service.sender.util.basic;

import java.util.concurrent.Callable;

import com.appzoneltd.lastmile.microservice.email.model.EmailSenderResult;
import com.appzoneltd.lastmile.microservice.email.model.EmailTemplate;

public abstract class BasicSenderUtil implements Callable<EmailSenderResult> {

	public abstract void setEmailObject(EmailTemplate validatedEmailObject);

}
