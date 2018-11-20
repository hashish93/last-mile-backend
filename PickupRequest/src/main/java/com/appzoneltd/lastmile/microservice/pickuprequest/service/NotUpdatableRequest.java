package com.appzoneltd.lastmile.microservice.pickuprequest.service;

/**
 * Created by alaa.nabil on 5/15/2017.
 */
public class NotUpdatableRequest extends Exception {
    public NotUpdatableRequest(String msg) {
        super(msg);
    }
}
