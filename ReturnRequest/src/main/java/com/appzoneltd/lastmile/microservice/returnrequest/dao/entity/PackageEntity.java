package com.appzoneltd.lastmile.microservice.returnrequest.dao.entity;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@Entity
@Table(name = "package", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "PackageEntity.countAll", query = "SELECT COUNT(x) FROM PackageEntity x")
})
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

    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "tracking_number", length = 100)
    private String trackingNumber;

    @Column(name = "package_value")
    private String packageValue;

//    @OneToMany(mappedBy = "package", targetEntity = PlanOrderEntity.class)
//    private List<PlanOrderEntity> listOfPlanOrder;

    @ManyToOne
    @JoinColumn(name = "package_type_id", referencedColumnName = "package_type_id")
    private PackageTypeEntity packageType;

    @OneToOne
    @JoinColumn(name = "package_id", referencedColumnName = "package_id")
    private VerifiedPackageEntity verifiedPackage;

    @ManyToOne
    @JoinColumn(name = "shipment_service_type_id", referencedColumnName = "shipment_service_type_id")
    private ShipmentServiceTypeEntity shipmentServiceType;

//    @OneToMany(mappedBy = "package", targetEntity = PackageImagesEntity.class)
//    private List<PackageImagesEntity> listOfPackageImages;

    @OneToMany
    @JoinColumn(name = "package_id", referencedColumnName = "package_id")
    private List<PackageLabelingEntity> listOfPackageLabeling;

    @OneToMany(mappedBy = "packge", targetEntity = PackageImagesEntity.class)
    private List<PackageImagesEntity> listOfPackageImages;
}
