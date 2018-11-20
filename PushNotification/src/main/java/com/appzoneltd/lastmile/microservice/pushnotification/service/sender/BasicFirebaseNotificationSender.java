package com.appzoneltd.lastmile.microservice.pushnotification.service.sender;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.appzoneltd.lastmile.microservice.pushnotification.firebase.FCMSender;
import com.appzoneltd.lastmile.microservice.pushnotification.firebase.Message;
import com.appzoneltd.lastmile.microservice.pushnotification.firebase.MulticastResult;
import com.appzoneltd.lastmile.microservice.pushnotification.service.sender.exception.NotificationSendingException;

public abstract class BasicFirebaseNotificationSender implements IPushNotificationsSender {

	protected List<String> firebaseRegIds;
	protected final FCMSender sender;
	private final int retiresCount;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	//	@Value("${firebase.server-key}")
	//    private String FIREBASE_API_KEY;

	public BasicFirebaseNotificationSender(String FIREBASE_API_KEY, final int retiresCount) {

		this.firebaseRegIds = new ArrayList<String>();

		this.sender = new FCMSender(FIREBASE_API_KEY);

		this.retiresCount = retiresCount ;
	}

	@Override
	public void addRegistrationId(String regId) {

		if (regId != null && !regId.trim().isEmpty()) {

			this.firebaseRegIds.add(regId);
		}
	}

	@Override
	public Object sendNotifications(Map<String, Object> data) 
			throws NotificationSendingException {

		System.out.println("inside "+this.getClass().getSimpleName()+" sendNotifications():::");
		if (firebaseRegIds.isEmpty()) {
			return null ;
		}
		//else
		Message payload = buildMessagePayload(data);
		logger.warn("MESSAGE WITH DATA AND NOTIFICATION PATLOAD :: " + payload.toString());
		
		MulticastResult result = null;
		try {
			System.out.println("will now call FCM.SEND() on: "+firebaseRegIds.size());
			result = sender.send(payload, firebaseRegIds, retiresCount);
			
			System.out.println("MultiCastResult:: "+result.toString());

		} catch (IOException e) {
			e.printStackTrace();
			throw new NotificationSendingException(e);
		} finally {
			System.out.println("will CLEAR all current reg ids");
			this.firebaseRegIds.clear();
			System.out.println("after clearing, size is::: "+firebaseRegIds.size());
		}
		return result;
	}

	@Override
	public int getRegIdsCount() {
		return this.firebaseRegIds.size();
	}

	protected abstract Message buildMessagePayload(Map<String, Object> data);


}
