package com.appzoneltd.lastmile.microservice.workflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflow.service.NotificationService;

@Service
public class SendTrackingNumberNotificationDelegate implements JavaDelegate{

	@Autowired
	private NotificationService notificationService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(" Sending Notification ");
		Long packageId=(Long) execution.getVariable("packageId");
		Long userId=(Long) execution.getVariable("requesterId");
		String trackingNumber=(String)execution.getVariable("trackingNumber");
		System.out.println("SENDING PUSH NOTIFICATION ");
		notificationService.sendTrackingNumberNotification(userId, packageId,trackingNumber);
		
	}


}