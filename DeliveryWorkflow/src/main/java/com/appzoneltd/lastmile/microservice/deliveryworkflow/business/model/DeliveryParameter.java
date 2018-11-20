package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.model;

import lombok.Data;

import java.util.Date;

@Data
public class DeliveryParameter {

    private Long packageId;
    private Long driverId;
    private boolean driverAcceptance;
    private boolean customerApproveInvoice;
    private boolean customerFound;
    private Date deliveryDate;
    private String deliveryTimeFrom;
    private String deliveryTimeTo;
    private Long requestId;
    private Long activeVehicleId;

}
