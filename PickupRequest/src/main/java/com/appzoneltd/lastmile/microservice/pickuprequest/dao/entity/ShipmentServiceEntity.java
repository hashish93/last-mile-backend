package com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shipment_service", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "ShipmentServiceEntity.countAll", query = "SELECT COUNT(x) FROM ShipmentServiceEntity x")
})
public class ShipmentServiceEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "shipment_service_id", nullable = false)
    private Long shipmentServiceId;

    @Column(name = "service", nullable = false, length = 50)
    private String service;

    @Column(name = "description", length = 500)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "version", nullable = false)
    private Long version;

    @OneToMany(mappedBy = "shipmentService", targetEntity = ShipmentServiceTypeEntity.class)
    private List<ShipmentServiceTypeEntity> listOfShipmentServiceType;

    public ShipmentServiceEntity() {
        super();
    }

    public Long getShipmentServiceId() {
        return this.shipmentServiceId;
    }

    public void setShipmentServiceId(Long shipmentServiceId) {
        this.shipmentServiceId = shipmentServiceId;
    }

    public String getService() {
        return this.service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<ShipmentServiceTypeEntity> getListOfShipmentServiceType() {
        return this.listOfShipmentServiceType;
    }

    public void setListOfShipmentServiceType(List<ShipmentServiceTypeEntity> listOfShipmentServiceType) {
        this.listOfShipmentServiceType = listOfShipmentServiceType;
    }
}
