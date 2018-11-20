package com.appzoneltd.lastmile.microservice.distributionplan.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by alaa.nabil on 2/16/2017.
 */
public class PlanOrder implements Serializable {
    private Long id;
    private String driverName;
    private Date workShiftFrom;
    private Date workShiftTo;
    private String vehicleType;
    private List<Order> jobs;

    public PlanOrder() {
    }

    public PlanOrder(Long id, String driverName, Date workShiftFrom, Date workShiftTo, String vehicleType, List<Order> jobs) {
        this.id = id;
        this.driverName = driverName;
        this.workShiftFrom = workShiftFrom;
        this.workShiftTo = workShiftTo;
        this.vehicleType = vehicleType;
        this.jobs = jobs;
    }

    public Long getId() {
        return id;
    }

    public PlanOrder setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

    public PlanOrder setDriverName(String driverName) {
        this.driverName = driverName;
        return this;
    }

    public Date getWorkShiftFrom() {
        return workShiftFrom;
    }

    public PlanOrder setWorkShiftFrom(Date workShiftFrom) {
        this.workShiftFrom = workShiftFrom;
        return this;
    }

    public Date getWorkShiftTo() {
        return workShiftTo;
    }

    public PlanOrder setWorkShiftTo(Date workShiftTo) {
        this.workShiftTo = workShiftTo;
        return this;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public PlanOrder setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
        return this;
    }

    public List<Order> getJobs() {
        return jobs;
    }

    public PlanOrder setJobs(List<Order> jobs) {
        this.jobs = jobs;
        return this;
    }
}
