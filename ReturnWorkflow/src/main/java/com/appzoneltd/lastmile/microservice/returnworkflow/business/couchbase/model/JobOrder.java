/**
 * Sep 26, 201612:40:36 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.returnworkflow.business.couchbase.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class JobOrder implements Serializable {

	private static final long serialVersionUID = 5640983377604419424L;
	private Long jobOrderId;
	private String orderType;
	private String customerName;
	private String customerPhone;
	private OrderLocation originalLocation;
	private OrderLocation actualLocation;
	private String recepientName;
	private String recepientPhone;
	private String orderStatus;
	private int priority;
	private Integer actualWeight;
	private String activeVehicleAddress;

}
