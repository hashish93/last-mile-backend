package com.appzoneltd.lastmile.microservice.manualdistribution.model;

import java.util.List;

public class ValidatePlanRequest {
	
	private List<PlanOutline> planOutlines;

	public List<PlanOutline> getPlanOutlines() {
		return planOutlines;
	}

	public void setPlanOutlines(List<PlanOutline> planOutlines) {
		this.planOutlines = planOutlines;
	}

}
