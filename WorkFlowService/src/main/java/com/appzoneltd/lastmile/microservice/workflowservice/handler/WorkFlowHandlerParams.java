package com.appzoneltd.lastmile.microservice.workflowservice.handler;																																																																					

import java.util.List;

import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowBase;

public class WorkFlowHandlerParams {
	Long packageId;
	WorkflowBase workflowBase;
	List<WorkflowBase> workflowObjects;
	Clearable clearable;
	Throwable throwable;
}
