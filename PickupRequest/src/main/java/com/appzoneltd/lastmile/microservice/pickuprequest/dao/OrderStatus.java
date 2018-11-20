package com.appzoneltd.lastmile.microservice.pickuprequest.dao;

public enum OrderStatus {
	NEW("NEW"), AWAITING_PICKUP("AWAITING_PICKUP"), WAITING_FOR_CUSTOMER_ALTERNATIVE(
			"WAITING_FOR_CUSTOMER_ALTERNATIVE"), ACTION_NEEDED("ACTION_NEEDED"), IN_PICKUP_VERIFICATION(
					"IN_PICKUP_VERIFICATION"), PICKEDUP("PICKEDUP"), CANCELED("CANCELED"), ASSIGNED("ASSIGNED"), SCHEDULED_FOR_PICKUP("SCHEDULED_FOR_PICKUP"),RE_SCHEDULED_FOR_PICKUP("RE_SCHEDULED_FOR_PICKUP");

	private final String orderStatus;

	private OrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

}
