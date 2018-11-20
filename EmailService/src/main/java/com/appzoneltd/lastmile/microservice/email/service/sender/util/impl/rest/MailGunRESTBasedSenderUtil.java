package com.appzoneltd.lastmile.microservice.email.service.sender.util.impl.rest;

import javax.ws.rs.core.MediaType;

import com.appzoneltd.lastmile.microservice.email.model.EmailSenderResult;
import com.appzoneltd.lastmile.microservice.email.model.EmailTemplate;
import com.appzoneltd.lastmile.microservice.email.service.sender.util.impl.RESTBasedSenderUtil;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;

public class MailGunRESTBasedSenderUtil extends RESTBasedSenderUtil {

	private EmailTemplate emailObject;

	@Override
	public void setEmailObject(EmailTemplate validatedEmailObject) {

		this.emailObject = validatedEmailObject;

	}

	@Override
	public EmailSenderResult call() throws Exception {

		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter("api", "key-8dd85ebb7362655c3e57d80e2a43c670"));
		WebResource webResource = 
				client.resource("https://api.mailgun.net/v3/sandboxb55163a7bf114c6ca603ce2d7c1a4057.mailgun.org/messages");
		
		MultivaluedMapImpl formData = new MultivaluedMapImpl();
		
		formData.add("from", this.emailObject.getFrom() + " <"+this.emailObject.getFrom()+">");
		formData.add("to", this.emailObject.getTo() + " <"+this.emailObject.getTo()+">");
		formData.add("subject", this.emailObject.getSubject());
		formData.add("text", this.emailObject.getBody());

		ClientResponse response = 
				webResource.type(MediaType.APPLICATION_FORM_URLENCODED).
				post(ClientResponse.class, formData);

		EmailSenderResult result = new EmailSenderResult();
		result.setTo(this.emailObject.getTo());

		System.out.println(response.getStatus() );

		if (response.getStatus() != 200) {
			result.setErrorDescription("ERROR");
		} 
		result.setStatus(response.getStatus());

		return result;
	}


}
