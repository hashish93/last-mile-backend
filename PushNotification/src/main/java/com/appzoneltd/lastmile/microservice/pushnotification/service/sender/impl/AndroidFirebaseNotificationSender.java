package com.appzoneltd.lastmile.microservice.pushnotification.service.sender.impl;

import java.util.Map;

import com.appzoneltd.lastmile.microservice.pushnotification.firebase.Message;
import com.appzoneltd.lastmile.microservice.pushnotification.service.sender.BasicFirebaseNotificationSender;
import com.appzoneltd.lastmile.microservice.pushnotification.service.sender.exception.NotificationSendingException;


public class AndroidFirebaseNotificationSender extends BasicFirebaseNotificationSender {

	public AndroidFirebaseNotificationSender(String FIREBASE_API_KEY, int retiresCount) {
		super(FIREBASE_API_KEY, retiresCount);
	}

	@Override
	protected Message buildMessagePayload(Map<String, Object> data) {
		
		Message payload = new Message.Builder().setData(data).build();
		
		return payload;
	}


}
