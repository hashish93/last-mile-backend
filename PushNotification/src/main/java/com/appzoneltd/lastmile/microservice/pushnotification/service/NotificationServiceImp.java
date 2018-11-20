package com.appzoneltd.lastmile.microservice.pushnotification.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.pushnotification.dao.FirebaseTokenObject;
import com.appzoneltd.lastmile.microservice.pushnotification.dao.NotificationTokenRepository;
import com.appzoneltd.lastmile.microservice.pushnotification.service.sender.IPushNotificationsSender;
import com.appzoneltd.lastmile.microservice.pushnotification.service.sender.exception.NotificationSendingException;

@Service
public class NotificationServiceImp implements NotificationService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final NotificationTokenRepository repository;
	
    @Value("${notification.default-type}")
    private String DEFAULT_TYPE;

	private final Map<String, IPushNotificationsSender> notificationSendersMap;

	@Autowired
	public NotificationServiceImp(Map<String, IPushNotificationsSender> notificationSendersMap,
			NotificationTokenRepository repository) {

		this.notificationSendersMap = notificationSendersMap;
		this.repository = repository;
	}


	public void sendNotification(Long[] ids, Map<String, Object> data, String recipientType) 
			throws NotificationSendingException{

		List<FirebaseTokenObject> firebaseToken = null;

		for (Long id : ids) {

			if ("CUSTOMER".equalsIgnoreCase(recipientType)) {
				firebaseToken = repository.CustomerFirebaseTokens(id) ;

				if (firebaseToken != null) {
					System.out.println("size of the firebase tokens list::: "+firebaseToken.size());
					for (FirebaseTokenObject token : firebaseToken) {
						System.out.println("the firebase token to be added::: "+ token.toString());
						addTokonToSenders(token);
					}
				}

			} else if ("DRIVER".equalsIgnoreCase(recipientType)) {

				//assuming driver will always have android device for now!
				String driverRegId = repository.getDriverFirebaseToken(id) ;

				addTokonToSenders(new FirebaseTokenObject(driverRegId));
			}
		}

		sendNotifications(data);

	}

	private void sendNotifications(Map<String, Object> data) throws NotificationSendingException {
		Collection<IPushNotificationsSender> sendersCollection = this.notificationSendersMap.values();

		for (IPushNotificationsSender currentSender : sendersCollection) {

			currentSender.sendNotifications(data);
		}
	}

	private void addTokonToSenders(FirebaseTokenObject token) {

		IPushNotificationsSender currentSender = this.notificationSendersMap.get(token.getType());
		if (currentSender == null) {
			currentSender = getDefaultSender();
		}
		currentSender.addRegistrationId(token.getFirebaseToken());
		System.out.println("the size of the currentSender:: " 
		+ currentSender.getClass().getSimpleName()+ " ,,,, IS:: "+currentSender.getRegIdsCount());
	}


	private IPushNotificationsSender getDefaultSender() {
		System.out.println("will get the default sender ....");
		return this.notificationSendersMap.get(DEFAULT_TYPE);
	}

}
