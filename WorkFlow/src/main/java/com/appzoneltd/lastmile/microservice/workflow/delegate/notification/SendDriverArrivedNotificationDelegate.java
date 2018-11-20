package com.appzoneltd.lastmile.microservice.workflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflow.service.NotificationService;

@Service
public class SendDriverArrivedNotificationDelegate implements JavaDelegate{

	@Autowired
	private NotificationService notificationService;
	
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		// Getting PackageId to Process it 
		Long packageId=(Long) execution.getVariable("packageId");
		Long userId=(Long) execution.getVariable("userId");
		// Send Notification
		notificationService.sendDriverArrivedNotification(userId,packageId);
		
	}//end Execute 
	
}
