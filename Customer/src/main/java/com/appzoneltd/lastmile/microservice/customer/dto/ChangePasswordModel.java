package com.appzoneltd.lastmile.microservice.customer.dto;

/**
 * Created by alaa.nabil on 5/14/2017.
 */
public class ChangePasswordModel {
    private String oldPassword;
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }
}
