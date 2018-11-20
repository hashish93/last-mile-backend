package com.appzoneltd.lastmile.microservice.sms.service;

/**
 * Created by alaa.nabil on 3/13/2017.
 */
public interface SmsService {

    String sendSMS(String to, String body) throws Exception;

}
