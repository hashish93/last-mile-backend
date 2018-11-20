package com.appzoneltd.lastmile.microservice.opensocket.entity;

import java.io.Serializable;

import com.couchbase.client.java.repository.annotation.Id;


public class CustomerQuery implements Serializable {
    /**
     *
     */
    private static final long serialVersionUID = -1405031680137481071L;
    @Id
    private Long _ID;
    private Long hubId;
    private Long vehicleId;
    private Long driverId;
    private final Boolean isCustomerTopic;
    private Integer port;
    private Integer serverId;

    public CustomerQuery() {
        this.isCustomerTopic = true;
    }

    public CustomerQuery(Long _ID, Long hubId, Long vehicleId, Long driverId, Boolean isCustomerTopic, Integer port, Integer serverId) {
        super();
        this._ID = _ID;
        this.hubId = hubId;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.isCustomerTopic = isCustomerTopic;
        this.port = port;
        this.serverId = serverId;
    }

    public Long get_ID() {
        return _ID;
    }

    public void set_ID(Long _ID) {
        this._ID = _ID;
    }

    public Long getHubId() {
        return hubId;
    }

    public void setHubId(Long hubId) {
        this.hubId = hubId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Boolean getIsCustomerTopic() {
        return isCustomerTopic;
    }

    public Integer getServerId() {
        return serverId;
    }

    public void setServerId(Integer serverId) {
        this.serverId = serverId;
    }
}
