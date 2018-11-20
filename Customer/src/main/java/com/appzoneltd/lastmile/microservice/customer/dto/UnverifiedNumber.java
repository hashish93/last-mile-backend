package com.appzoneltd.lastmile.microservice.customer.dto;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

/**
 * Created by alaa.nabil on 3/20/2017.
 */
public class UnverifiedNumber extends CustomerVerification implements Serializable {

    @NotNull(message = "user.mobile.required")
    private Long countryCode;
    @NotEmpty(message = "user.mobile.required")
    @Pattern(regexp = "^[0-9+]?[0-9]*$", message = "user.phone.errorformat")
    private String phone;

    public Long getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Long countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
