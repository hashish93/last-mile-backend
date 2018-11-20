package com.appzoneltd.lastmile.microservice.ondemandworkflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.model.WorkflowNotificationModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.service.OnDemandNotificationService;

/**
 * Created by alaa.nabil on 2/19/2017.
 */
@Component
public class CustomerAlternativesNotificationDelegate implements JavaDelegate {
    
	@Autowired
    private OnDemandNotificationService notificationService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long packageId = (Long) execution.getVariable("packageId");
        Long requestId = (Long) execution.getVariable("requestId");
        Long customerId = (Long) execution.getVariable("requesterId");
        
        WorkflowNotificationModel workflowNotificationModel=new WorkflowNotificationModel();
        workflowNotificationModel.setCustomerId(customerId);
        workflowNotificationModel.setPackageId(packageId);
        workflowNotificationModel.setRequestId(requestId);
        
        notificationService.sendCustomerAlternativesNotification(workflowNotificationModel);
    }
}
