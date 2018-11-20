package com.appzoneltd.lastmile.microservice.returnworkflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.dao.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.returnworkflow.business.service.ReturnRequestService;
import com.appzoneltd.lastmile.microservice.returnworkflow.notification.model.NotificationParameter;
import com.appzoneltd.lastmile.microservice.returnworkflow.notification.service.ReturnPushNotificationService;

@Service
public class SendInvoiceNotificationToCustomerDelegate implements JavaDelegate {

	@Autowired
	private ReturnPushNotificationService notificationService;

	@Autowired
	private ReturnRequestService returnRequestService;
	
	@Autowired
	private RequestHistoryJpaRepository requestHistoryJpaRepository;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Long packageId = (Long) execution.getVariable("packageId");
		Long receipentId = returnRequestService.getReceipentForPackage(packageId);
		Long requestId=requestHistoryJpaRepository.getPackageReturnId(packageId);
		
		if (receipentId != null) {
			NotificationParameter notificationParameter = new NotificationParameter();
			notificationParameter.setPackageId(packageId);
			notificationParameter.setCustomerId(receipentId);
			notificationParameter.setRequestId(requestId);
			
			
			notificationService.sendInvoiceToCustomer(notificationParameter);
		}
	}

}