package com.appzoneltd.lastmile.microservice.manualdistribution.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "driver", schema = "lastmile_core")
@NamedQueries({@NamedQuery(name = "DriverEntity.countAll", query = "SELECT COUNT(x) FROM DriverEntity x")})
public class DriverEntity implements Serializable {

    private static final long serialVersionUID = 7446937162380829976L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    public DriverEntity() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
