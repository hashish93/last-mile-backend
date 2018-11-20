package com.appzoneltd.lastmile.microservice.pickuprequest.dto;

import java.io.Serializable;
import java.util.Date;

public class PackageTimeLineDto implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;
    private Date creationDate;

    public PackageTimeLineDto() {
    }

    public PackageTimeLineDto(String status, Date creationDate) {
        this.status = status;
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
