package com.appzoneltd.lastmile.microservice.employee.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by alaa.nabil on 10/10/2017.
 */
public class NewEmail {
    @NotBlank(message = "employee.email.required")
    @NotEmpty(message = "user.email.required")
    @Pattern(regexp = "^[a-zA-Z]+[a-zA-Z0-9.]+@[a-zA-Z]+.[a-zA-Z]{2,5}$", message = "customer.email.errorformat")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
