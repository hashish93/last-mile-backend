package com.appzoneltd.lastmile.microservice.pickuprequest.kafka.models;

import lombok.Data;

@Data
public class WorkflowCancelRequest extends WorkflowBase {

    private boolean admin;
    private Long requesterId;
    private Long requestId;
    private Long reasonId;
    private String description;


}
