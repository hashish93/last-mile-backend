package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.notification;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service.DeliveryRequestService;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.model.NotificationParameter;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.service.DeliveryPushNotificationService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendRateDriverNotificationDelegate implements JavaDelegate {

    @Autowired
    private DeliveryPushNotificationService notificationService;

    @Autowired
    private DeliveryRequestService deliveryRequestService;

    @Autowired
    private RequestHistoryJpaRepository requestHistoryJpaRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Long packageId = (Long) execution.getVariable("packageId");
        Long driverId = (Long) execution.getVariable("driverId");
        Long recipientId = deliveryRequestService.getReceipentForPackage(packageId);
        Long requesterId = deliveryRequestService.getRequesterForPackage(packageId);
        Long requestId = requestHistoryJpaRepository.getPackageDeliveryId(packageId);

        if (recipientId != null) {

            boolean paymentTypeAtDelivery = deliveryRequestService.isPaymentAtDelivery(packageId);

            NotificationParameter notificationParameter = new NotificationParameter();
            notificationParameter.setPackageId(packageId);
            notificationParameter.setDriverId(driverId);
            notificationParameter.setRequestId(requestId);
            if (paymentTypeAtDelivery) {
                notificationParameter.setCustomerId(recipientId);
                notificationService.sendRateDriverAndService(notificationParameter);
            } else {
                // SEND DRIVER Rate to Receipient
                notificationParameter.setCustomerId(recipientId);
                notificationService.sendRateDriver(notificationParameter);

                // SEND Service Rate To Sender
                notificationParameter.setCustomerId(requesterId);
                notificationService.sendRateService(notificationParameter);

            }

        }
    }

}