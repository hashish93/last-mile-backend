package com.appzoneltd.lastmile.microservice.returnrequest.kafka.model;

import lombok.Data;

@Data
public class WorkflowBase {

    private Long packageId;
    private Long requestId;
    private String requestType;

}
