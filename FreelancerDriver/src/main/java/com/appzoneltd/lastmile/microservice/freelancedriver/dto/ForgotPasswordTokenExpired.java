package com.appzoneltd.lastmile.microservice.freelancedriver.dto;

/**
 * Created by alaa.nabil on 5/14/2017.
 */
public class ForgotPasswordTokenExpired extends Exception {
    public ForgotPasswordTokenExpired(String message) {
        super(message);
    }
}
