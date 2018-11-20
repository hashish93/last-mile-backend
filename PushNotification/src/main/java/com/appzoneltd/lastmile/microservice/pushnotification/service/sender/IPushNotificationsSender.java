package com.appzoneltd.lastmile.microservice.pushnotification.service.sender;

import java.util.Map;

import com.appzoneltd.lastmile.microservice.pushnotification.service.sender.exception.NotificationSendingException;

public interface IPushNotificationsSender {

	public void addRegistrationId(String regId);
	
	public Object sendNotifications(Map<String, Object> data) throws NotificationSendingException;

	public int getRegIdsCount();
}
