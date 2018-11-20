package com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.service;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.service.DeliveryRequestService;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.model.*;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.notification.service.component.DeliveryInvoiceGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeliveryPushNotificationService {

    @Autowired
    private PushNotificationService pushNotificationService;

    @Autowired
    private DeliveryRequestService deliveryRequestService;

    @Autowired
    private DeliveryInvoiceGenerator deliveryInvoiceGenerator;

    @Autowired
    private ObjectMapper mapper;

    public void sendDeliveryDateNotification(NotificationParameter notificationParameter) throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(notificationParameter.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Package Delivery Date");
        genericNotifierModule.setNotificationItemTitle("Package Delivery Date");
        genericNotifierModule.setNotificationItemBody("Package Delivery Date");
        genericNotifierModule.setType("deliveryDate");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = notificationParameter.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);
        // GENERATING PAYLOAD
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(notificationParameter.getPackageId());
        customerNotificationModel.setRequestId(notificationParameter.getRequestId());
        customerNotificationModel.setDeliveryDate(notificationParameter.getDeliveryDate());
        customerNotificationModel.setDeliveryTimeFrom(notificationParameter.getDeliveryTimeFrom());
        customerNotificationModel.setDeliveryTimeTo(notificationParameter.getDeliveryTimeTo());

        // GENERATING PAYLOAD
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(customerNotificationModel);
        genericNotifierModule.setPayload(notificationPayload);
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);
    }


    public void sendReScheduleDeliveryDateNotification(NotificationParameter notificationParameter) throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(notificationParameter.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Package ReSchedule Delivery Date");
        genericNotifierModule.setNotificationItemTitle("Package ReScheduleDelivery Date");
        genericNotifierModule.setNotificationItemBody("Package ReScheduleDelivery Date");
        genericNotifierModule.setType("rescheduleDeliveryDate");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = notificationParameter.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);
        // GENERATING PAYLOAD
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(notificationParameter.getPackageId());
        customerNotificationModel.setRequestId(notificationParameter.getRequestId());
        customerNotificationModel.setDeliveryDate(notificationParameter.getDeliveryDate());
        // GENERATING PAYLOAD
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(customerNotificationModel);
        genericNotifierModule.setPayload(notificationPayload);
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);
    }

    public void sendReScheduleDeliveryNotificationForDriver(NotificationParameter notificationParameter) throws JsonProcessingException {
    	System.out.println("sendReScheduleDeliveryNotificationForDriver step");
    	System.out.println("notificationParameter "+notificationParameter.toString());
        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(notificationParameter.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.DRIVER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody(" ReSchedule Delivery Package ");
        genericNotifierModule.setNotificationItemTitle(" ReSchedule Delivery Package");
        genericNotifierModule.setNotificationItemBody("Package has been rescheduled");
        genericNotifierModule.setType("rescheduleDeliveryPackage");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = notificationParameter.getDriverId();
        genericNotifierModule.setReceiverIds(receiverIds);
        genericNotifierModule.setPayload(null);
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);
    }

    public void sendOnHisWayNotification(NotificationParameter notificationParameter, Long activeVehicleId) throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(notificationParameter.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Driver on his way");
        genericNotifierModule.setNotificationItemTitle("Driver on his way");
        genericNotifierModule.setNotificationItemBody("Driver on his way, please wait him");
        genericNotifierModule.setType("onHisWay");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = notificationParameter.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);
        // GENERATING PAYLOAD
        DriverNotificationModel driverNotificationModel = deliveryRequestService
                .getDriverDetails(notificationParameter.getPackageId(), notificationParameter.getDriverId(), notificationParameter.getRequestId(), activeVehicleId);
        driverNotificationModel.setRequestType("DELIVERY");
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(driverNotificationModel);
        genericNotifierModule.setPayload(notificationPayload);
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);
    }

    public void sendDriverArrivedNotification(NotificationParameter notificationParameter)
            throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(notificationParameter.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Driver Arrived");
        genericNotifierModule.setNotificationItemTitle("Driver Arrived");
        genericNotifierModule.setNotificationItemBody("Driver Arrived");
        genericNotifierModule.setType("arrived");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = notificationParameter.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);

        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(notificationParameter.getPackageId());
        customerNotificationModel.setDriverId(notificationParameter.getDriverId());
        customerNotificationModel.setRequestId(notificationParameter.getRequestId());
        // GENERATING PAYLOAD
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(customerNotificationModel);
        genericNotifierModule.setPayload(notificationPayload);

        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);
    }

    public void sendInvoiceToCustomer(NotificationParameter notificationParameter) throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(notificationParameter.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Your Receipt");
        genericNotifierModule.setNotificationItemTitle("Request Receipt");
        genericNotifierModule.setNotificationItemBody("View You Receipt");
        genericNotifierModule.setType("INVOICE");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = notificationParameter.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);

        Invoice invoice = deliveryInvoiceGenerator.buildInvoiceModel(notificationParameter.getPackageId(), notificationParameter.getRequestId());

        // GENERATING PAYLOAD
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(invoice);
        genericNotifierModule.setPayload(notificationPayload);
        // SEND NOTIFICATION
        System.out.println("We are about to send notification with the invoice " + invoice.toString());
        pushNotificationService.generatePushNotification(genericNotifierModule);
    }

    public void sendRateDriver(NotificationParameter notificationParameter) throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(notificationParameter.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Rate the Driver");
        genericNotifierModule.setNotificationItemTitle("Rate the Driver");
        genericNotifierModule.setNotificationItemBody("please click to rate the Driver");
        genericNotifierModule.setType("rateDriver");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = notificationParameter.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);

        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(notificationParameter.getPackageId());
        customerNotificationModel.setDriverId(notificationParameter.getDriverId());
        customerNotificationModel.setRequestId(notificationParameter.getRequestId());
        // GENERATING PAYLOAD
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(customerNotificationModel);
        genericNotifierModule.setPayload(notificationPayload);
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);
    }

    public void sendRateService(NotificationParameter notificationParameter) throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(notificationParameter.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Rate the service");
        genericNotifierModule.setNotificationItemTitle("Rate the service");
        genericNotifierModule.setNotificationItemBody("please click to rate the service");
        genericNotifierModule.setType("rateService");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = notificationParameter.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);

        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(notificationParameter.getPackageId());
        customerNotificationModel.setDriverId(notificationParameter.getDriverId());
        customerNotificationModel.setRequestId(notificationParameter.getRequestId());
        // GENERATING PAYLOAD
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(customerNotificationModel);
        genericNotifierModule.setPayload(notificationPayload);
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);
    }

    public void sendRateDriverAndService(NotificationParameter notificationParameter) throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(notificationParameter.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Rate the Driver And Service");
        genericNotifierModule.setNotificationItemTitle("Rate the Driver And Service");
        genericNotifierModule.setNotificationItemBody("please click to rate the Driver And Service");
        genericNotifierModule.setType("rateDriverAndService");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = notificationParameter.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);

        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(notificationParameter.getPackageId());
        customerNotificationModel.setDriverId(notificationParameter.getDriverId());
        customerNotificationModel.setRequestId(notificationParameter.getRequestId());
        // GENERATING PAYLOAD
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(customerNotificationModel);
        genericNotifierModule.setPayload(notificationPayload);
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);
    }

}