package com.appzoneltd.lastmile.microservice.delivery.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class DeliveryRequest implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 6745927753055415900L;
	private Long requestId;
    private String recipientAddress;
    private String senderAddress;
    private Date deliveryDate;
    private String deliveryTimeFrom;
    private String deliveryTimeTo;
    private String packageType;
    private String requestStatus;

    private Long hubId;
    private String hubName;
   
    private Boolean inTodaysPlan;
}
