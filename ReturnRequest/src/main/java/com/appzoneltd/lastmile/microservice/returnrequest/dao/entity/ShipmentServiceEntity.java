package com.appzoneltd.lastmile.microservice.returnrequest.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "shipment_service", schema = "lastmile_core")
// Define named queries here
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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getShipmentServiceId() {
        return shipmentServiceId;
    }

    public void setShipmentServiceId(Long shipmentServiceId) {
        this.shipmentServiceId = shipmentServiceId;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<ShipmentServiceTypeEntity> getListOfShipmentServiceType() {
        return listOfShipmentServiceType;
    }

    public void setListOfShipmentServiceType(List<ShipmentServiceTypeEntity> listOfShipmentServiceType) {
        this.listOfShipmentServiceType = listOfShipmentServiceType;
    }
}
