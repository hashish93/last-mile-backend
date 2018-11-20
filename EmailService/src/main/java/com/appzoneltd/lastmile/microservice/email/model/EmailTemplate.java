package com.appzoneltd.lastmile.microservice.email.model;

import org.hibernate.validator.constraints.NotEmpty;

public class EmailTemplate {

	@NotEmpty
	private String from;
	@NotEmpty
	private String to;
	@NotEmpty
	private String subject;
	@NotEmpty
	private String body;
	
	
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}

	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
}
