package com.appzoneltd.lastmile.microservice.sms.controller;

import com.appzoneltd.lastmile.microservice.sms.dao.entity.UsersEntity;
import com.appzoneltd.lastmile.microservice.sms.dao.repository.CustomerRepository;
import com.appzoneltd.lastmile.microservice.sms.dao.repository.UsersRepository;
import com.appzoneltd.lastmile.microservice.sms.dto.SmsModel;
import com.appzoneltd.lastmile.microservice.sms.dto.UnRegisteredModel;
import com.appzoneltd.lastmile.microservice.sms.service.SmsService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Arrays;

/**
 * Created by alaa.nabil on 3/9/2017.
 */
@Service
public class SmsKafkaListener {
    private final static Logger LOGGER = LoggerFactory.getLogger(SmsKafkaListener.class);
    private final static String PUBLISH_TOPIC = "SMS-RESPONSE";
    private final KafkaTemplate<Long, String> kafkaProducer;
    private final SmsService smsService;
    private final CustomerRepository customerRepository;
    private final UsersRepository usersRepository;
    private final ObjectMapper mapper;

    @Autowired
    public SmsKafkaListener(KafkaTemplate<Long, String> kafkaProducer, SmsService smsService, CustomerRepository customerRepository, UsersRepository usersRepository) {
        this.smsService = smsService;
        this.customerRepository = customerRepository;
        this.usersRepository = usersRepository;
        this.mapper = new ObjectMapper();
        this.kafkaProducer = kafkaProducer;
    }

    @KafkaListener(topics = "SMS-NOTIFICATION")
    public void listen(@Payload String model)
            throws JsonParseException, JsonMappingException, IOException {
        LOGGER.info("SMS MODEL :: {}", model);
        SmsModel smsModel = mapper.readValue(model, SmsModel.class);

        Arrays.asList(smsModel.getIds()).forEach(id -> {
            try {
                sendResult(smsService.sendSMS(mapIdToMobileNumber(id, smsModel.getReceiverType()), smsModel.getMessage()));
            } catch (Exception e) {
                LOGGER.error(e.getMessage(), e);
                sendResult(e.getMessage());
            }
        });
    }

    @KafkaListener(topics = "SMS-NOTIFICATION-UNREGISTERED")
    public void smsToUnRegisteredUser(@Payload String model)
            throws JsonParseException, JsonMappingException, IOException {
        LOGGER.info("SMS NOTIFICATION-UNREGISTERED :: {}", model);
        UnRegisteredModel unRegisteredModel = mapper.readValue(model, UnRegisteredModel.class);

        try {
            sendResult(smsService.sendSMS(unRegisteredModel.getPhoneNumber(), unRegisteredModel.getMessage()));
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            sendResult(e.getMessage());
        }

    }

    private void sendResult(String result) {
        kafkaProducer.send(PUBLISH_TOPIC, result);
    }

    private String mapIdToMobileNumber(Long id, String receiverType) {
        if (id == null)
            return null;

        UsersEntity usersEntity = usersRepository.findOne(id);

        if ("DRIVER".equalsIgnoreCase(receiverType))
            return buildMobileNumber(usersEntity.getCountryCodes().getPhonecode().toString(), usersEntity.getPhone());
        
        return buildMobileNumber(customerRepository.findOne(id).getCountryCodes().getPhonecode().toString()
                , usersEntity.getPhone());
    }

    private String buildMobileNumber(String countryCode, String mobile) {
        if (countryCode.endsWith("0"))
            if (mobile.startsWith("0"))
                return "+" + countryCode + mobile.replaceFirst("0", "");
        return "+" + countryCode + mobile;
    }
}
