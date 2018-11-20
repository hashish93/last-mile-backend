package com.appzoneltd.lastmile.microservice.email.service.sender.util.impl.rest;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import javax.ws.rs.core.MediaType;

import org.apache.commons.io.IOUtils;

import com.appzoneltd.lastmile.microservice.email.model.EmailSenderResult;
import com.appzoneltd.lastmile.microservice.email.model.EmailTemplate;
import com.appzoneltd.lastmile.microservice.email.service.sender.util.impl.RESTBasedSenderUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class SendGridSenderUtil extends RESTBasedSenderUtil {

	private EmailTemplate emailObject;

	@Override
	public void setEmailObject(EmailTemplate validatedEmailObject) {

		this.emailObject = validatedEmailObject;

	}
	@Override
	public EmailSenderResult call() throws Exception {
		
		String mailRequestBody = readBodyFromResources();
		
		mailRequestBody = mailRequestBody.replace("$from", this.emailObject.getFrom()).replace("$to", this.emailObject.getTo())
		.replace("$subject", this.emailObject.getSubject()).replace("$body", this.emailObject.getBody());
		
		Client client = Client.create();
		WebResource webResource = 
				client.resource("https://api.sendgrid.com/v3/mail/send");

		ClientResponse response = 
				webResource.header("Authorization", "Bearer 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").
				header("Content-Type", "application/json").
				post(ClientResponse.class, mailRequestBody);

		EmailSenderResult result = new EmailSenderResult();
		result.setTo(this.emailObject.getTo());

		System.out.println(response.getStatus() );

		if (response.getStatus() != 200) {
			result.setErrorDescription("ERROR");
		} 
		result.setStatus(response.getStatus());

		return result;
	}
	private String readBodyFromResources() throws IOException {
		
		InputStream stream = getClass().getResourceAsStream("/sendgrid_template.txt");
		
		return IOUtils.toString(stream, Charset.forName("UTF-8"));
	}

}
