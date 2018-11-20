package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "package", schema = "lastmile_core")

@NamedQueries({
        @NamedQuery(name = "PackageEntity.countAll", query = "SELECT COUNT(x) FROM PackageEntity x")
})
public class PackageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "package_id", nullable = false)
    private Long packageId;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "actualweight", nullable = false)
    private BigDecimal actualweight;

    @Column(name = "status", length = 50)
    private String status;

    @Column(name = "description", length = 500)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "tracking_number", length = 100)
    private String trackingNumber;

    @OneToMany(mappedBy = "packageEntity", targetEntity = PlanOrderEntity.class)
    private List<PlanOrderEntity> listOfPlanOrders;

    @ManyToOne
    @JoinColumn(name = "package_type_id", referencedColumnName = "package_type_id")
    private PackageTypeEntity packageType;

    @OneToOne(mappedBy = "packge", targetEntity = VerifiedPackageEntity.class)
    private VerifiedPackageEntity verifiedPackage;

/*
    @ManyToOne
    @JoinColumn(name = "shipment_service_type_id", referencedColumnName = "shipment_service_type_id")
    private ShipmentServiceTypeEntity shipmentServiceType;
*/

/*
    @OneToMany(mappedBy = "package", targetEntity = PackageImagesEntity.class)
    private List<PackageImagesEntity> listOfPackageImages;
*/

/*
    @OneToMany(mappedBy = "package", targetEntity = PackageLabelingEntity.class)
    private List<PackageLabelingEntity> listOfPackageLabeling;
*/

    public PackageEntity() {
        super();
    }

    public Long getPackageId() {
        return this.packageId;
    }

    public void setPackageId(Long packageId) {
        this.packageId = packageId;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public BigDecimal getActualweight() {
        return this.actualweight;
    }

    public void setActualweight(BigDecimal actualweight) {
        this.actualweight = actualweight;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getTrackingNumber() {
        return this.trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public List<PlanOrderEntity> getListOfPlanOrders() {
        return this.listOfPlanOrders;
    }

    public void setListOfPlanOrders(List<PlanOrderEntity> listOfPlanOrders) {
        this.listOfPlanOrders = listOfPlanOrders;
    }

    public PackageTypeEntity getPackageType() {
        return this.packageType;
    }

    public void setPackageType(PackageTypeEntity packageType) {
        this.packageType = packageType;
    }

    public VerifiedPackageEntity getVerifiedPackage() {
        return verifiedPackage;
    }

    public void setVerifiedPackage(VerifiedPackageEntity verifiedPackage) {
        this.verifiedPackage = verifiedPackage;
    }

    /*
    public ShipmentServiceTypeEntity getShipmentServiceType() {
        return this.shipmentServiceType;
    }

    public void setShipmentServiceType(ShipmentServiceTypeEntity shipmentServiceType) {
        this.shipmentServiceType = shipmentServiceType;
    }
*/

/*
    public List<PackageImagesEntity> getListOfPackageImages() {
        return this.listOfPackageImages;
    }

    public void setListOfPackageImages(List<PackageImagesEntity> listOfPackageImages) {
        this.listOfPackageImages = listOfPackageImages;
    }
*/

/*
    public List<PackageLabelingEntity> getListOfPackageLabeling() {
        return this.listOfPackageLabeling;
    }

    public void setListOfPackageLabeling(List<PackageLabelingEntity> listOfPackageLabeling) {
        this.listOfPackageLabeling = listOfPackageLabeling;
    }
*/

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(packageId);
        sb.append("]:");
        sb.append(nickname);
        sb.append("|");
        sb.append(actualweight);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(created);
        sb.append("|");
        sb.append(version);
        sb.append("|");
        sb.append(trackingNumber);
        return sb.toString();
    }

}
