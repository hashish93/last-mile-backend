/*
 * Created on 27 Dec 2016 ( Time 16:27:39 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "package", schema = "lastmile_core")
@NamedQueries({@NamedQuery(name = "PackageEntity.countAll", query = "SELECT COUNT(x) FROM PackageEntity x")})
public class PackageEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "package_id", nullable = false)
    private Long packageId;

    @Column(name = "nickname", length = 50)
    private String nickname;

    @Column(name = "actualweight", nullable = false)
    private BigDecimal actualweight;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "description", length = 500)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "tracking_number", nullable = false)
    private String trackingNumber;

    @Column(name = "package_value")
    private String packageValue;

    @ManyToOne
    @JoinColumn(name = "shipment_service_type_id", referencedColumnName = "shipment_service_type_id")
    private ShipmentServiceTypeEntity shipmentServiceType;

    @OneToMany(mappedBy = "packge", targetEntity = PackageImagesEntity.class)
    private List<PackageImagesEntity> listOfPackageImages;

    @ManyToOne
    @JoinColumn(name = "package_type_id", referencedColumnName = "package_type_id")
    private PackageTypeEntity packageType;

    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "packageEntity", targetEntity = RequestHistoryEntity.class)
    private List<RequestHistoryEntity> listOfRequestHistory;


    public PackageEntity() {
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public PackageTypeEntity getPackageType() {
        return this.packageType;
    }

    public void setPackageType(PackageTypeEntity packageType) {
        this.packageType = packageType;
    }

    public List<RequestHistoryEntity> getListOfRequestHistory() {
        return listOfRequestHistory;
    }

    public void setListOfRequestHistory(List<RequestHistoryEntity> listOfRequestHistory) {
        this.listOfRequestHistory = listOfRequestHistory;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public ShipmentServiceTypeEntity getShipmentServiceType() {
        return shipmentServiceType;
    }

    public void setShipmentServiceType(ShipmentServiceTypeEntity shipmentServiceType) {
        this.shipmentServiceType = shipmentServiceType;
    }

    public List<PackageImagesEntity> getListOfPackageImages() {
        return listOfPackageImages;
    }

    public void setListOfPackageImages(List<PackageImagesEntity> listOfPackageImages) {
        this.listOfPackageImages = listOfPackageImages;
    }

    public String getPackageValue() {
        return packageValue;
    }

    public void setPackageValue(String packageValue) {
        this.packageValue = packageValue;
    }
}