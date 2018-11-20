package com.appzoneltd.lastmile.microservice.workflow.delegate.notification;

import com.appzoneltd.lastmile.microservice.workflow.service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by alaa.nabil on 2/20/2017.
 */
@Component
public class ScheduleOndemandNotificationDelegate implements JavaDelegate {
    private final Logger logger = LoggerFactory.getLogger(ScheduleOndemandNotificationDelegate.class);
    private final NotificationService notificationService;
    private final ObjectMapper mapper;

    @Autowired
    public ScheduleOndemandNotificationDelegate(NotificationService notificationService, ObjectMapper mapper) {
        this.notificationService = notificationService;
        this.mapper = mapper;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        final Long packageId = (Long) execution.getVariable("packageId");
        final Long requestId = (Long) execution.getVariable("requestId");
        final Long customerId = (Long) execution.getVariable("requesterId");
        final Date pickupDate = (Date) execution.getVariable("pickupDate");
        final String timeFrom = (String) execution.getVariable("from");
        final String timeTo = (String) execution.getVariable("to");

        logger.info("SEND PUSH NOTIFICATION TO  {}  WITH PACKAGE ID  {}", customerId, packageId);

        notificationService.sendScheduleOndemandPickupNotificationToCustomer(customerId, packageId, pickupDate, timeFrom, timeTo);
    }
}
