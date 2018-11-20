package com.appzoneltd.lastmile.microservice.manualdistribution.model;

import java.util.Date;

public class TodayPlan {

	private boolean checkPlanExist;
	private Date creationDate;

	public Date getCreationDate() {
		return creationDate;
	}
	
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public boolean isCheckPlanExist() {
		return checkPlanExist;
	}

	public void setCheckPlanExist(boolean checkPlanExist) {
		this.checkPlanExist = checkPlanExist;
	}

}
