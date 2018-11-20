package com.appzoneltd.lastmile.microservice.workflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflow.service.NotificationService;

@Service
public class SendOutOfRangeNotificationDelegate implements JavaDelegate{

	@Autowired
	private NotificationService notificationService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(" Sending Notification Out of Range ");
		Long packageId=(Long) execution.getVariable("packageId");
		Long customerId=(Long) execution.getVariable("requesterId");
		// Sending Notification
		notificationService.sendOutOfRangeNotification(packageId, customerId);
	}

	
}
