package com.appzoneltd.lastmile.microservice.building.kafka.models;

import lombok.Data;

import java.util.Date;

@Data
public class WorkflowPackageStatus extends WorkflowBase {

    private ChangePackageStatusEnum status;


    private Long requestId;
    private Long requesterId;
    private String timeFrom;
    private String timeTo;
    private Date pickupDate;
    private Boolean isWebUser;
}
