package com.appzoneltd.lastmile.microservice.pushnotification.service.sender.impl;

import java.util.Map;

import com.appzoneltd.lastmile.microservice.pushnotification.firebase.Message;
import com.appzoneltd.lastmile.microservice.pushnotification.service.sender.BasicFirebaseNotificationSender;


public class IOSFirebaseNotificationSender extends BasicFirebaseNotificationSender {

	public IOSFirebaseNotificationSender(String FIREBASE_API_KEY, int retiresCount) {
		super(FIREBASE_API_KEY, retiresCount);
	}

	@Override
	protected Message buildMessagePayload(Map<String, Object> data) {

		Message payload = new Message.Builder().setData(data)
				.addNotificationTitle(data.get("notification_item_title"))
				.addNotificationBody(data.get("notification_item_body"))
				.build();

		return payload;
	}


}
