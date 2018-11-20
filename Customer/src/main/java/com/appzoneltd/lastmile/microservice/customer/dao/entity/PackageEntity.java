package com.appzoneltd.lastmile.microservice.customer.dao.entity;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Entity
@Table(name = "package", schema = "lastmile_core")
public class PackageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "package_id", nullable = false)
    private Long packageId;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "actualweight", nullable = false)
    private BigDecimal actualweight;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "description", length = 500)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "tracking_number", length = 100)
    private String trackingNumber;

    @Column(name = "package_value")
    private String packageValue;
    
    @ManyToOne
    @JoinColumn(name="package_type_id", referencedColumnName="package_type_id")
    private PackageTypeEntity packageType ;

}
