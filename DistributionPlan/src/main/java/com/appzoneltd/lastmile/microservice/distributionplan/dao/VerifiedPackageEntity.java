package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


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

    @OneToOne
    @JoinColumn(name = "package_id", referencedColumnName = "package_id", insertable = false, updatable = false)
    private PackageEntity packge;

    @ManyToOne
    @JoinColumn(name = "package_type_id", referencedColumnName = "package_type_id")
    private PackageTypeEntity packageType;

/*
    @OneToMany(mappedBy = "verifiedPackage", targetEntity = VerifiedPackageImagesEntity.class)
    private List<VerifiedPackageImagesEntity> listOfVerifiedPackageImages;
*/

    public VerifiedPackageEntity() {
        super();
    }

    public Long getPackageId() {
        return this.packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public BigDecimal getActualweight() {
        return this.actualweight;
    }

    public void setActualweight(BigDecimal actualweight) {
        this.actualweight = actualweight;
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

    public PackageEntity getPackage() {
        return this.packge;
    }

    public void setPackage(PackageEntity packge) {
        this.packge = packge;
    }

    public PackageTypeEntity getPackageType() {
        return this.packageType;
    }

    public void setPackageType(PackageTypeEntity packageType) {
        this.packageType = packageType;
    }

/*
    public List<VerifiedPackageImagesEntity> getListOfVerifiedPackageImages() {
        return this.listOfVerifiedPackageImages;
    }

    public void setListOfVerifiedPackageImages(List<VerifiedPackageImagesEntity> listOfVerifiedPackageImages) {
        this.listOfVerifiedPackageImages = listOfVerifiedPackageImages;
    }
*/

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(packageId);
        sb.append("]:");
        sb.append(actualweight);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(created);
        sb.append("|");
        sb.append(version);
        return sb.toString();
    }

}
