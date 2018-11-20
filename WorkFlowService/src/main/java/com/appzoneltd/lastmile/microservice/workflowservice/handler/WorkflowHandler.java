package com.appzoneltd.lastmile.microservice.workflowservice.handler;

public interface WorkflowHandler {
	void onNext(WorkFlowHandlerParams p);
	void onError(WorkFlowHandlerParams p);
}