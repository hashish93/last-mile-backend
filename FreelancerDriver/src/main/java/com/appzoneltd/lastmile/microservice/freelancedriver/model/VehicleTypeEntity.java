package com.appzoneltd.lastmile.microservice.freelancedriver.model;

import javax.persistence.*;
import java.io.Serializable;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getVehicleTypeId() {
        return vehicleTypeId;
    }

    public VehicleTypeEntity setVehicleTypeId(Long vehicleTypeId) {
        this.vehicleTypeId = vehicleTypeId;
        return this;
    }

    public String getType() {
        return type;
    }

    public VehicleTypeEntity setType(String type) {
        this.type = type;
        return this;
    }
}
