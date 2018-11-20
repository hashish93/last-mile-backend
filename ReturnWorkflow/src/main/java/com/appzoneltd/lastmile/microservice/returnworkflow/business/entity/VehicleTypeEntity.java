package com.appzoneltd.lastmile.microservice.returnworkflow.business.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vehicle_type", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "VehicleTypeEntity.countAll", query = "SELECT COUNT(x) FROM VehicleTypeEntity x")
})
public class VehicleTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "vehicle_type_id", nullable = false)
    private Long vehicleTypeId;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "version", nullable = false)
    private Long version;

    @OneToMany(mappedBy = "vehicleType", targetEntity = VehicleEntity.class)
    private List<VehicleEntity> listOfVehicle;

    public VehicleTypeEntity() {
        super();
    }

    public Long getVehicleTypeId() {
        return this.vehicleTypeId;
    }

    public void setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<VehicleEntity> getListOfVehicle() {
        return this.listOfVehicle;
    }

    public void setListOfVehicle(List<VehicleEntity> listOfVehicle) {
        this.listOfVehicle = listOfVehicle;
    }

}
