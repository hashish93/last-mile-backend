package com.appzoneltd.lastmile.microservice.delivery.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "package_images", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "PackageImagesEntity.countAll", query = "SELECT COUNT(x) FROM PackageImagesEntity x")
})
public class PackageImagesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PackageImagesEntityKey compositePrimaryKey;

    @Column(name = "version", nullable = false)
    private Long version;

    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "package_id", insertable = false, updatable = false)
    private PackageEntity packge;

    public PackageImagesEntity() {
        super();
        this.compositePrimaryKey = new PackageImagesEntityKey();
    }

    public PackageImagesEntity(Long packageId, Long imageId) {
        this.compositePrimaryKey.setPackageId(packageId);
        this.compositePrimaryKey.setImageId(imageId);
    }

    public Long getPackageId() {
        return this.compositePrimaryKey.getPackageId();
    }

    public void setPackageId(Long packageId) {
        this.compositePrimaryKey.setPackageId(packageId);
    }

    public Long getImageId() {
        return this.compositePrimaryKey.getImageId();
    }

    public void setImageId(Long imageId) {
        this.compositePrimaryKey.setImageId(imageId);
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

}
