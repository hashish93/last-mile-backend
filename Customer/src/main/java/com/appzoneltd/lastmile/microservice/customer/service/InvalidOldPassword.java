package com.appzoneltd.lastmile.microservice.customer.service;

/**
 * Created by alaa.nabil on 5/14/2017.
 */
public class InvalidOldPassword extends Exception {
    public InvalidOldPassword(String s) {
        super(s);
    }
}
