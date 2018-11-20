package com.appzoneltd.lastmile.microservice.details.dto;

import com.appzoneltd.lastmile.microservice.details.dao.couchbase.Location;

import java.io.Serializable;

public class CancelRequest extends BaseRequestParameter implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 491104176291330428L;
    private Long packageId;
    private Long driverId;
    private long reasonId;
    private Long requesterId;
    private com.appzoneltd.lastmile.microservice.details.dao.couchbase.Location location;
    private String description;
    
    private String rejectionPhase ;

    public CancelRequest() {
    }

    public long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public void setPackageId(long packageId) {
        this.packageId = packageId;
    }

    public long getReasonId() {
        return reasonId;
    }

    public void setReasonId(long reasonId) {
        this.reasonId = reasonId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public void setRequesterId(Long requesterId) {
        this.requesterId = requesterId;
    }

    public com.appzoneltd.lastmile.microservice.details.dao.couchbase.Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

	public String getRejectionPhase() {
		return rejectionPhase;
	}

	public void setRejectionPhase(String rejectionPhase) {
		this.rejectionPhase = rejectionPhase;
	}

}
