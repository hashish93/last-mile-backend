package com.appzoneltd.lastmile.microservice.deliveryworkflow.kafka.models;

import lombok.Data;

@Data
public class DeliveryPackageStatus extends DeliveryBase{

	private String packageStatus;
	
}
