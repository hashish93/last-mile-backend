package com.appzoneltd.lastmile.microservice.manualdistribution.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by alaa.nabil on 9/5/2017.
 */
public class PlanOrdersHubContainer {
    List<SavePlanOrderDto> savePlanOrders;
    
    Long hubId;

    public List<SavePlanOrderDto> getSavePlanOrders() {
        return savePlanOrders;
    }

    public PlanOrdersHubContainer setSavePlanOrders(List<SavePlanOrderDto> savePlanOrders) {
        this.savePlanOrders = savePlanOrders;
        return this;
    }

    public Long getHubId() {
        return hubId;
    }

    public PlanOrdersHubContainer setHubId(Long hubId) {
        this.hubId = hubId;
        return this;
    }
}
