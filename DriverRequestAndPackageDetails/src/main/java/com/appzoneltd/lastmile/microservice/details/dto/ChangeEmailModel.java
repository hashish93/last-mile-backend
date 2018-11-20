package com.appzoneltd.lastmile.microservice.details.dto;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import java.io.Serializable;

public class ChangeEmailModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7920404001185337427L;

    @NotBlank(message = "employee.email.required")
    @NotEmpty(message = "user.email.required")
    @Pattern(regexp = "^[a-zA-Z]+[a-zA-Z0-9.]+@[a-zA-Z]+.[a-zA-Z]{2,5}$", message = "customer.email.errorformat")
    private String newEmail;
    private String password;


    //	public String getOldEmail() {
//		return oldEmail;
//	}
//	public void setOldEmail(String oldEmail) {
//		this.oldEmail = oldEmail;
//	}
    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
