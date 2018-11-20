package com.appzoneltd.lastmile.microservice.details.dto;

import java.io.Serializable;

public class PackageType implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 4553295976636863432L;
    private Long packageTypeId;
    private String packageType;
    private String packageDimension;
    private Long expectedWeight;

    public PackageType() {
    }

    public PackageType(Long packageTypeId, String packageType, String packageDimension, Long expectedWeight) {
        super();
        this.packageTypeId = packageTypeId;
        this.packageType = packageType;
        this.packageDimension = packageDimension;
        this.expectedWeight = expectedWeight;
    }

    public Long getPackageTypeId() {
        return packageTypeId;
    }

    public void setPackageTypeId(Long packageTypeId) {
        this.packageTypeId = packageTypeId;
    }

    public String getPackageType() {
        return packageType;
    }

    public void setPackageType(String packageType) {
        this.packageType = packageType;
    }

    public String getPackageDimension() {
        return packageDimension;
    }

    public void setPackageDimension(String packageDimension) {
        this.packageDimension = packageDimension;
    }

    public Long getExpectedWeight() {
        return expectedWeight;
    }

    public void setExpectedWeight(Long expectedWeight) {
        this.expectedWeight = expectedWeight;
    }

}
