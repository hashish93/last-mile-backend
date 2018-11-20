package com.appzoneltd.lastmile.microservice.customer.service;

/**
 * Created by alaa.nabil on 5/14/2017.
 */
public class ExceededNumberOfAttempts extends Exception {
    public ExceededNumberOfAttempts(String message) {
        super(message);
    }
}
