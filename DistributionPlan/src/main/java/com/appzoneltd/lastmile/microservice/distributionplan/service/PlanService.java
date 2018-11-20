package com.appzoneltd.lastmile.microservice.distributionplan.service;

import java.security.Principal;
import java.util.List;

import com.appzoneltd.lastmile.microservice.distributionplan.dao.PlanEntity;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.Plan;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.PlanDetailsDto;
import com.appzoneltd.lastmile.microservice.distributionplan.dto.TimedPlanDetailsDto;

/**
 * Created by alaa.nabil on 2/15/2017.
 */
public interface PlanService {

    PlanEntity addPlan(Plan plan);

    List<Plan> getPlans(Principal principal);
    
    List<PlanDetailsDto> planDetails(Principal principal);

    TimedPlanDetailsDto tmpPlanDetails();

}
