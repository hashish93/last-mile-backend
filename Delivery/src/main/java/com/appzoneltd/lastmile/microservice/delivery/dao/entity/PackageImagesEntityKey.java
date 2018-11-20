package com.appzoneltd.lastmile.microservice.delivery.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PackageImagesEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "package_id", nullable = false)
    private Long packageId;

    @Column(name = "image_id", nullable = false)
    private Long imageId;

    public PackageImagesEntityKey() {
        super();
    }

    public PackageImagesEntityKey(Long packageId, Long imageId) {
        super();
        this.packageId = packageId;
        this.imageId = imageId;
    }

    public Long getPackageId() {
        return this.packageId;
    }

    public void setPackageId(Long value) {
        this.packageId = value;
    }

    public Long getImageId() {
        return this.imageId;
    }

    public void setImageId(Long value) {
        this.imageId = value;
    }

}
