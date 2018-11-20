package com.appzoneltd.lastmile.microservice.freelancedriver.dto;

import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

/**
 * Created by alaa.nabil on 5/14/2017.
 */
public class NewPassword implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5838683236819284435L;
	
//	@Pattern(regexp = "^[0-9+]?[0-9]*$", message = "user.phone.errorformat")
//    private String phone;
	@NotBlank(message="user.oldpassword.missing")
    private String oldPassword ;
	@NotBlank(message="user.newpassword.missing")
    private String newPassword;

//    public String getPhone() {
//        return phone;
//    }
//
//    public void setPhone(String phone) {
//        this.phone = phone;
//    }

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

//    public String getPassword() {
//        return Password;
//    }
//
//    public void setPassword(String password) {
//        Password = password;
//    }
}
