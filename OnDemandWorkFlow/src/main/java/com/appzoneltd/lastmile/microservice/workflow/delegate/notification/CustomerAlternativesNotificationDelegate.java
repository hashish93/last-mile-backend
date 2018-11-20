package com.appzoneltd.lastmile.microservice.workflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.workflow.business.dao.RequestHistoryRepo;
import com.appzoneltd.lastmile.microservice.workflow.model.WorkflowNotificationModel;
import com.appzoneltd.lastmile.microservice.workflow.notification.service.OnDemandNotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by alaa.nabil on 2/19/2017.
 */
@Component
public class CustomerAlternativesNotificationDelegate implements JavaDelegate {
    private final OnDemandNotificationService notificationService;
    private final ObjectMapper mapper;

    @Autowired
    private RequestHistoryRepo requestHistoryJpaRepository;
    
    @Autowired
    public CustomerAlternativesNotificationDelegate(OnDemandNotificationService notificationService, ObjectMapper mapper) {
        this.notificationService = notificationService;
        this.mapper = mapper;
    }

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        final Long packageId = (Long) execution.getVariable("packageId");
        //final Long requestId = (Long) execution.getVariable("requestId");
        final Long customerId = (Long) execution.getVariable("requesterId");
        Long requestId=requestHistoryJpaRepository.getPackagePickupId(packageId);
        
        WorkflowNotificationModel workflowNotificationModel=new WorkflowNotificationModel();
        workflowNotificationModel.setCustomerId(customerId);
        workflowNotificationModel.setPackageId(packageId);
        workflowNotificationModel.setRequestId(requestId);
        
        notificationService.sendCustomerAlternativesNotification(workflowNotificationModel);
    }
}
