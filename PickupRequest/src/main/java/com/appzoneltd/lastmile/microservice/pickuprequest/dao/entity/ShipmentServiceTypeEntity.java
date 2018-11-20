package com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "shipment_service_type", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "ShipmentServiceTypeEntity.countAll", query = "SELECT COUNT(x) FROM ShipmentServiceTypeEntity x")
})
public class ShipmentServiceTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "shipment_service_type_id", nullable = false)
    private Long shipmentServiceTypeId;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "description", length = 500)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "version", nullable = false)
    private Long version;

    @OneToMany(mappedBy = "shipmentServiceType", targetEntity = PackageEntity.class)
    private List<PackageEntity> listOfPackage;

    @ManyToOne
    @JoinColumn(name = "shipment_service_id", referencedColumnName = "shipment_service_id")
    private ShipmentServiceEntity shipmentService;

    public ShipmentServiceTypeEntity() {
        super();
    }

    public Long getShipmentServiceTypeId() {
        return this.shipmentServiceTypeId;
    }

    public void setShipmentServiceTypeId(Long shipmentServiceTypeId) {
        this.shipmentServiceTypeId = shipmentServiceTypeId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<PackageEntity> getListOfPackage() {
        return this.listOfPackage;
    }

    public void setListOfPackage(List<PackageEntity> listOfPackage) {
        this.listOfPackage = listOfPackage;
    }

    public ShipmentServiceEntity getShipmentService() {
        return this.shipmentService;
    }

    public void setShipmentService(ShipmentServiceEntity shipmentService) {
        this.shipmentService = shipmentService;
    }

}
