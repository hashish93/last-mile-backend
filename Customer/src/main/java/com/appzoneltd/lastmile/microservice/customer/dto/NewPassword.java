package com.appzoneltd.lastmile.microservice.customer.dto;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by alaa.nabil on 5/14/2017.
 */
public class NewPassword implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -1621359054574087336L;
	@Pattern(regexp = "^[0-9+]?[0-9]*$", message = "user.phone.errorformat")
    private String phone;
    private String password;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
