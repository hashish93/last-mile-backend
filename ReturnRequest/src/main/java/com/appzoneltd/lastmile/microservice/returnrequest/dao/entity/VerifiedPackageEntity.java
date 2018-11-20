package com.appzoneltd.lastmile.microservice.returnrequest.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "verified_package", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "VerifiedPackageEntity.countAll", query = "SELECT COUNT(x) FROM VerifiedPackageEntity x")
})
public class VerifiedPackageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "package_id", nullable = false)
    private Long packageId;

    @Column(name = "actualweight", nullable = false)
    private BigDecimal actualweight;

    @Column(name = "description", length = 500)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "package_value")
    private String packageValue;

//    @ManyToOne
//    @JoinColumn(name = "package_id", referencedColumnName = "package_id", insertable = false, updatable = false)
//    private PackageEntity package;

    @ManyToOne
    @JoinColumn(name = "package_type_id", referencedColumnName = "package_type_id")
    private PackageTypeEntity packageType;

    @OneToMany
    @JoinColumn(name = "package_id", referencedColumnName = "package_id")
    private List<VerifiedPackageImagesEntity> listOfVerifiedPackageImages;


    public VerifiedPackageEntity() {
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getPackageId() {
        return packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public BigDecimal getActualweight() {
        return actualweight;
    }

    public void setActualweight(BigDecimal actualweight) {
        this.actualweight = actualweight;
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

    public PackageTypeEntity getPackageType() {
        return packageType;
    }

    public void setPackageType(PackageTypeEntity packageType) {
        this.packageType = packageType;
    }

    public List<VerifiedPackageImagesEntity> getListOfVerifiedPackageImages() {
        return listOfVerifiedPackageImages;
    }

    public void setListOfVerifiedPackageImages(List<VerifiedPackageImagesEntity> listOfVerifiedPackageImages) {
        this.listOfVerifiedPackageImages = listOfVerifiedPackageImages;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }
}
