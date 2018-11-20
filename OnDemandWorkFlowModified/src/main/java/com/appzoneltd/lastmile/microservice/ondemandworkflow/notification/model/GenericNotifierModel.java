package com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.model;

import lombok.Data;

@Data
public class GenericNotifierModel {

	private Long packageId;
	private Long[] receiverIds; 
	private String recipientType;
	private String notificationTitle;
	private String notificationBody;
	private String notificationItemTitle;
	private String notificationItemBody;
	private String type;
	private String payload;

}
