package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.notification;

import java.util.Date;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.DeliveryRequestEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service.DeliveryRequestService;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.model.NotificationParameter;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.service.DeliveryPushNotificationService;

@Service
public class SendDeliveryDateNotificationDelegate implements JavaDelegate {

	@Autowired
	private DeliveryPushNotificationService notificationService;

	@Autowired
	private DeliveryRequestService deliveryRequestService;

	@Override
	public void execute(DelegateExecution execution) throws Exception {

		Long packageId = (Long) execution.getVariable("packageId");
		DeliveryRequestEntity deliveryRequestEntity=deliveryRequestService.getDeliveryRequestForPackage(packageId);
		Long receipentId = deliveryRequestService.getReceipentForPackage(packageId);
		Date deliveryDate=deliveryRequestService.getDeliveryDate(packageId);
		
		NotificationParameter notificationParameter = new NotificationParameter();
		notificationParameter.setPackageId(packageId);
		notificationParameter.setRequestId(deliveryRequestEntity.getDeliveryRequestId());
		notificationParameter.setCustomerId(receipentId);
		notificationParameter.setDeliveryDate(deliveryDate);
		notificationParameter.setDeliveryTimeFrom(deliveryRequestEntity.getTimeFrom());
		notificationParameter.setDeliveryTimeFrom(deliveryRequestEntity.getTimeTo());
		
		notificationService.sendDeliveryDateNotification(notificationParameter);

	}

}