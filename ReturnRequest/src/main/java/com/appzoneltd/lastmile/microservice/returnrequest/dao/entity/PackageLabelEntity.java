package com.appzoneltd.lastmile.microservice.returnrequest.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "package_label", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "PackageLabelEntity.countAll", query = "SELECT COUNT(x) FROM PackageLabelEntity x")
})
public class PackageLabelEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "package_label_id", nullable = false)
    private Long packageLabelId;

    @Column(name = "label", nullable = false, length = 50)
    private String label;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "version", nullable = false)
    private Long version;

//    @OneToMany(mappedBy = "packageLabel", targetEntity = PackageLabelingEntity.class)
//    private List<PackageLabelingEntity> listOfPackageLabeling;


    public PackageLabelEntity() {
        super();
    }

    public Long getPackageLabelId() {
        return this.packageLabelId;
    }

    public void setPackageLabelId(Long packageLabelId) {
        this.packageLabelId = packageLabelId;
    }

    public String getLabel() {
        return this.label;
    }

    public void setLabel(String label) {
        this.label = label;
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


}
