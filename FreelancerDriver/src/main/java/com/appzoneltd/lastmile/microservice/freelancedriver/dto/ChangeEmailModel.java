package com.appzoneltd.lastmile.microservice.freelancedriver.dto;

import org.hibernate.validator.constraints.NotBlank;

import java.io.Serializable;

public class ChangeEmailModel implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -7920404001185337427L;

    @NotBlank
    private String newEmail;

	
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

}
