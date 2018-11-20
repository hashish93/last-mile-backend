package com.appzoneltd.lastmile.microservice.manualdistribution.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "active_vehicle", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "ActiveVehicleEntity.countAll", query = "SELECT COUNT(x) FROM ActiveVehicleEntity x")})
public class ActiveVehicleEntity implements Serializable {

    private static final long serialVersionUID = -3285611241140505386L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private DriverEntity driver;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DriverEntity getDriver() {
        return driver;
    }

    public void setDriver(DriverEntity driver) {
        this.driver = driver;
    }
}
