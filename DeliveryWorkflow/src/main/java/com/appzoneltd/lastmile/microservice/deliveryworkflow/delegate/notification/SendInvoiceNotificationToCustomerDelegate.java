package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.notification;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service.DeliveryRequestService;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.model.NotificationParameter;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.service.DeliveryPushNotificationService;

@Service
public class SendInvoiceNotificationToCustomerDelegate implements JavaDelegate {

	@Autowired
	private DeliveryPushNotificationService notificationService;

	@Autowired
	private DeliveryRequestService deliveryRequestService;

	@Autowired
	private RequestHistoryJpaRepository requestHistoryJpaRepository;
	
	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> SendInvoiceNotificationToCustomerDelegate step");
		Long packageId = (Long) execution.getVariable("packageId");
		Long receipentId = deliveryRequestService.getReceipentForPackage(packageId);
		Long requestId=requestHistoryJpaRepository.getPackageDeliveryId(packageId);

		if (receipentId != null) {
			NotificationParameter notificationParameter = new NotificationParameter();
			notificationParameter.setPackageId(packageId);
			notificationParameter.setCustomerId(receipentId);
			notificationParameter.setRequestId(requestId);
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> NotificationParameter >>>>>>>>>> "+notificationParameter.toString());
			notificationService.sendInvoiceToCustomer(notificationParameter);
		}
	}

}