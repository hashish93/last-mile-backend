package com.appzoneltd.lastmile.microservice.details.dto;

import java.io.Serializable;

public class Documents implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = 2122458304374178112L;
    private Long requestId;
    private String requestType;
    private Long customerIdImageId;
    private Long creditCardImageId;
    private String barcode;
    private String confirmationCode;

    public Documents() {
    }

    public Documents(Long requestId, String requestType, Long customerIdImageId, Long creditCardImageId, String barcode, String confirmationCode) {
        super();
        this.requestId = requestId;
        this.requestType = requestType;
        this.customerIdImageId = customerIdImageId;
        this.creditCardImageId = creditCardImageId;
        this.barcode = barcode;
        this.confirmationCode = confirmationCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCustomerIdImageId() {
        return customerIdImageId;
    }

    public void setCustomerIdImageId(Long customerIdImageId) {
        this.customerIdImageId = customerIdImageId;
    }

    public Long getCreditCardImageId() {
        return creditCardImageId;
    }

    public void setCreditCardImageId(Long creditCardImageId) {
        this.creditCardImageId = creditCardImageId;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

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

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public void setConfirmationCode(String confirmationCode) {
        this.confirmationCode = confirmationCode;
    }
}
