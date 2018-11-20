package com.appzoneltd.lastmile.microservice.freelancedriver.dto;

import java.io.Serializable;

/**
 * Created by alaa.nabil on 4/10/2017.
 */
public class DriverResponseDto implements Serializable {
    private Long loadingActivityId;
    private Boolean isAccept;
    private Long rejectionReasonId;
    private String rejectionReason;

    public String getRejectionReason() {
        return rejectionReason;
    }

    public void setRejectionReason(String rejectionReason) {
        this.rejectionReason = rejectionReason;
    }

    public Long getLoadingActivityId() {
        return loadingActivityId;
    }

    public void setLoadingActivityId(Long loadingActivityId) {
        this.loadingActivityId = loadingActivityId;
    }

    public Boolean getIsAccept() {
        return isAccept;
    }

    public void setIsAccept(Boolean accept) {
        isAccept = accept;
    }

    public Long getRejectionReasonId() {
        return rejectionReasonId;
    }

    public void setRejectionReasonId(Long rejectionReasonId) {
        this.rejectionReasonId = rejectionReasonId;
    }
}
