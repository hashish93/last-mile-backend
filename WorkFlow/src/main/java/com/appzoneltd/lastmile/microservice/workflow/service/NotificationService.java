package com.appzoneltd.lastmile.microservice.workflow.service;

import com.appzoneltd.lastmile.microservice.workflow.kafka.WorkFlowProducer;
import com.appzoneltd.lastmile.microservice.workflow.kafka.models.WorkflowNearByVehicles;
import com.appzoneltd.lastmile.microservice.workflow.model.CustomerNotificationModel;
import com.appzoneltd.lastmile.microservice.workflow.model.NotificationModel;
import com.appzoneltd.lastmile.microservice.workflow.model.OnDemandPickupRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class NotificationService {

    @Autowired
    private WorkFlowProducer workFlowProducer;

    @Autowired
    private ObjectMapper mapper;

    ////////////////////////////////////////////////////////////////////////
    /*
     *   Send Go Extra Push Notification
	 */
    ////////////////////////////////////////////////////////////////////////
    public void sendGoExtraNotification(WorkflowNearByVehicles workflowNearByVehicles) {
        /// Generate the Notification Model
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setPackageId(workflowNearByVehicles.getPackageId());
        notificationModel.setRecipientType("DRIVER");
        ////// Filling the drivers
        Long[] driversIDs = null;
        // Filling Lsit of Drivers to Array
        if (workflowNearByVehicles.getVehicles().size() > 0) {
            int index = 0;
            driversIDs = new Long[workflowNearByVehicles.getVehicles().size()];
            for (Long driverId : workflowNearByVehicles.getVehicles()) {
                driversIDs[index] = driverId;
                index++;
            }//end for Loop
        }//end if condition
        /// Setting the driverIds to Send
        notificationModel.setUserIds(driversIDs);
        // Putting the Map
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("notification_title", "Go Extra");
        data.put("notification_body", "New Pickup Request Go Extra");
        data.put("notification_item_title", "Go Extra");
        data.put("notification_item_body", "New Pickup Request Go Extra");
        data.put("type", "OnDemandPickupRequestAssigned");
        data.put("time", new Date().getTime());
        /// OnDemandRequest
        OnDemandPickupRequest onDemandPickupRequest = new OnDemandPickupRequest();
        onDemandPickupRequest.setPackageId(workflowNearByVehicles.getPackageId());
        onDemandPickupRequest.setRequestId(workflowNearByVehicles.getRequestId());
        onDemandPickupRequest.setAddress(workflowNearByVehicles.getRequestAddress());
        onDemandPickupRequest.setWeight(workflowNearByVehicles.getRequestWeight());
        /// Setting Payload
        try {
            data.put("payload", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(onDemandPickupRequest));
        } catch (JsonProcessingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // Attach Data Section
        notificationModel.setData(data);
        // Produce Message to Kafka
        try {
            workFlowProducer.sendMessage("push-notification", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notificationModel));
            System.out.println(">>> TO SEND NOTIFICATION : " + notificationModel.toString());
            System.out.println(">>> Notification Pushed Successfully ");
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//end try_catch Block

    }

    public void sendNoVehicleFoundNotification(Long customerId, Long packageId) {
        /// Generate the Notification Model
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setPackageId(packageId);
        notificationModel.setRecipientType("CUSTOMER");
        ////// Filling the drivers
        Long[] customers = new Long[1];
        customers[0] = customerId;
        /// Setting the driverIds to Send
        notificationModel.setUserIds(customers);
        // Putting the Map
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("notification_title", "LastMile");
        data.put("notification_body", "No Vehicles");
        data.put("notification_item_title", "No Vehicles");
        data.put("notification_item_body", "Sorry! We cannot serve you at the moment. Please try again later");
        data.put("type", "busy");
        data.put("time", new Date().getTime());
        /// OnDemandRequest
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(packageId);
        /// Setting Payload
        try {
            data.put("payload", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerNotificationModel));
        } catch (JsonProcessingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // Attach Data Section
        notificationModel.setData(data);
        // Produce Message to Kafka
        try {
            workFlowProducer.sendMessage("push-notification", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notificationModel));
            System.out.println(">>> TO SEND NOTIFICATION : " + notificationModel.toString());
            System.out.println(">>> Notification Pushed Successfully ");
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//end try_catch Block
    }//end sendNoVehicleFoundNotification

    public void sendDriverAssignedNotification(Long customerId, Long packageId) {
        /// Generate the Notification Model
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setPackageId(packageId);
        notificationModel.setRecipientType("CUSTOMER");
        ////// Filling the drivers
        Long[] customers = new Long[1];
        customers[0] = customerId;
        /// Setting the driverIds to Send
        notificationModel.setUserIds(customers);
        // Putting the Map
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("notification_title", "LastMile");
        data.put("notification_body", "Driver Assigned");
        data.put("notification_item_title", "Driver Assigned");
        data.put("notification_item_body", "There is a driver assigned for request");
        data.put("type", "assigned");
        data.put("time", new Date().getTime());
        data.put("payload", null);
        // Attach Data Section
        notificationModel.setData(data);
        // Produce Message to Kafka
        try {
            workFlowProducer.sendMessage("push-notification", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notificationModel));
            System.out.println(">>> TO SEND NOTIFICATION : " + notificationModel.toString());
            System.out.println(">>> Notification Pushed Successfully ");
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//end try_catch Block
    }//end sendNoVehicleFoundNotification

    public void sendDriverArrivedNotification(Long customerId, Long packageId) {
        /// Generate the Notification Model
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setPackageId(packageId);
        notificationModel.setRecipientType("CUSTOMER");
        ////// Filling the drivers
        Long[] customers = new Long[1];
        customers[0] = customerId;
        /// Setting the driverIds to Send
        notificationModel.setUserIds(customers);
        // Putting the Map
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("notification_title", "LastMile");
        data.put("notification_body", "Driver Arrived");
        data.put("notification_item_title", "Driver Arrived");
        data.put("notification_item_body", "The Driver arrived");
        data.put("type", "arrived");
        data.put("time", new Date().getTime());
        data.put("payload", null);
        // Attach Data Section
        notificationModel.setData(data);
        // Produce Message to Kafka
        try {
            workFlowProducer.sendMessage("push-notification", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notificationModel));
            System.out.println(">>> TO SEND NOTIFICATION : " + notificationModel.toString());
            System.out.println(">>> Notification Pushed Successfully ");
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//end try_catch Block
    }//end sendNoVehicleFoundNotification

    public void sendRequestCancelledNotification(Long customerId, Long packageId) {
        /// Generate the Notification Model
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setPackageId(packageId);
        notificationModel.setRecipientType("CUSTOMER");
        ////// Filling the drivers
        Long[] customers = new Long[1];
        customers[0] = customerId;
        /// Setting the driverIds to Send
        notificationModel.setUserIds(customers);
        // Putting the Map
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("notification_title", "LastMile");
        data.put("notification_body", "Request Cancelled");
        data.put("notification_item_title", "Request Cancelled");
        data.put("notification_item_body", "The Request Cancelled");
        data.put("type", "cancelled");
        data.put("time", new Date().getTime());
        data.put("payload", null);
        // Attach Data Section
        notificationModel.setData(data);
        // Produce Message to Kafka
        try {
            workFlowProducer.sendMessage("push-notification", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notificationModel));
            System.out.println(">>> TO SEND NOTIFICATION : " + notificationModel.toString());
            System.out.println(">>> Notification Pushed Successfully ");
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//end try_catch Block
    }//end sendNoVehicleFoundNotification


    public void sendTrackingNumberNotification(Long customerId, Long packageId, String trackingNumber) {
        /// Generate the Notification Model
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setPackageId(packageId);
        notificationModel.setRecipientType("CUSTOMER");
        ////// Filling the drivers
        Long[] customers = new Long[1];
        customers[0] = customerId;
        /// Setting the driverIds to Send
        notificationModel.setUserIds(customers);
        // Putting the Map
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("notification_title", "LastMile");
        data.put("notification_body", "Tracking Number");
        data.put("notification_item_title", "Tracking Number");
        data.put("notification_item_body", "Tracking Number");
        data.put("type", "trackingNumber");
        data.put("time", new Date().getTime());
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(packageId);
        customerNotificationModel.setValue(trackingNumber);

        try {
            data.put("payload", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerNotificationModel));
        } catch (JsonProcessingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // Attach Data Section
        notificationModel.setData(data);
        // Produce Message to Kafka
        try {
            workFlowProducer.sendMessage("push-notification", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notificationModel));
            System.out.println(">>> TO SEND NOTIFICATION : " + notificationModel.toString());
            System.out.println(">>> Notification Pushed Successfully ");
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//end try_catch Block
    }//end sendNoVehicleFoundNotification

    public void sendRatingServiceNotification(Long packageId, Long customerId, Long driverId) {
        /// Generate the Notification Model
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setPackageId(packageId);
        notificationModel.setRecipientType("CUSTOMER");
        ////// Filling the drivers
        Long[] customers = new Long[1];
        customers[0] = customerId;
        /// Setting the driverIds to Send
        notificationModel.setUserIds(customers);
        // Putting the Map
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("notification_title", "LastMile");
        data.put("notification_body", "Rate the service");
        data.put("notification_item_title", "Rate the service");
        data.put("notification_item_body", "please click to rate the service");
        data.put("type", "rating");
        data.put("time", new Date().getTime());
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(packageId);
        customerNotificationModel.setDriverId(driverId);

        try {
            data.put("payload", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerNotificationModel));
        } catch (JsonProcessingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // Attach Data Section
        notificationModel.setData(data);
        // Produce Message to Kafka
        try {
            workFlowProducer.sendMessage("push-notification", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notificationModel));
            System.out.println(">>> TO SEND NOTIFICATION : " + notificationModel.toString());
            System.out.println(">>> Notification Pushed Successfully ");
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//end try_catch Block
    }//end sendNoVehicleFoundNotification


    public void sendOutOfRangeNotification(Long packageId, Long customerId) {
        /// Generate the Notification Model
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setPackageId(packageId);
        notificationModel.setRecipientType("CUSTOMER");
        ////// Filling the drivers
        Long[] customers = new Long[1];
        customers[0] = customerId;
        /// Setting the driverIds to Send
        notificationModel.setUserIds(customers);
        // Putting the Map
        Map<String, Object> data = new HashMap<String, Object>();
        data.put("notification_title", "LastMile");
        data.put("notification_body", "Out Of Range");
        data.put("notification_item_title", "Out Of Range");
        data.put("notification_item_body", "Sorry, You are not in our Serving Area");
        data.put("type", "busy");
        data.put("time", new Date().getTime());
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(packageId);

        try {
            data.put("payload", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerNotificationModel));
        } catch (JsonProcessingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        // Attach Data Section
        notificationModel.setData(data);
        // Produce Message to Kafka
        try {
            workFlowProducer.sendMessage("push-notification", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notificationModel));
            System.out.println(">>> TO SEND NOTIFICATION : " + notificationModel.toString());
            System.out.println(">>> Notification Pushed Successfully ");
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//end try_catch Block
    }//end sendNoVehicleFoundNotification

    public void sendCustomerAlternativesNotification(Long customerId, Long packageId) {
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setPackageId(packageId);
        notificationModel.setRecipientType("CUSTOMER");
        Long[] customers = new Long[1];
        customers[0] = customerId;
        notificationModel.setUserIds(customers);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("notification_title", "LastMile");
        data.put("notification_body", "Customer Alternatives");
        data.put("notification_item_title", "Action");
        data.put("notification_item_body", "Please, select another option");
        data.put("type", "busy");
        data.put("time", new Date().getTime());
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(packageId);
        try {
            data.put("payload", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerNotificationModel));
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        notificationModel.setData(data);
        try {
            workFlowProducer.sendMessage("push-notification", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notificationModel));
            System.out.println(">>> TO SEND NOTIFICATION : " + notificationModel.toString());
            System.out.println(">>> Notification Pushed Successfully ");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void sendScheduleOndemandPickupNotificationToCustomer(Long customerId, Long packageId, Date pickupDate, String timeFrom, String timeTo) {
        NotificationModel notificationModel = new NotificationModel();
        notificationModel.setPackageId(packageId);
        notificationModel.setRecipientType("CUSTOMER");
        Long[] customers = new Long[1];
        customers[0] = customerId;
        notificationModel.setUserIds(customers);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("notification_title", "LastMile");
        data.put("notification_body", "Schedule Ondemand Request");
        data.put("notification_item_title", "Schedule On-demand Pickup Request");
        data.put("notification_item_body", "Your request was scheduled on " + pickupDate.toString() + " within " + timeFrom + " to " + timeTo);
        data.put("type", "SCHEDULED");
        data.put("time", new Date().getTime());
        CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
        customerNotificationModel.setPackageId(packageId);
        try {
            data.put("payload", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(customerNotificationModel));
        } catch (JsonProcessingException e1) {
            e1.printStackTrace();
        }
        notificationModel.setData(data);
        try {
            workFlowProducer.sendMessage("push-notification", mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notificationModel));
            System.out.println(">>> TO SEND NOTIFICATION : " + notificationModel.toString());
            System.out.println(">>> Notification Pushed Successfully ");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}