package com.appzoneltd.lastmile.microservice.forgetpassword.model;

import lombok.Data;

@Data
public class RequestParameter extends ValidationUrl {
	private String generatedCode;
	private String newPassword ;
	private String repeatedPassword;
	

}
