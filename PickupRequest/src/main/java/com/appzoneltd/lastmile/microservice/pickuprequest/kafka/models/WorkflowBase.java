package com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models;

import lombok.Data;

@Data
public class WorkflowBase {

    private Long packageId;
    private Long requestId;
    private String requestType;

}
