package com.appzoneltd.lastmile.microservice.pushnotification.service;

import java.util.Map;

import com.appzoneltd.lastmile.microservice.pushnotification.service.sender.exception.NotificationSendingException;

public interface NotificationService {

	public void sendNotification(Long[] ids, Map<String, Object> data, String recipientType) throws NotificationSendingException;

}
