package com.appzoneltd.lastmile.microservice.returnrequest.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "verified_package_images", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "VerifiedPackageImagesEntity.countAll", query = "SELECT COUNT(x) FROM VerifiedPackageImagesEntity x")
})
public class VerifiedPackageImagesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private VerifiedPackageImagesEntityKey compositePrimaryKey;

    @Column(name = "version", nullable = false)
    private Long version;


//    @ManyToOne
//    @JoinColumn(name = "package_id", referencedColumnName = "package_id", insertable = false, updatable = false)
//    private VerifiedPackageEntity verifiedPackage;

//    @ManyToOne
//    @JoinColumn(name = "image_id", referencedColumnName = "content_id", insertable = false, updatable = false)
//    private StaticContentEntity staticContent;

    public VerifiedPackageImagesEntity() {
        super();
        this.compositePrimaryKey = new VerifiedPackageImagesEntityKey();
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

}
