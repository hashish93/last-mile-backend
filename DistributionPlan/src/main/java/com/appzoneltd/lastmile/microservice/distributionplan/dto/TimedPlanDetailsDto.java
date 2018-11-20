package com.appzoneltd.lastmile.microservice.distributionplan.dto;

import java.util.Date;
import java.util.List;


/**
 * Created by hashish on 4/20/2017.
 */
public class TimedPlanDetailsDto  {
    private Date created;
    List<PlanDetailsDto> planDetails;

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<PlanDetailsDto> getPlanDetails() {
        return planDetails;
    }

    public void setPlanDetails(List<PlanDetailsDto> planDetails) {
        this.planDetails = planDetails;
    }
}
