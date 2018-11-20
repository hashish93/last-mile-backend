package com.appzoneltd.lastmile.microservice.delivery.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class VerifiedPackageImagesEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "package_id", nullable = false)
    private Long packageId;

    @Column(name = "image_id", nullable = false)
    private Long imageId;


    public VerifiedPackageImagesEntityKey() {
        super();
    }

    public VerifiedPackageImagesEntityKey(Long packageId, Long imageId) {
        super();
        this.packageId = packageId;
        this.imageId = imageId;
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

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }
}
