package com.appzoneltd.lastmile.microservice.delivery.dto;

import java.util.Date;

/**
 * Created by alaa.nabil on 3/12/2017.
 */
public class DeliveryRequestDetails extends DeliveryRequest {

    private String packageValue;
    private Date requestTime;
    private Long requesterId;
    private String requesterName;
    private String requesterMobile;
    private Date pickupDate;
    private String pickupLongitude;
    private String pickupLatitude;
    private String pickupWaselLocation;
    private String pickupFormattedAddress;
    private Long recipientId;
    private String recipientName;
    private String recipientMobile;
    private String recipientLongitude;
    private String recipientLatitude;
    private String recipientWaselLocation;
    private String recipientFormattedAddress;
    private String recipientAdditionalInfo;
    private String additionalServices;
    private String labelingText;
    private String paymentType;
    private String paymentMethod;
    private String description;
    private Date created;
    private Long packageId;
    private DeliveryStatus deliveryStatus;

    public DeliveryRequestDetails() {
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public DeliveryRequestDetails setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
        return this;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public DeliveryRequestDetails setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
        return this;
    }

    public String getPickupLongitude() {
        return pickupLongitude;
    }

    public DeliveryRequestDetails setPickupLongitude(String pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
        return this;
    }

    public String getPickupLatitude() {
        return pickupLatitude;
    }

    public DeliveryRequestDetails setPickupLatitude(String pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
        return this;
    }

    public String getPickupWaselLocation() {
        return pickupWaselLocation;
    }

    public DeliveryRequestDetails setPickupWaselLocation(String pickupWaselLocation) {
        this.pickupWaselLocation = pickupWaselLocation;
        return this;
    }

    public String getPickupFormattedAddress() {
        return pickupFormattedAddress;
    }

    public DeliveryRequestDetails setPickupFormattedAddress(String pickupFormattedAddress) {
        this.pickupFormattedAddress = pickupFormattedAddress;
        return this;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public DeliveryRequestDetails setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
        return this;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public DeliveryRequestDetails setRecipientName(String recipientName) {
        this.recipientName = recipientName;
        return this;
    }

    public String getRecipientMobile() {
        return recipientMobile;
    }

    public DeliveryRequestDetails setRecipientMobile(String recipientMobile) {
        this.recipientMobile = recipientMobile;
        return this;
    }

    public String getRecipientLongitude() {
        return recipientLongitude;
    }

    public DeliveryRequestDetails setRecipientLongitude(String recipientLongitude) {
        this.recipientLongitude = recipientLongitude;
        return this;
    }

    public String getRecipientLatitude() {
        return recipientLatitude;
    }

    public DeliveryRequestDetails setRecipientLatitude(String recipientLatitude) {
        this.recipientLatitude = recipientLatitude;
        return this;
    }

    public String getRecipientWaselLocation() {
        return recipientWaselLocation;
    }

    public DeliveryRequestDetails setRecipientWaselLocation(String recipientWaselLocation) {
        this.recipientWaselLocation = recipientWaselLocation;
        return this;
    }

    public String getRecipientFormattedAddress() {
        return recipientFormattedAddress;
    }

    public DeliveryRequestDetails setRecipientFormattedAddress(String recipientFormattedAddress) {
        this.recipientFormattedAddress = recipientFormattedAddress;
        return this;
    }

    public String getRecipientAdditionalInfo() {
        return recipientAdditionalInfo;
    }

    public DeliveryRequestDetails setRecipientAdditionalInfo(String recipientAdditionalInfo) {
        this.recipientAdditionalInfo = recipientAdditionalInfo;
        return this;
    }

    public String getAdditionalServices() {
        return additionalServices;
    }

    public DeliveryRequestDetails setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
        return this;
    }

    public String getLabelingText() {
        return labelingText;
    }

    public DeliveryRequestDetails setLabelingText(String labelingText) {
        this.labelingText = labelingText;
        return this;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public DeliveryRequestDetails setPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public DeliveryRequestDetails setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public DeliveryRequestDetails setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public DeliveryRequestDetails setCreated(Date created) {
        this.created = created;
        return this;
    }

    public Long getPackageId() {
        return packageId;
    }

    public DeliveryRequestDetails setPackageId(Long packageId) {
        this.packageId = packageId;
        return this;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public DeliveryRequestDetails setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
        return this;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public DeliveryRequestDetails setRequesterName(String requesterName) {
        this.requesterName = requesterName;
        return this;
    }

    public String getRequesterMobile() {
        return requesterMobile;
    }

    public DeliveryRequestDetails setRequesterMobile(String requesterMobile) {
        this.requesterMobile = requesterMobile;
        return this;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public DeliveryRequestDetails setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
        return this;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }
}
