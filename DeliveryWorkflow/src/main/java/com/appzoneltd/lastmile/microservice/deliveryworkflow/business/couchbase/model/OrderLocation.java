package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.couchbase.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class OrderLocation implements Serializable {

	private static final long serialVersionUID = -6043719507258236587L;
	private String latitude;
	private String longitude;

}
