package com.appzoneltd.lastmile.microservice.details.service.utils;

import com.appzoneltd.lastmile.microservice.details.dao.entity.EmailFromEntity;
import com.appzoneltd.lastmile.microservice.details.dao.repos.EmailFromJpaRepository;
import com.appzoneltd.lastmile.microservice.details.dto.EmailTemplate;
import com.appzoneltd.lastmile.microservice.details.dto.SmsModel;
import com.appzoneltd.lastmile.microservice.details.dto.UnRegisteredSmsModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.List;

@Component
public class SMSEmailUtils {

    private final static String SMS_TOPIC = "SMS-NOTIFICATION-UNREGISTERED";
    private final static String EMAIL_SERVICE = "EMAIL-SERVICE";
    private final KafkaTemplate<Long, String> kafkaTemplate;
    private final EmailFromJpaRepository emailFromJpaRepository;
    private final TemplateEngine templateEngine;
    private final ObjectMapper mapper;

    @Autowired
    public SMSEmailUtils(KafkaTemplate<Long, String> kafkaTemplate, EmailFromJpaRepository emailFromJpaRepository, TemplateEngine templateEngine) {
        this.kafkaTemplate = kafkaTemplate;
        this.emailFromJpaRepository = emailFromJpaRepository;
        this.templateEngine = templateEngine;
        this.mapper = new ObjectMapper();
    }

    public void sendEmail(EmailTemplate fullMessageTempleteInfo) {
        try {
            kafkaTemplate.send(EMAIL_SERVICE, mapper.writeValueAsString(fullMessageTempleteInfo));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendSms(String fullPhoneNumber, String message) {

        try {

            kafkaTemplate.send(SMS_TOPIC,
                    mapper.writeValueAsString(new UnRegisteredSmsModel(fullPhoneNumber, message)));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendSms(UnRegisteredSmsModel messageModel) {

        try {

            kafkaTemplate.send(SMS_TOPIC,
                    mapper.writeValueAsString(messageModel));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendSmsById(Long id, String message) {
        Long[] ids = {id};
        try {

            kafkaTemplate.send("SMS-NOTIFICATION", mapper.writeValueAsString(new SmsModel(ids, "DRIVER", message)));

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

    }

    public void sendChangeEmailTemplate(String userName, String email, String subject, String bodyMsg) throws JsonProcessingException {

        List<EmailFromEntity> emails = (List<EmailFromEntity>) emailFromJpaRepository.findAll();
        String emailFrom = emails.get(0).getEmail();

        Context context = new Context();
        context.setVariable("userName", userName);
        context.setVariable("body", bodyMsg);

        String body = templateEngine.process("EmailChangeConfirmation", context);

        body = body.replaceAll("\\n", "");
        body = body.replaceAll("\\r", "");
        body = body.replaceAll("\"", "\'");

        EmailTemplate emailNotifierModel = new EmailTemplate();
        emailNotifierModel.setFrom(emailFrom);
        emailNotifierModel.setTo(email);
        emailNotifierModel.setSubject(subject);
        emailNotifierModel.setBody(body);

        kafkaTemplate.send(EMAIL_SERVICE,
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emailNotifierModel));
    }
}
