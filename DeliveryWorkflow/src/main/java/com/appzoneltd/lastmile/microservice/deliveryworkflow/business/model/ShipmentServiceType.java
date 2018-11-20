package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.model;

public enum ShipmentServiceType {

	NEXT_DAY("Next Day"), TWO_THREE_DAYS("2-3 Days"), SEVEN_DAYS("7 Days");

	private final String name;

	private ShipmentServiceType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


}
