package com.appzoneltd.lastmile.microservice.customer.dto;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by alaa.nabil on 5/14/2017.
 */
public class ForgetPasswordVerifyToken implements Serializable{

    @Pattern(regexp = "^[0-9+]?[0-9]*$", message = "user.phone.errorformat")
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
