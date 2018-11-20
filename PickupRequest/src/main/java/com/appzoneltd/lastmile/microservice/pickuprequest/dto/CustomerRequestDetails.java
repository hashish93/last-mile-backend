package com.appzoneltd.lastmile.microservice.pickuprequest.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by alaa.nabil on 4/3/2017.
 */
public class CustomerRequestDetails implements Serializable {

    private Long id;
    private Long packageId;
    private String paymentAt;
    private String pickupRequestType;
    private String paymentMethod;
    private String shipmentService;
    private String shipmentServiceType;
    private String pickupTime;
    private String pickupAddress;
    private String nickName;
    private String packageType;
    private String packageValue;
    private String packageWeight;
    private String whatInside;
    private String additionalServices;
    private String recipientName;
    private String recipientMobileNumber;
    private String recipientLocation;
    private String additionalNotes;
    private List<Long> imageIds;
    private Date date;
    private List<PackageTimeLineDto> timeLine;
    private String requestType;
    private String latitude ; 
    private String longitude;
    
    private Long senderId;
    private Long recipientId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getShipmentService() {
        return shipmentService;
    }

    public void setShipmentService(String shipmentService) {
        this.shipmentService = shipmentService;
    }

    public String getShipmentServiceType() {
        return shipmentServiceType;
    }

    public void setShipmentServiceType(String shipmentServiceType) {
        this.shipmentServiceType = shipmentServiceType;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime;
    }

    public String getPickupAddress() {
        return pickupAddress;
    }

    public void setPickupAddress(String pickupAddress) {
        this.pickupAddress = pickupAddress;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getPackageWeight() {
        return packageWeight;
    }

    public void setPackageWeight(String packageWeight) {
        this.packageWeight = packageWeight;
    }

    public String getWhatInside() {
        return whatInside;
    }

    public void setWhatInside(String whatInside) {
        this.whatInside = whatInside;
    }

    public String getAdditionalServices() {
        return additionalServices;
    }

    public void setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientMobileNumber() {
        return recipientMobileNumber;
    }

    public void setRecipientMobileNumber(String recipientMobileNumber) {
        this.recipientMobileNumber = recipientMobileNumber;
    }

    public String getRecipientLocation() {
        return recipientLocation;
    }

    public void setRecipientLocation(String recipientLocation) {
        this.recipientLocation = recipientLocation;
    }

    public String getAdditionalNotes() {
        return additionalNotes;
    }

    public void setAdditionalNotes(String additionalNotes) {
        this.additionalNotes = additionalNotes;
    }

    public List<Long> getImageIds() {
        return imageIds;
    }

    public void setImageIds(List<Long> imageIds) {
        this.imageIds = imageIds;
    }

    public List<PackageTimeLineDto> getTimeLine() {
        return timeLine;
    }

    public void setTimeLine(List<PackageTimeLineDto> timeLine) {
        this.timeLine = timeLine;
    }

    public String getPaymentAt() {
        return paymentAt;
    }

    public void setPaymentAt(String paymentAt) {
        this.paymentAt = paymentAt;
    }

    public String getPickupRequestType() {
        return pickupRequestType;
    }

    public void setPickupRequestType(String pickupRequestType) {
        this.pickupRequestType = pickupRequestType;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}


    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

	public Long getSenderId() {
		return senderId;
	}

	public void setSenderId(Long senderId) {
		this.senderId = senderId;
	}

	public Long getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
	}

	public String getLatitude() {
		return latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


    
    
}
