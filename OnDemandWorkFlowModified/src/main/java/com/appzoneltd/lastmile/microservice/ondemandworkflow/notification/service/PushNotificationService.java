package com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.OnDemandWorkFlowProducer;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.model.GenericNotifierModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.model.NotificationLabel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.model.NotificationModel;
import com.appzoneltd.lastmile.microservice.ondemandworkflow.utility.MyPrinter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class PushNotificationService {

	@Autowired
	private OnDemandWorkFlowProducer workFlowProducer;

	@Autowired
	private ObjectMapper mapper;

	public void generatePushNotification(GenericNotifierModel genericNotifierModule) {
		/// Generate the Notification Model
		NotificationModel notificationModel = new NotificationModel();
		notificationModel.setPackageId(genericNotifierModule.getPackageId());
		notificationModel.setRecipientType(genericNotifierModule.getRecipientType());
		notificationModel.setUserIds(genericNotifierModule.getReceiverIds());
		/// Filling Data
		Map<String, Object> data = new HashMap<String, Object>();
		data.put(NotificationLabel.NOTIFICATION_TITLE.getLabel(), genericNotifierModule.getNotificationTitle());
		data.put(NotificationLabel.NOTIFICATION_BODY.getLabel(), genericNotifierModule.getNotificationBody());
		data.put(NotificationLabel.NOTIFICATION_ITEM_TITLE.getLabel(),
				genericNotifierModule.getNotificationItemTitle());
		data.put(NotificationLabel.NOTIFICATION_ITEM_BODY.getLabel(),
				genericNotifierModule.getNotificationItemBody());
		data.put(NotificationLabel.TYPE.getLabel(), genericNotifierModule.getType());
		data.put(NotificationLabel.TIME.getLabel(), new Date().getTime());
		data.put(NotificationLabel.PAYLOAD.getLabel(), genericNotifierModule.getPayload());
		// SETTING DATA
		notificationModel.setData(data);
		MyPrinter.print("NOTIFICATION", notificationModel.toString());
		sendPushNotification(notificationModel);
	}

	private void sendPushNotification(NotificationModel notificationModel) {
		try {
			workFlowProducer.sendMessage("push-notification",
					mapper.writerWithDefaultPrettyPrinter().writeValueAsString(notificationModel));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
	}

}
