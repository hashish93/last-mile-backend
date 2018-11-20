package com.appzoneltd.lastmile.microservice.details.dto;

import java.io.Serializable;
import java.util.List;

/**
 * Created by alaa.nabil on 3/23/2017.
 */
public class JobOrdersRoute implements Serializable {

    List<JobOrder> jobOrders;
    List<Location> route;

    public JobOrdersRoute(List<JobOrder> jobOrders, List<Location> route) {
        this.jobOrders = jobOrders;
        this.route = route;
    }

    public List<JobOrder> getJobOrders() {
        return jobOrders;
    }

    public void setJobOrders(List<JobOrder> jobOrders) {
        this.jobOrders = jobOrders;
    }

    public List<Location> getRoute() {
        return route;
    }

    public void setRoute(List<Location> route) {
        this.route = route;
    }
}
