package com.appzoneltd.lastmile.microservice.workflow.delegate.notification;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.workflow.business.dao.RequestHistoryRepo;
import com.appzoneltd.lastmile.microservice.workflow.model.WorkflowNotificationModel;
import com.appzoneltd.lastmile.microservice.workflow.notification.service.OnDemandNotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by alaa.nabil on 2/20/2017.
 */
@Component
public class ScheduleOndemandNotificationDelegate implements JavaDelegate {
    private final Logger logger = LoggerFactory.getLogger(ScheduleOndemandNotificationDelegate.class);
    private final OnDemandNotificationService notificationService;
    private final ObjectMapper mapper;

    @Autowired
    private RequestHistoryRepo requestHistoryJpaRepository;
    
    @Autowired
    public ScheduleOndemandNotificationDelegate(OnDemandNotificationService notificationService, ObjectMapper mapper) {
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

        Long theRequestId=requestHistoryJpaRepository.getPackagePickupId(packageId);
        
        logger.info("SEND PUSH NOTIFICATION TO  {}  WITH PACKAGE ID  {}", customerId, packageId);
        
        WorkflowNotificationModel workflowNotificationModel=new WorkflowNotificationModel();
        workflowNotificationModel.setCustomerId(customerId);
        workflowNotificationModel.setPackageId(packageId);
        workflowNotificationModel.setRequestId(theRequestId);

        notificationService.sendScheduleOndemandPickupNotificationToCustomer(workflowNotificationModel, pickupDate, timeFrom, timeTo);
    }
}
