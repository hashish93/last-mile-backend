package com.appzoneltd.lastmile.microservice.customer.dto;

import java.io.Serializable;

/**
 * Created by alaa.nabil on 3/20/2017.
 */
public class CustomerVerification implements Serializable {
    private String code;

    public CustomerVerification() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
