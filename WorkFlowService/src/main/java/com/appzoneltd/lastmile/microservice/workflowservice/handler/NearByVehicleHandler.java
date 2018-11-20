package com.appzoneltd.lastmile.microservice.workflowservice.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.appzoneltd.lastmile.microservice.workflowservice.kafka.models.WorkflowBase;


public class NearByVehicleHandler implements Clearable{

	private static final Map<Long, NearByVehicleHandler> instances = new HashMap<>();	
	private final List<WorkflowBase> workflowObjects = new ArrayList<>();
	private WorkflowHandler implementer;
	private long packageId;

	private NearByVehicleHandler() {
	}

	public static NearByVehicleHandler getInstance(Long packageId,WorkflowHandler workFLowHandler) {
		NearByVehicleHandler instance = instances.get(packageId);
		if (instance == null) {
			instance = new NearByVehicleHandler();
			instance.packageId = packageId;
			instance.implementer = workFLowHandler;
			instances.put(packageId, instance);
		}
		return instance;
	}

	public NearByVehicleHandler onError(Throwable throwable) {
		WorkFlowHandlerParams p = new WorkFlowHandlerParams();
		p.packageId = packageId;
		p.throwable = throwable;
		p.workflowObjects = workflowObjects;
		implementer.onError(p);
		return this;
	}

	public NearByVehicleHandler onNext(WorkflowBase workflowBase) {
		WorkFlowHandlerParams p = new WorkFlowHandlerParams();
		p.packageId = packageId;
		p.workflowObjects = workflowObjects;
		p.workflowBase = workflowBase;
		p.clearable = this;
		implementer.onNext(p);
		return this;
	}

	public void clear() {
		workflowObjects.clear();
		instances.put(packageId, null);
	}

	// -------

	

	
	
	

}