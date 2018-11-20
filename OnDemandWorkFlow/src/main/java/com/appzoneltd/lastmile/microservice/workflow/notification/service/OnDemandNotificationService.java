package com.appzoneltd.lastmile.microservice.workflow.notification.service;

import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowNearByVehicles;
import com.appzoneltd.lastmile.microservice.workflow.model.WorkflowNotificationModel;
import com.appzoneltd.lastmile.microservice.workflow.notification.model.CustomerNotificationModel;
import com.appzoneltd.lastmile.microservice.workflow.notification.model.GenericNotifierModel;
import com.appzoneltd.lastmile.microservice.workflow.notification.model.NotificationRecipientType;
import com.appzoneltd.lastmile.microservice.workflow.notification.model.OnDemandPickupRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.util.Date;

@Service
public class OnDemandNotificationService {

    @Autowired
    private PushNotificationService pushNotificationService;

    @Autowired
    private ObjectMapper mapper;

    public void sendGoExtraNotification(WorkflowNearByVehicles workflowNearByVehicles) throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(workflowNearByVehicles.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.DRIVER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Go Extra");
        genericNotifierModule.setNotificationItemTitle("Go Extra");
        genericNotifierModule.setNotificationItemBody("New Pickup Request Go Extra");
        genericNotifierModule.setType("OnDemandPickupRequestAssigned");
        // ADDING RECEIPIENTS
        Long[] receiverIds = null;
        if (workflowNearByVehicles.getVehicles().size() > 0) {
            int index = 0;
            receiverIds = new Long[workflowNearByVehicles.getVehicles().size()];
            for (Long driverId : workflowNearByVehicles.getVehicles()) {
                receiverIds[index] = driverId;
                index++;
            } // end for Loop
        } // end if condition
        genericNotifierModule.setReceiverIds(receiverIds);
        // GENERATING PAYLOAD
        OnDemandPickupRequest onDemandPickupRequest = new OnDemandPickupRequest();
        onDemandPickupRequest.setPackageId(workflowNearByVehicles.getPackageId());
        onDemandPickupRequest.setRequestId(workflowNearByVehicles.getRequestId());
        onDemandPickupRequest.setAddress(workflowNearByVehicles.getRequestAddress());
        onDemandPickupRequest.setWeight(workflowNearByVehicles.getRequestWeight());
        onDemandPickupRequest.setPackageType(workflowNearByVehicles.getPackageType());
        // PAYLOAD STRING
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(onDemandPickupRequest);
        genericNotifierModule.setPayload(notificationPayload);
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);
    }

    public void sendNoVehicleFoundNotification(WorkflowNotificationModel workflowNotificationModel)
            throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(workflowNotificationModel.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Oops!");
        genericNotifierModule.setNotificationItemTitle("Oops!");
        genericNotifierModule.setNotificationItemBody("Sorry We cannot serve you at this moment, Please Select another option.");
        genericNotifierModule.setType("busy");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = workflowNotificationModel.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);
        // GENERATING PAYLOAD
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(workflowNotificationModel.getPackageId());
        customerNotificationModel.setRequestId(workflowNotificationModel.getRequestId());
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(customerNotificationModel);
        genericNotifierModule.setPayload(notificationPayload);
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);
    }// end sendNoVehicleFoundNotification

    public void sendDriverAssignedNotification(WorkflowNotificationModel workflowNotificationModel)
            throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(workflowNotificationModel.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Driver Found");
        genericNotifierModule.setNotificationItemTitle("Driver Found");
        genericNotifierModule.setNotificationItemBody("Your driver has been found and will be on his way shortly.");
        genericNotifierModule.setType("assigned");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = workflowNotificationModel.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);
        // GENERATING PAYLOAD
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(workflowNotificationModel.getPackageId());
        customerNotificationModel.setRequestId(workflowNotificationModel.getRequestId());
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(customerNotificationModel);
        genericNotifierModule.setPayload(notificationPayload);
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);

    }// end sendNoVehicleFoundNotification

    public void sendDriverArrivedNotification(WorkflowNotificationModel workflowNotificationModel) throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(workflowNotificationModel.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Driver Arrived");
        genericNotifierModule.setNotificationItemTitle("Driver Arrived");
        genericNotifierModule.setNotificationItemBody("The Driver arrived");
        genericNotifierModule.setType("arrived");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = workflowNotificationModel.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);
        // GENERATING PAYLOAD
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(workflowNotificationModel.getPackageId());
        customerNotificationModel.setRequestId(workflowNotificationModel.getRequestId());
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(customerNotificationModel);
        genericNotifierModule.setPayload(notificationPayload);
        
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);

    }// end sendNoVehicleFoundNotification

    public void sendRequestCancelledNotification(WorkflowNotificationModel workflowNotificationModel) throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(workflowNotificationModel.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Request Cancelled");
        genericNotifierModule.setNotificationItemTitle("Request Cancelled");
        genericNotifierModule.setNotificationItemBody("The Request has been Cancelled");
        genericNotifierModule.setType("cancelled");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = workflowNotificationModel.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);
        // GENERATING PAYLOAD
     // GENERATING PAYLOAD
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(workflowNotificationModel.getPackageId());
        customerNotificationModel.setRequestId(workflowNotificationModel.getRequestId());
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(customerNotificationModel);
        genericNotifierModule.setPayload(notificationPayload);
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);

    }// end sendNoVehicleFoundNotification

    public void sendTrackingNumberNotification(WorkflowNotificationModel workflowNotificationModel)
            throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(workflowNotificationModel.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Tracking Number");
        genericNotifierModule.setNotificationItemTitle("Tracking Number");
        genericNotifierModule.setNotificationItemBody("Tracking Number");
        genericNotifierModule.setType("trackingNumber");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = workflowNotificationModel.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);
        // GENERATING PAYLOAD
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(workflowNotificationModel.getPackageId());
        customerNotificationModel.setRequestId(workflowNotificationModel.getRequestId());
        customerNotificationModel.setValue(workflowNotificationModel.getTrackingNumber());
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(customerNotificationModel);
        genericNotifierModule.setPayload(notificationPayload);
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);
    }// end sendNoVehicleFoundNotification

    public void sendRatingServiceNotification(WorkflowNotificationModel workflowNotificationModel)
            throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(workflowNotificationModel.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Rate the service");
        genericNotifierModule.setNotificationItemTitle("Rate the service");
        genericNotifierModule.setNotificationItemBody("please click to rate the service");
        genericNotifierModule.setType("rateDriver");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = workflowNotificationModel.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);
        // GENERATING PAYLOAD
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(workflowNotificationModel.getPackageId());
        customerNotificationModel.setDriverId(workflowNotificationModel.getDriverId());
        customerNotificationModel.setRequestId(workflowNotificationModel.getRequestId());
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(customerNotificationModel);
        genericNotifierModule.setPayload(notificationPayload);
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);

    }// end sendNoVehicleFoundNotification

    public void sendOutOfRangeNotification(WorkflowNotificationModel workflowNotificationModel)
            throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(workflowNotificationModel.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("No Coverage");
        genericNotifierModule.setNotificationItemTitle("No Coverage");
        genericNotifierModule.setNotificationItemBody("Sorry, This area is out of our coverage.");
        genericNotifierModule.setType("outOfRange");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = workflowNotificationModel.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);
        // GENERATING PAYLOAD
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(workflowNotificationModel.getPackageId());
        customerNotificationModel.setRequestId(workflowNotificationModel.getRequestId());
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(customerNotificationModel);
        genericNotifierModule.setPayload(notificationPayload);
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);
    }// end sendNoVehicleFoundNotification

    public void sendCustomerAlternativesNotification(WorkflowNotificationModel workflowNotificationModel)
            throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(workflowNotificationModel.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Oops!");
        genericNotifierModule.setNotificationItemTitle("Oops!");
        genericNotifierModule.setNotificationItemBody("Sorry We cannot serve you at this moment, Please Select another option.");
        genericNotifierModule.setType("busy");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = workflowNotificationModel.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);
        // GENERATING PAYLOAD
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(workflowNotificationModel.getPackageId());
        customerNotificationModel.setRequestId(workflowNotificationModel.getRequestId());
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(customerNotificationModel);
        genericNotifierModule.setPayload(notificationPayload);
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);
    }

    public void sendScheduleOndemandPickupNotificationToCustomer(WorkflowNotificationModel workflowNotificationModel, Date pickupDate, String timeFrom, String timeTo)
            throws JsonProcessingException {

        GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
        genericNotifierModule.setPackageId(workflowNotificationModel.getPackageId());
        genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
        genericNotifierModule.setNotificationTitle("LastMile");
        genericNotifierModule.setNotificationBody("Schedule Ondemand Request");
        genericNotifierModule.setNotificationItemTitle("Schedule On-demand Pickup Request");
        genericNotifierModule.setNotificationItemBody("Your request was scheduled on " + DateFormat.getDateInstance(DateFormat.FULL).format(pickupDate) + " within " + timeFrom + " to " + timeTo);
        genericNotifierModule.setType("SCHEDULED");
        // ADDING RECEIPIENTS
        Long[] receiverIds = new Long[1];
        receiverIds[0] = workflowNotificationModel.getCustomerId();
        genericNotifierModule.setReceiverIds(receiverIds);
        // GENERATING PAYLOAD
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(workflowNotificationModel.getPackageId());
        customerNotificationModel.setRequestId(workflowNotificationModel.getRequestId());
        String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(customerNotificationModel);
        genericNotifierModule.setPayload(notificationPayload);
        // SEND NOTIFICATION
        pushNotificationService.generatePushNotification(genericNotifierModule);

    }

}