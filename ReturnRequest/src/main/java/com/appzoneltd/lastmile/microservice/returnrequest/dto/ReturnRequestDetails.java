package com.appzoneltd.lastmile.microservice.returnrequest.dto;

import java.util.Date;

/**
 * Created by alaa.nabil on 3/12/2017.
 */
public class ReturnRequestDetails extends ReturnRequest {

    private String packageValue;
    private Date requestTime;
    private Long requesterId;
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
    private String returnLongitude;
    private String returnLatitude;
    private String returnDescription;
    private String recipientAdditionalInfo;
    private String additionalServices;
    private String labelingText;
    private String paymentType;
    private String paymentMethod;
    private String description;
    private Date created;
    private Long packageId;
    private ReturnStatus returnStatus;

    public ReturnRequestDetails() {
    }

    public Date getRequestTime() {
        return requestTime;
    }

    public ReturnRequestDetails setRequestTime(Date requestTime) {
        this.requestTime = requestTime;
        return this;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public ReturnRequestDetails setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
        return this;
    }

    public String getPickupLongitude() {
        return pickupLongitude;
    }

    public ReturnRequestDetails setPickupLongitude(String pickupLongitude) {
        this.pickupLongitude = pickupLongitude;
        return this;
    }

    public String getPickupLatitude() {
        return pickupLatitude;
    }

    public ReturnRequestDetails setPickupLatitude(String pickupLatitude) {
        this.pickupLatitude = pickupLatitude;
        return this;
    }

    public String getPickupWaselLocation() {
        return pickupWaselLocation;
    }

    public ReturnRequestDetails setPickupWaselLocation(String pickupWaselLocation) {
        this.pickupWaselLocation = pickupWaselLocation;
        return this;
    }

    public String getPickupFormattedAddress() {
        return pickupFormattedAddress;
    }

    public ReturnRequestDetails setPickupFormattedAddress(String pickupFormattedAddress) {
        this.pickupFormattedAddress = pickupFormattedAddress;
        return this;
    }

    public Long getRecipientId() {
        return recipientId;
    }

    public ReturnRequestDetails setRecipientId(Long recipientId) {
        this.recipientId = recipientId;
        return this;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public ReturnRequestDetails setRecipientName(String recipientName) {
        this.recipientName = recipientName;
        return this;
    }

    public String getRecipientMobile() {
        return recipientMobile;
    }

    public ReturnRequestDetails setRecipientMobile(String recipientMobile) {
        this.recipientMobile = recipientMobile;
        return this;
    }

    public String getRecipientLongitude() {
        return recipientLongitude;
    }

    public ReturnRequestDetails setRecipientLongitude(String recipientLongitude) {
        this.recipientLongitude = recipientLongitude;
        return this;
    }

    public String getRecipientLatitude() {
        return recipientLatitude;
    }

    public ReturnRequestDetails setRecipientLatitude(String recipientLatitude) {
        this.recipientLatitude = recipientLatitude;
        return this;
    }

    public String getRecipientWaselLocation() {
        return recipientWaselLocation;
    }

    public ReturnRequestDetails setRecipientWaselLocation(String recipientWaselLocation) {
        this.recipientWaselLocation = recipientWaselLocation;
        return this;
    }

    public String getRecipientFormattedAddress() {
        return recipientFormattedAddress;
    }

    public ReturnRequestDetails setRecipientFormattedAddress(String recipientFormattedAddress) {
        this.recipientFormattedAddress = recipientFormattedAddress;
        return this;
    }

    public String getRecipientAdditionalInfo() {
        return recipientAdditionalInfo;
    }

    public ReturnRequestDetails setRecipientAdditionalInfo(String recipientAdditionalInfo) {
        this.recipientAdditionalInfo = recipientAdditionalInfo;
        return this;
    }

    public String getAdditionalServices() {
        return additionalServices;
    }

    public ReturnRequestDetails setAdditionalServices(String additionalServices) {
        this.additionalServices = additionalServices;
        return this;
    }

    public String getLabelingText() {
        return labelingText;
    }

    public ReturnRequestDetails setLabelingText(String labelingText) {
        this.labelingText = labelingText;
        return this;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public ReturnRequestDetails setPaymentType(String paymentType) {
        this.paymentType = paymentType;
        return this;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public ReturnRequestDetails setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public ReturnRequestDetails setDescription(String description) {
        this.description = description;
        return this;
    }

    public Date getCreated() {
        return created;
    }

    public ReturnRequestDetails setCreated(Date created) {
        this.created = created;
        return this;
    }

    public Long getPackageId() {
        return packageId;
    }

    public ReturnRequestDetails setPackageId(Long packageId) {
        this.packageId = packageId;
        return this;
    }

    public ReturnStatus getReturnStatus() {
        return returnStatus;
    }

    public ReturnRequestDetails setReturnStatus(ReturnStatus returnStatus) {
        this.returnStatus = returnStatus;
        return this;
    }

    public Date getPickupDate() {
        return pickupDate;
    }

    public ReturnRequestDetails setPickupDate(Date pickupDate) {
        this.pickupDate = pickupDate;
        return this;
    }

    public String getReturnLongitude() {
        return returnLongitude;
    }

    public ReturnRequestDetails setReturnLongitude(String returnLongitude) {
        this.returnLongitude = returnLongitude;
        return this;
    }

    public String getReturnLatitude() {
        return returnLatitude;
    }

    public ReturnRequestDetails setReturnLatitude(String returnLatitude) {
        this.returnLatitude = returnLatitude;
        return this;
    }

    public String getReturnDescription() {
        return returnDescription;
    }

    public ReturnRequestDetails setReturnDescription(String returnDescription) {
        this.returnDescription = returnDescription;
        return this;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }
}
