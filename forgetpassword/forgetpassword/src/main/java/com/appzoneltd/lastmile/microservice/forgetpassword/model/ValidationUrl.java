package com.appzoneltd.lastmile.microservice.forgetpassword.model;

import lombok.Data;

@Data
public class ValidationUrl {
	
	
	private Boolean isGeneratedCodeValid ;
	private String email ;

}
