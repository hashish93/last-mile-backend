package com.appzoneltd.lastmile.microservice.returnworkflow.delegate.notification;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.dao.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.entity.ReturnRequestEntity;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.service.ReturnRequestService;
import com.appzoneltd.lastmile.microservice.returnworkflow.notification.model.NotificationParameter;
import com.appzoneltd.lastmile.microservice.returnworkflow.notification.service.ReturnPushNotificationService;



@Service
public class SendRescheduledReturnNotificationDelegate implements JavaDelegate {

	@Autowired
	private ReturnPushNotificationService notificationService;
	
	@Autowired
	private ReturnRequestService returnRequestService;

	@Autowired
	private RequestHistoryJpaRepository requestHistoryJpaRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		Long packageId = (Long) execution.getVariable("packageId");
		
		ReturnRequestEntity returnRequestEntity=returnRequestService.getReturnForPackageId(packageId);
		Long receipientId = returnRequestEntity.getRecipientId();
		Date returnDate=returnRequestEntity.getReturnDate();
		Long requestId=requestHistoryJpaRepository.getPackageReturnId(packageId);
		
		if(receipientId!=null){

			NotificationParameter notificationParameter = new NotificationParameter();
			notificationParameter.setPackageId(packageId);
			notificationParameter.setCustomerId(receipientId);
			notificationParameter.setReturnDate(returnDate);
			notificationParameter.setRequestId(requestId);
		
			notificationService.sendRescheduledReturn(notificationParameter);
			System.out.println("Notification send to user with Rescheduled For Return");
		}
	}

}