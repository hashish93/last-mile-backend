package com.appzoneltd.lastmile.microservice.returnrequest.dao.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PackageLabelingEntityKey implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "package_id", nullable = false)
    private Long packageId;

    @Column(name = "package_label_id", nullable = false)
    private Long packageLabelId;

    public PackageLabelingEntityKey() {
        super();
    }

    public PackageLabelingEntityKey(Long packageId, Long packageLabelId) {
        super();
        this.packageId = packageId;
        this.packageLabelId = packageLabelId;
    }

    public Long getPackageId() {
        return this.packageId;
    }

    public void setPackageId(Long value) {
        this.packageId = value;
    }

    public Long getPackageLabelId() {
        return this.packageLabelId;
    }

    public void setPackageLabelId(Long value) {
        this.packageLabelId = value;
    }
    
}
