/**
 * Sep 26, 201612:40:36 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.vehicleregistration.dao;

import java.io.Serializable;

import lombok.Data;

/**
 * @author alaa.nabil
 *
 */
@Data
public class JobOrder implements Serializable {

	private static final long serialVersionUID = 5640983377604419424L;
	private Long jobOrderId;
	private String orderType;
	private String customerName;
	private String customerPhone;
	private Location originalLocation;
	private Location actualLocation;
	private String recepientName;
	private String recepientPhone;
	private String orderStatus;
	private Boolean currentOrder;
	private int priority;
	private Integer actualWeight;
	private String activeVehicleAddress;

}
