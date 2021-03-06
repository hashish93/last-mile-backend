/*
 * Created on 7 Nov 2016 ( Time 11:30:23 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
package com.appzoneltd.lastmile.microservice.pickuprequest.dao;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

/**
 * Java bean for 'PickupRequest' entity
 *
 * @author Telosys Tools
 *
 */
public class PickupRequest implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 3391718556791312717L;
	private Long pickupRequestId;
	private String requestType;
	private Date requestTime;
	private Long requesterId;
	private String pickupLongitude;
	private String pickupLatitude;
	private String pickupWaselLocation;
	private String pickupFormatedAddress;
	private String timeFrom;
	private String timeTo;
	@NotNull(message = "pickuprequest.pickupdate.notnull")
	private Date pickupDate;
	private Long recipientId;
	@NotEmpty(message = "pickuprequest.recipientname.notempty")
//	@Pattern(regexp = "^[a-zA-Z][a-zA-Z ]*$", message = "pickuprequest.recipientname.invalid")
	@Pattern(regexp = "^[^\\p{Punct}]*$", message = "pickuprequest.recipientname.invalid")
	private String recipientName;
	@NotEmpty(message = "pickuprequest.recipientmobile.notempty")
	@Pattern(regexp = "[0-9]*", message = "pickuprequest.recipientmobile.invalid")
	private String recipientMobile;
	private String recipientLongitude;
	private String recipientLatitude;
	private String recipientWaselLocation;
	private String recipientFormatedAddress;
	private String recipientAdditionalInfo;
	private Long pickupServiceTypeId;
	private String additionalServices;
	private String labelingText;
	@NotEmpty(message = "pickuprequest.paymenttype.notempty")
	private String paymentType;
	@NotEmpty(message = "pickuprequest.paymentmethod.notempty")
	private String paymentMethod;
	private String description;
	private Long version;
	private Date created;
	private Long packageId;
	private OrderStatus orderStatus;
	private String countryCode;

	public PickupRequest() {
		// TODO Auto-generated constructor stub
	}

	public PickupRequest(Long pickupRequestId, String requestType, Date requestTime, Long requesterId,
			String pickupLongitude, String pickupLatitude, String pickupWaselLocation, String pickupFormatedAddress,
			String timeFrom, String timeTo, Date pickupDate, Long recipientId, String recipientName,
			String recipientMobile, String recipientLongitude, String recipientLatitude, String recipientWaselLocation,
			String recipientFormatedAddress, String recipientAdditionalInfo, Long pickupServiceTypeId,
			String additionalServices, String labelingText, String paymentType, String paymentMethod,
			String description, Long version, Date created, Long packageId, OrderStatus orderStatus ,String countryCode) {
		super();
		this.pickupRequestId = pickupRequestId;
		this.requestType = requestType;
		this.requestTime = requestTime;
		this.requesterId = requesterId;
		this.pickupLongitude = pickupLongitude;
		this.pickupLatitude = pickupLatitude;
		this.pickupWaselLocation = pickupWaselLocation;
		this.pickupFormatedAddress = pickupFormatedAddress;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.pickupDate = pickupDate;
		this.recipientId = recipientId;
		this.recipientName = recipientName;
		this.recipientMobile = recipientMobile;
		this.recipientLongitude = recipientLongitude;
		this.recipientLatitude = recipientLatitude;
		this.recipientWaselLocation = recipientWaselLocation;
		this.recipientFormatedAddress = recipientFormatedAddress;
		this.recipientAdditionalInfo = recipientAdditionalInfo;
		this.pickupServiceTypeId = pickupServiceTypeId;
		this.additionalServices = additionalServices;
		this.labelingText = labelingText;
		this.paymentType = paymentType;
		this.paymentMethod = paymentMethod;
		this.description = description;
		this.version = version;
		this.created = created;
		this.packageId = packageId;
		this.orderStatus = orderStatus;
		this.countryCode = countryCode;
	}

	public Long getPickupRequestId() {
		return pickupRequestId;
	}

	public void setPickupRequestId(Long pickupRequestId) {
		this.pickupRequestId = pickupRequestId;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public Date getRequestTime() {
		return requestTime;
	}

	public void setRequestTime(Date requestTime) {
		this.requestTime = requestTime;
	}

	public Long getRequesterId() {
		return requesterId;
	}

	public void setRequesterId(Long requesterId) {
		this.requesterId = requesterId;
	}

	public String getPickupLongitude() {
		return pickupLongitude;
	}

	public void setPickupLongitude(String pickupLongitude) {
		this.pickupLongitude = pickupLongitude;
	}

	public String getPickupLatitude() {
		return pickupLatitude;
	}

	public void setPickupLatitude(String pickupLatitude) {
		this.pickupLatitude = pickupLatitude;
	}

	public String getPickupWaselLocation() {
		return pickupWaselLocation;
	}

	public void setPickupWaselLocation(String pickupWaselLocation) {
		this.pickupWaselLocation = pickupWaselLocation;
	}

	public String getPickupFormatedAddress() {
		return pickupFormatedAddress;
	}

	public void setPickupFormatedAddress(String pickupFormatedAddress) {
		this.pickupFormatedAddress = pickupFormatedAddress;
	}

	public String getTimeFrom() {
		return timeFrom;
	}

	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}

	public String getTimeTo() {
		return timeTo;
	}

	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Long getRecipientId() {
		return recipientId;
	}

	public void setRecipientId(Long recipientId) {
		this.recipientId = recipientId;
	}

	public String getRecipientName() {
		return recipientName;
	}

	public void setRecipientName(String recipientName) {
		this.recipientName = recipientName;
	}

	public String getRecipientMobile() {
		return recipientMobile;
	}

	public void setRecipientMobile(String recipientMobile) {
		this.recipientMobile = recipientMobile;
	}

	public String getRecipientLongitude() {
		return recipientLongitude;
	}

	public void setRecipientLongitude(String recipientLongitude) {
		this.recipientLongitude = recipientLongitude;
	}

	public String getRecipientLatitude() {
		return recipientLatitude;
	}

	public void setRecipientLatitude(String recipientLatitude) {
		this.recipientLatitude = recipientLatitude;
	}

	public String getRecipientWaselLocation() {
		return recipientWaselLocation;
	}

	public void setRecipientWaselLocation(String recipientWaselLocation) {
		this.recipientWaselLocation = recipientWaselLocation;
	}

	public String getRecipientFormatedAddress() {
		return recipientFormatedAddress;
	}

	public void setRecipientFormatedAddress(String recipientFormatedAddress) {
		this.recipientFormatedAddress = recipientFormatedAddress;
	}

	public String getRecipientAdditionalInfo() {
		return recipientAdditionalInfo;
	}

	public void setRecipientAdditionalInfo(String recipientAdditionalInfo) {
		this.recipientAdditionalInfo = recipientAdditionalInfo;
	}

	public Long getPickupServiceTypeId() {
		return pickupServiceTypeId;
	}

	public void setPickupServiceTypeId(Long pickupServiceTypeId) {
		this.pickupServiceTypeId = pickupServiceTypeId;
	}

	public String getAdditionalServices() {
		return additionalServices;
	}

	public void setAdditionalServices(String additionalServices) {
		this.additionalServices = additionalServices;
	}

	public String getLabelingText() {
		return labelingText;
	}

	public void setLabelingText(String labelingText) {
		this.labelingText = labelingText;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Long getPackageId() {
		return packageId;
	}

	public void setPackageId(Long packageId) {
		this.packageId = packageId;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		if((countryCode!=null) && (!countryCode.isEmpty())){
			this.countryCode = "+"+countryCode;
		}		
	}

	@Override
	public String toString() {
		return "PickupRequest [pickupRequestId=" + pickupRequestId + ", requestType=" + requestType + ", requestTime="
				+ requestTime + ", requesterId=" + requesterId + ", pickupLongitude=" + pickupLongitude
				+ ", pickupLatitude=" + pickupLatitude + ", pickupWaselLocation=" + pickupWaselLocation
				+ ", pickupFormatedAddress=" + pickupFormatedAddress + ", timeFrom=" + timeFrom + ", timeTo=" + timeTo
				+ ", pickupDate=" + pickupDate + ", recipientId=" + recipientId + ", recipientName=" + recipientName
				+ ", recipientMobile=" + recipientMobile + ", recipientLongitude=" + recipientLongitude
				+ ", recipientLatitude=" + recipientLatitude + ", recipientWaselLocation=" + recipientWaselLocation
				+ ", recipientFormatedAddress=" + recipientFormatedAddress + ", recipientAdditionalInfo="
				+ recipientAdditionalInfo + ", pickupServiceTypeId=" + pickupServiceTypeId + ", additionalServices="
				+ additionalServices + ", labelingText=" + labelingText + ", paymentType=" + paymentType
				+ ", paymentMethod=" + paymentMethod + ", description=" + description + ", version=" + version
				+ ", created=" + created + ", packageId=" + packageId + ", orderStatus=" + orderStatus
				+ ", getPickupRequestId()=" + getPickupRequestId() + ", getRequestType()=" + getRequestType()
				+ ", getRequestTime()=" + getRequestTime() + ", getRequesterId()=" + getRequesterId()
				+ ", getPickupLongitude()=" + getPickupLongitude() + ", getPickupLatitude()=" + getPickupLatitude()
				+ ", getPickupWaselLocation()=" + getPickupWaselLocation() + ", getPickupFormatedAddress()="
				+ getPickupFormatedAddress() + ", getTimeFrom()=" + getTimeFrom() + ", getTimeTo()=" + getTimeTo()
				+ ", getPickupDate()=" + getPickupDate() + ", getRecipientId()=" + getRecipientId()
				+ ", getRecipientName()=" + getRecipientName() + ", getRecipientMobile()=" + getRecipientMobile()
				+ ", getRecipientLongitude()=" + getRecipientLongitude() + ", getRecipientLatitude()="
				+ getRecipientLatitude() + ", getRecipientWaselLocation()=" + getRecipientWaselLocation()
				+ ", getRecipientFormatedAddress()=" + getRecipientFormatedAddress() + ", getRecipientAdditionalInfo()="
				+ getRecipientAdditionalInfo() + ", getPickupServiceTypeId()=" + getPickupServiceTypeId()
				+ ", getAdditionalServices()=" + getAdditionalServices() + ", getLabelingText()=" + getLabelingText()
				+ ", getPaymentType()=" + getPaymentType() + ", getPaymentMethod()=" + getPaymentMethod()
				+ ", getDescription()=" + getDescription() + ", getVersion()=" + getVersion() + ", getCreated()="
				+ getCreated() + ", getPackageId()=" + getPackageId() + ", getOrderStatus()=" + getOrderStatus()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
	
	

}
