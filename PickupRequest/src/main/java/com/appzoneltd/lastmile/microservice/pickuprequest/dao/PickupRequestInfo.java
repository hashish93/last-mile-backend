package com.appzoneltd.lastmile.microservice.pickuprequest.dao;

public class PickupRequestInfo extends PickupRequest {
    /**
     *
     */
    private static final long serialVersionUID = 4927038135253181628L;
    private Long pickupRequestTypeId;
    private String requesterMobile;
    private String requesterName;
    private String actualWeight;
    private String packageDimension;
    private String packageType;
    private String packageValue;
    private String receivedFrom;
    private Long hubId;
    private String buildingName ;
    private String cancellationReason;

    private Boolean inTodaysPlan;

    public PickupRequestInfo() {
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getRequesterMobile() {
        return requesterMobile;
    }

    public void setRequesterMobile(String requesterMobile) {
        this.requesterMobile = requesterMobile;
    }

    public String getRequesterName() {
        return requesterName;
    }

    public void setRequesterName(String requesterName) {
        this.requesterName = requesterName;
    }

    public String getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(String actualWeight) {
        this.actualWeight = actualWeight;
    }

    public String getPackageDimension() {
        return packageDimension;
    }

    public void setPackageDimension(String packageDimension) {
        this.packageDimension = packageDimension;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getReceivedFrom() {
        return receivedFrom;
    }

    public void setReceivedFrom(String receivedFrom) {
        this.receivedFrom = receivedFrom;
    }


    public Long getPickupRequestTypeId() {
        return pickupRequestTypeId;
    }


    public void setPickupRequestTypeId(Long pickupRequestTypeId) {
        this.pickupRequestTypeId = pickupRequestTypeId;
    }


    public Long getHubId() {
        return hubId;
    }


    public void setHubId(Long hubId) {
        this.hubId = hubId;
    }

    public String getBuildingName() {
		return buildingName;
	}

	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}

	public String getCancellationReason() {
        return cancellationReason;
    }

    public void setCancellationReason(String cancellationReason) {
        this.cancellationReason = cancellationReason;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }

    public Boolean getInTodaysPlan() {
        return inTodaysPlan;
    }

    public void setInTodaysPlan(Boolean inTodaysPlan) {
        this.inTodaysPlan = inTodaysPlan;
    }
}
