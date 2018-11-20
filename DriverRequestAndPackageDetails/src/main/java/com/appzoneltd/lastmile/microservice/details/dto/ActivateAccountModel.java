package com.appzoneltd.lastmile.microservice.details.dto;

import java.io.Serializable;

public class ActivateAccountModel implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6119628939281500123L;
	
	private String phone;
    private String token;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
