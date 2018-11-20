package com.appzoneltd.lastmile.microservice.returnworkflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.service.ReturnRequestService;
import com.appzoneltd.lastmile.microservice.returnworkflow.notification.model.NotificationParameter;
import com.appzoneltd.lastmile.microservice.returnworkflow.notification.service.ReturnPushNotificationService;

@Service
public class SendDriverArrivedForReturnNotificationDelagate implements JavaDelegate {

	@Autowired
	private ReturnPushNotificationService notificationService;

	@Autowired
	private ReturnRequestService returnRequestService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Long packageId = (Long) execution.getVariable("packageId");
		Long receipentId = returnRequestService.getReceipentForPackage(packageId);

		if (receipentId != null) {
			NotificationParameter notificationParameter = new NotificationParameter();
			notificationParameter.setPackageId(packageId);
			notificationParameter.setCustomerId(receipentId);

			notificationService.sendDriverArrivedNotification(notificationParameter);
			System.out.println(">>>>>>>>>>>>>>>> notification sent to customerId "+receipentId);
			System.out.println(">>>>>>>>>>>>>>>> notification sent withpackageId "+packageId);
		}
	}


}
