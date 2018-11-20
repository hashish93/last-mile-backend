package com.appzoneltd.lastmile.microservice.delivery.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

@Entity
@Table(name = "package_labeling", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "PackageLabelingEntity.countAll", query = "SELECT COUNT(x) FROM PackageLabelingEntity x")
})
public class PackageLabelingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private PackageLabelingEntityKey compositePrimaryKey;

    @Column(name = "version", nullable = false)
    private Long version;

//    @ManyToOne
//    @JoinColumn(name = "package_id", referencedColumnName = "package_id", insertable = false, updatable = false)
//    private PackageEntity package;

    @ManyToOne
    @JoinColumn(name = "package_label_id", referencedColumnName = "package_label_id", insertable = false, updatable = false)
    private PackageLabelEntity packageLabel;


    public PackageLabelingEntity() {
        super();
        this.compositePrimaryKey = new PackageLabelingEntityKey();
    }

    public Long getPackageId() {
        return this.compositePrimaryKey.getPackageId();
    }

    public void setPackageId(Long packageId) {
        this.compositePrimaryKey.setPackageId(packageId);
    }

    public Long getPackageLabelId() {
        return this.compositePrimaryKey.getPackageLabelId();
    }

    public void setPackageLabelId(Long packageLabelId) {
        this.compositePrimaryKey.setPackageLabelId(packageLabelId);
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public PackageLabelEntity getPackageLabel() {
        return this.packageLabel;
    }

    public void setPackageLabel(PackageLabelEntity packageLabel) {
        this.packageLabel = packageLabel;
    }

}
