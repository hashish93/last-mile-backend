package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.notification;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.dao.RequestHistoryJpaRepository;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.DeliveryRequestEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service.DeliveryRequestService;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.model.NotificationParameter;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.service.DeliveryPushNotificationService;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendDetailsAndVehicleETANotificationDelegate implements JavaDelegate {

    @Autowired
    private DeliveryPushNotificationService notificationService;

    @Autowired
    private DeliveryRequestService deliveryRequestService;

    @Autowired
    private RequestHistoryJpaRepository requestHistoryJpaRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {

        Long packageId = (Long) execution.getVariable("packageId");
        Long receipientId = deliveryRequestService.getReceipentForPackage(packageId);
        Long driverId = (Long) execution.getVariable("driverId");
        Long requestId = requestHistoryJpaRepository.getPackageDeliveryId(packageId);
        Long activeVehicleId = (Long) execution.getVariable("activeVehicleId");

        DeliveryRequestEntity deliveryRequestEntity = deliveryRequestService.getDeliveryRequestForPackage(packageId);

        if (receipientId != null) {
            NotificationParameter notificationParameter = new NotificationParameter();
            notificationParameter.setPackageId(packageId);
            notificationParameter.setCustomerId(receipientId);
            notificationParameter.setDriverId(driverId);
            notificationParameter.setRequestId(requestId);

            String mobileNumber = "";
            if (deliveryRequestEntity.getCountryCode() != null) {
                mobileNumber = deliveryRequestEntity.getCountryCode() + deliveryRequestEntity.getRecipientmobile();
            } else {
                mobileNumber = deliveryRequestEntity.getRecipientmobile();
            }
            String confirmationCode = deliveryRequestEntity.getConfirmationCode();

            notificationParameter.setConfirmationCode(confirmationCode);

            notificationService.sendOnHisWayNotification(notificationParameter, activeVehicleId);
        }
    }

}