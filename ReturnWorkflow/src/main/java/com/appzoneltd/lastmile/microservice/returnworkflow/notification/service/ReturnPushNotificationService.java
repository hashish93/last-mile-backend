package com.appzoneltd.lastmile.microservice.returnworkflow.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.returnworkflow.business.service.ReturnRequestService;
import com.appzoneltd.lastmile.microservice.returnworkflow.notification.model.CustomerNotificationModel;
import com.appzoneltd.lastmile.microservice.returnworkflow.notification.model.DriverNotificationModel;
import com.appzoneltd.lastmile.microservice.returnworkflow.notification.model.GenericNotifierModel;
import com.appzoneltd.lastmile.microservice.returnworkflow.notification.model.NotificationParameter;
import com.appzoneltd.lastmile.microservice.returnworkflow.notification.model.NotificationRecipientType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ReturnPushNotificationService {

	@Autowired
	private PushNotificationService pushNotificationService;

	@Autowired
	private ReturnRequestService returnRequestService;

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
		customerNotificationModel.setReturnDate(notificationParameter.getReturnDate());
		// GENERATING PAYLOAD
		String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(customerNotificationModel);
		genericNotifierModule.setPayload(notificationPayload);
		// SEND NOTIFICATION
		pushNotificationService.generatePushNotification(genericNotifierModule);
	}
	
	public void sendOnHisWayNotification(NotificationParameter notificationParameter) throws JsonProcessingException {

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
		DriverNotificationModel driverNotificationModel = returnRequestService
				.getDriverDetails(notificationParameter.getPackageId(), notificationParameter.getDriverId());
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
		genericNotifierModule.setPayload(null);
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

	public void sendRateDriver(NotificationParameter notificationParameter) throws JsonProcessingException {

		GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
		genericNotifierModule.setPackageId(notificationParameter.getPackageId());
		genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
		genericNotifierModule.setNotificationTitle("LastMile");
		genericNotifierModule.setNotificationBody("Rate the service");
		genericNotifierModule.setNotificationItemTitle("Rate the service");
		genericNotifierModule.setNotificationItemBody("please click to rate the service");
		genericNotifierModule.setType("rating");
		// ADDING RECEIPIENTS
		Long[] receiverIds = new Long[1];
		receiverIds[0] = notificationParameter.getCustomerId();
		genericNotifierModule.setReceiverIds(receiverIds);

		CustomerNotificationModel customerNotificationModel = new CustomerNotificationModel();
		customerNotificationModel.setPackageId(notificationParameter.getPackageId());
		customerNotificationModel.setDriverId(notificationParameter.getDriverId());
		// GENERATING PAYLOAD
		String notificationPayload = mapper.writerWithDefaultPrettyPrinter()
				.writeValueAsString(customerNotificationModel);
		genericNotifierModule.setPayload(notificationPayload);
		// SEND NOTIFICATION
		pushNotificationService.generatePushNotification(genericNotifierModule);
	}

	public void sendRescheduledReturn(NotificationParameter notificationParameter) throws JsonProcessingException {

		GenericNotifierModel genericNotifierModule = new GenericNotifierModel();
		genericNotifierModule.setPackageId(notificationParameter.getPackageId());
		genericNotifierModule.setRecipientType(NotificationRecipientType.CUSTOMER.getName());
		genericNotifierModule.setNotificationTitle("LastMile");
		genericNotifierModule.setNotificationBody("Reschedule For Return");
		genericNotifierModule.setNotificationItemTitle("Reschedule For Return");
		genericNotifierModule.setNotificationItemBody("Your Rescheduled Date for Return is "+notificationParameter.getReturnDate());
		genericNotifierModule.setType("RESCHEDULED");
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