package com.appzoneltd.lastmile.microservice.offloading.dto;

import java.io.Serializable;

/**
 * Created by alaa.nabil on 8/6/2017.
 */
public class PackageSummaryModel implements Serializable {
    private Long packageId;
    private Long requestId;
    private String requestType;
    private String trackingNumber;
    private String category;
    private String weight;
    private Boolean isPackageOffloaded;
    private String comment;

    public Long getPackageId() {
        return packageId;
    }

    public PackageSummaryModel setPackageId(Long packageId) {
        this.packageId = packageId;
        return this;
    }

    public Long getRequestId() {
        return requestId;
    }

    public PackageSummaryModel setRequestId(Long requestId) {
        this.requestId = requestId;
        return this;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public PackageSummaryModel setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
        return this;
    }

    public String getCategory() {
        return category;
    }

    public PackageSummaryModel setCategory(String category) {
        this.category = category;
        return this;
    }

    public String getWeight() {
        return weight;
    }

    public PackageSummaryModel setWeight(String weight) {
        this.weight = weight;
        return this;
    }

    public String getRequestType() {
        return requestType;
    }

    public PackageSummaryModel setRequestType(String requestType) {
        this.requestType = requestType;
        return this;
    }

    public Boolean getPackageOffloaded() {
        return isPackageOffloaded;
    }

    public PackageSummaryModel setPackageOffloaded(Boolean packageOffloaded) {
        isPackageOffloaded = packageOffloaded;
        return this;
    }

    public String getComment() {
        return comment;
    }

    public PackageSummaryModel setComment(String comment) {
        this.comment = comment;
        return this;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PackageSummaryModel{");
        sb.append("packageId=").append(packageId);
        sb.append(", requestId=").append(requestId);
        sb.append(", requestType='").append(requestType).append('\'');
        sb.append(", trackingNumber='").append(trackingNumber).append('\'');
        sb.append(", category='").append(category).append('\'');
        sb.append(", weight='").append(weight).append('\'');
        sb.append(", isPackageOffloaded=").append(isPackageOffloaded);
        sb.append(", comment='").append(comment).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
