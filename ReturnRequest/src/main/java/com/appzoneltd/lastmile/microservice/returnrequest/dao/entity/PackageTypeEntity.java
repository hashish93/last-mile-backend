package com.appzoneltd.lastmile.microservice.returnrequest.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "package_type", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "PackageTypeEntity.countAll", query = "SELECT COUNT(x) FROM PackageTypeEntity x")
})
public class PackageTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "package_type_id", nullable = false)
    private Long packageTypeId;

    @Column(name = "packagetype", nullable = false, length = 50)
    private String packagetype;

    @Column(name = "packagedimension", nullable = false, length = 50)
    private String packagedimension;

    @Column(name = "expectedweight", nullable = false)
    private Long expectedweight;

    @Column(name = "description", length = 500)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "version", nullable = false)
    private Long version;

//    @OneToMany(mappedBy = "packageType", targetEntity = PackageEntity.class)
//    private List<PackageEntity> listOfPackage;

    @OneToMany(mappedBy = "packageType", targetEntity = VerifiedPackageEntity.class)
    private List<VerifiedPackageEntity> listOfVerifiedPackage;


    public PackageTypeEntity() {
        super();
    }

    public Long getPackageTypeId() {
        return this.packageTypeId;
    }

    public void setPackageTypeId(Long packageTypeId) {
        this.packageTypeId = packageTypeId;
    }

    public String getPackagetype() {
        return this.packagetype;
    }

    public void setPackagetype(String packagetype) {
        this.packagetype = packagetype;
    }

    public String getPackagedimension() {
        return this.packagedimension;
    }

    public void setPackagedimension(String packagedimension) {
        this.packagedimension = packagedimension;
    }

    public Long getExpectedweight() {
        return this.expectedweight;
    }

    public void setExpectedweight(Long expectedweight) {
        this.expectedweight = expectedweight;
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

    public List<VerifiedPackageEntity> getListOfVerifiedPackage() {
        return this.listOfVerifiedPackage;
    }

    public void setListOfVerifiedPackage(List<VerifiedPackageEntity> listOfVerifiedPackage) {
        this.listOfVerifiedPackage = listOfVerifiedPackage;
    }

}
