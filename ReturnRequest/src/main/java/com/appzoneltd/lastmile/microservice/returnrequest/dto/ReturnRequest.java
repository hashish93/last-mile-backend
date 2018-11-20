package com.appzoneltd.lastmile.microservice.returnrequest.dto;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;


@Data
public class ReturnRequest implements Serializable {
    private Long requestId;
    private String requesterName;
    private String requesterMobile;
    private String returnAddress;
    private Date returnDate;
    private String returnTimeFrom;
    private String returnTimeTo;
    private String packageType;
    private String requestStatus;

    private Long hubId;
    private String hubName;
    
    private Boolean inTodaysPlan;
}
