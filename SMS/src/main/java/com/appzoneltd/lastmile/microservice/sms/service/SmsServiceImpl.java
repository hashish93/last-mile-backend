package com.appzoneltd.lastmile.microservice.sms.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Created by alaa.nabil on 3/14/2017.
 */
@Service
public class SmsServiceImpl implements SmsService {
    private final String NUMBER;

    public SmsServiceImpl(@Value("${twilio.SID}") String sid, @Value("${twilio.auth-token}") String auth_token, @Value("${twilio.number}") String number) {
        NUMBER = number;
        Twilio.init(sid, auth_token);
    }

    @Override
    public String sendSMS(String to, String body) throws Exception {
        Message message = Message.creator(new PhoneNumber(to), new PhoneNumber(NUMBER), body).create();
        return message.getStatus().name();
    }
}
