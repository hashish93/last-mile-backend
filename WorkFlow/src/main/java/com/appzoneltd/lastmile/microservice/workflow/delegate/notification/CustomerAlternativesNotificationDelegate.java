package com.appzoneltd.lastmile.microservice.workflow.delegate.notification;

import com.appzoneltd.lastmile.microservice.workflow.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by alaa.nabil on 2/19/2017.
 */
@Component
public class CustomerAlternativesNotificationDelegate implements JavaDelegate {
    private final NotificationService notificationService;
    private final ObjectMapper mapper;

    @Autowired
    public CustomerAlternativesNotificationDelegate(NotificationService notificationService, ObjectMapper mapper) {
        this.notificationService = notificationService;
        this.mapper = mapper;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        final Long packageId = (Long) execution.getVariable("packageId");
        final Long requestId = (Long) execution.getVariable("requestId");
        final Long customerId = (Long) execution.getVariable("requesterId");
        notificationService.sendCustomerAlternativesNotification(customerId, packageId);
    }
}
