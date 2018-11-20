package com.appzoneltd.lastmile.microservice.ondemandworkflow.notification.model;

import java.io.Serializable;
import java.util.Arrays;
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
		// TODO Auto-generated constructor stub
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
		return "NotificationModel [userIds=" + Arrays.toString(userIds) + ", packageId=" + packageId
				+ ", recipientType=" + recipientType + ", data=" + data + "]";
	}

	
	
}
