package com.appzoneltd.lastmile.microservice.ondemandworkflow.kafka.model;


import lombok.Data;

@Data
public class WorkflowCancelRequest{
	
	private boolean admin ;
	private Long requesterId;
	private Long packageId;
	private Long requestId;
	private Long driverId;
	private long reasonId;
	

}
