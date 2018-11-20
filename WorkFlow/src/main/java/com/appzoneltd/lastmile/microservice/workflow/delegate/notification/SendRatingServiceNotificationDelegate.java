package com.appzoneltd.lastmile.microservice.workflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.workflow.service.NotificationService;

@Service
public class SendRatingServiceNotificationDelegate implements JavaDelegate{

	@Autowired
	private NotificationService notificationService;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		// TODO Auto-generated method stub
		System.out.println(" SendRatingServiceNotificationDelegate ");
		Long packageId=(Long) execution.getVariable("packageId");
		Long driverId=(Long) execution.getVariable("driverId");
		Long customerId=(Long) execution.getVariable("requesterId");
		Thread.sleep(100);
		notificationService.sendRatingServiceNotification(packageId,customerId,driverId);
		
	}


}
