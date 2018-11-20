package com.appzoneltd.lastmile.microservice.returnrequest.dto;

import java.io.Serializable;
import java.util.Date;


public class RequestHistoryTimeLine implements Serializable {

    private String status;
    private Date creationDate;

    public RequestHistoryTimeLine(String status, Date creationDate) {
        this.status = status;
        this.creationDate = creationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
