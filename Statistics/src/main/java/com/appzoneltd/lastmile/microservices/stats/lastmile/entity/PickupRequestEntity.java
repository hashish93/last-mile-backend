package com.appzoneltd.lastmile.microservices.stats.lastmile.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "pickup_request", schema = "lastmile_request")
@NamedQueries({
        @NamedQuery(name = "PickupRequestEntity.countAll", query = "SELECT COUNT(x) FROM PickupRequestEntity x")})
public class PickupRequestEntity {

    @Id
    @Column(name = "pickup_request_id", nullable = false)
    private Long pickupRequestId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "requesttime")
    private Date requesttime;

    @Column(name = "request_status", length = 50)
    private String requestStatus;

    @Column(name = "pickup_request_type_id")
    private Long requestTypeId;

    @Temporal(TemporalType.DATE)
    @Column(name = "pickupdate")
    private Date pickupdate;

    @Column(name = "hub_id")
    private Long hubId;

    @ManyToOne
    @JoinColumn(name = "requester_id", referencedColumnName = "customer_id")
    private CustomerEntity customerEntity;

    public Long getPickupRequestId() {
        return pickupRequestId;
    }

    public void setPickupRequestId(Long pickupRequestId) {
        this.pickupRequestId = pickupRequestId;
    }

    public Date getRequesttime() {
        return requesttime;
    }

    public void setRequesttime(Date requesttime) {
        this.requesttime = requesttime;
    }

    public String getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Long getRequestTypeId() {
        return requestTypeId;
    }

    public void setRequestTypeId(Long requestTypeId) {
        this.requestTypeId = requestTypeId;
    }

    public Long getHubId() {
        return hubId;
    }

    public void setHubId(Long hubId) {
        this.hubId = hubId;
    }

    public Date getPickupdate() {
        return pickupdate;
    }

    public void setPickupdate(Date pickupdate) {
        this.pickupdate = pickupdate;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public PickupRequestEntity setCustomerEntity(CustomerEntity customerEntity) {
        this.customerEntity = customerEntity;
        return this;
    }
}
