package com.appzoneltd.lastmile.microservice.workflow.kafka.models;

import java.io.Serializable;

/**
 * Created by alaa.nabil on 2/20/2017.
 */
public class CustomerAlternativesDTO implements Serializable {
    private Long requestId;
    private Long requesterId;
    private Long packageId;
    private Boolean isWebUser;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public Boolean getWebUser() {
        return isWebUser;
    }

    public void setWebUser(Boolean webUser) {
        isWebUser = webUser;
    }

    @Override
    public String toString() {
        return "CustomerAlternativesDTO{" +
                "requestId=" + requestId +
                ", requesterId=" + requesterId +
                ", packageId=" + packageId +
                ", isWebUser=" + isWebUser +
                '}';
    }
}
