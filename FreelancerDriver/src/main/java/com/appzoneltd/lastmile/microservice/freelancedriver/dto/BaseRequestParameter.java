package com.appzoneltd.lastmile.microservice.freelancedriver.dto;

import java.io.Serializable;

/**
 * Created by alaa.nabil on 4/12/2017.
 */
public class BaseRequestParameter implements Serializable {
    private Long requestId;
    private String requestType;

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }
}
