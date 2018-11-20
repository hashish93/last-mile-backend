package com.appzoneltd.lastmile.microservice.details.dto;

import java.io.Serializable;

/**
 * Created by alaa.nabil on 3/13/2017.
 */
public class SmsModel implements Serializable {
    private Long[] ids;
    private String receiverType;
    private String message;

    public SmsModel() {
    }

    public SmsModel(Long[] ids, String receiverType, String message) {
        this.ids = ids;
        this.receiverType = receiverType;
        this.message = message;
    }

    public Long[] getIds() {
        return ids;
    }

    public void setIds(Long[] ids) {
        this.ids = ids;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(String receiverType) {
        this.receiverType = receiverType;
    }
}
