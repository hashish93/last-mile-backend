package com.appzoneltd.lastmile.microservice.manualdistribution.service.saveplan;

import com.appzoneltd.lastmile.microservice.manualdistribution.dto.SavePlanOrderDto;
import com.appzoneltd.lastmile.microservice.manualdistribution.model.TodayPlan;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface SavePlanService {

    Long savePlan(List<SavePlanOrderDto> savePlanOrders, Long hubId) throws JsonProcessingException;

    TodayPlan checkPlanForToday(Long hubId);
}
