package com.appzoneltd.lastmile.microservice.details.dto;

/**
 * Created by alaa.nabil on 4/12/2017.
 */
public class PackageDetailsRequest extends BaseRequestParameter {
    private Long packageId;

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }
}
