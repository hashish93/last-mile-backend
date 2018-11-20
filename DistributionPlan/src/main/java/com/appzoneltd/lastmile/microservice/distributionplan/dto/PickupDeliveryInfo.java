package com.appzoneltd.lastmile.microservice.distributionplan.dto;

public class PickupDeliveryInfo extends JobOrderDto {
	
	private String formattedAddress ;

	public String getFormattedAddress() {
		return formattedAddress;
	}

	public void setFormattedAddress(String formattedAddress) {
		this.formattedAddress = formattedAddress;
	}

}
