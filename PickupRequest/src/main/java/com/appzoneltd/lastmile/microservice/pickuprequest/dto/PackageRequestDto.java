package com.appzoneltd.lastmile.microservice.pickuprequest.dto;

import lombok.Data;

import java.io.Serializable;


@Data
public class PackageRequestDto implements Serializable{

	private Long packageId;
	private Long requestId ;
	private String packageTrackingNumber;
	private String requestType;
	private String requestStatus;
	private String status;
	private String packageType;
	private String requesterName;
	private String recipientName;
	private String senderPhone;
	private String recipientPhone;
}
