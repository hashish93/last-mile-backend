package com.appzoneltd.lastmile.microservice.pushnotification.dao;

import java.io.Serializable;
import java.util.Map;

/**
 * @author alaa.nabil
 *
 */
public class NotificationModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -640480487708175544L;
	private Long[] userIds;
	private Long packageId;
	private String recipientType;
	private Map<String, Object> data;

	public NotificationModel() {
	}

	public Long[] getUserIds() {
		return userIds;
	}

	public void setUserIds(Long[] userIds) {
		this.userIds = userIds;
	}

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public String getRecipientType() {
		return recipientType;
	}

	public void setRecipientType(String recipientType) {
		this.recipientType = recipientType;
	}

	public Map<String, Object> getData() {
		return data;
	}

	public void setData(Map<String, Object> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TYPE:: " + this.recipientType);
		builder.append("\n");
		for (int i = 0; i < userIds.length; i++) {
			builder.append("USER ::");
			builder.append(userIds[i]);
			builder.append("\n");
		}
		builder.append("\n DATA ::");
		builder.append(data);
		return builder.toString();
	}

}
