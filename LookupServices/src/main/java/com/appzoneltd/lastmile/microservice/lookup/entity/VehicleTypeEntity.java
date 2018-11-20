package com.appzoneltd.lastmile.microservice.lookup.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name = "vehicle_type", schema = "lastmile_core")
public class VehicleTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "vehicle_type_id", nullable = false)
    private Long vehicleTypeId;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "type_ar", nullable = false, length = 50)
    private String typeAr;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "version", nullable = false)
    private Long version;

}
