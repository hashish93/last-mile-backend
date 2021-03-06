package com.appzoneltd.lastmile.microservice.deliveryworkflow.delegate.notification;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.RequestHistoryEntity;
import com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity.ShipmentServiceTypeEntity;

@Entity
@Table(name = "package", schema = "lastmile_core")
@NamedQueries({@NamedQuery(name = "InvoicePackageEntity.countAll", query = "SELECT COUNT(x) FROM InvoicePackageEntity x")})
public class InvoicePackageEntity implements Serializable {

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

//    @ManyToOne
//    @JoinColumn(name = "package_type_id", referencedColumnName = "package_type_id")
//    private PackageTypeEntity packageType;

//    @ManyToMany(targetEntity = StaticContentEntity.class)
//    @JoinTable(name = "package_images", schema = "lastmile_core", joinColumns = @JoinColumn(name = "package_id", referencedColumnName = "package_id"), inverseJoinColumns = @JoinColumn(name = "image_id", referencedColumnName = "content_id"))
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private Set<StaticContentEntity> listOfStaticContent;

//    @ManyToMany(targetEntity = PackageLabelEntity.class)
//    @JoinTable(name = "package_labeling", schema = "lastmile_core", joinColumns = @JoinColumn(name = "package_id", referencedColumnName = "package_id"), inverseJoinColumns = @JoinColumn(name = "package_label_id", referencedColumnName = "package_label_id"))
//    @LazyCollection(LazyCollectionOption.FALSE)
//    private Set<PackageLabelEntity> listOfPackageLabel;

//    @LazyCollection(LazyCollectionOption.FALSE)
//    @OneToMany(mappedBy = "packageEntity", targetEntity = RequestHistoryEntity.class)
//    private List<RequestHistoryEntity> listOfRequestHistory;

    @ManyToOne
    @JoinColumn(name = "shipment_service_type_id", referencedColumnName = "shipment_service_type_id")
    private ShipmentServiceTypeEntity shipmentServiceType;

    @OneToOne
    @JoinColumn(name = "package_id", referencedColumnName = "package_id")
    private VerifiedPackageEntity verifiedPackage;


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

//    public PackageTypeEntity getPackageType() {
//        return this.packageType;
//    }
//
//    public void setPackageType(PackageTypeEntity packageType) {
//        this.packageType = packageType;
//    }
//
//    public List<RequestHistoryEntity> getListOfRequestHistory() {
//        return listOfRequestHistory;
//    }
//
//    public void setListOfRequestHistory(List<RequestHistoryEntity> listOfRequestHistory) {
//        this.listOfRequestHistory = listOfRequestHistory;
//    }
//
//    public Set<StaticContentEntity> getListOfStaticContent() {
//        return listOfStaticContent;
//    }
//
//    public void setListOfStaticContent(Set<StaticContentEntity> listOfStaticContent) {
//        this.listOfStaticContent = listOfStaticContent;
//    }
//
//    public Set<PackageLabelEntity> getListOfPackageLabel() {
//        return listOfPackageLabel;
//    }
//
//    public void setListOfPackageLabel(Set<PackageLabelEntity> listOfPackageLabel) {
//        this.listOfPackageLabel = listOfPackageLabel;
//    }

    public ShipmentServiceTypeEntity getShipmentServiceType() {
        return shipmentServiceType;
    }

    public void setShipmentServiceType(ShipmentServiceTypeEntity shipmentServiceType) {
        this.shipmentServiceType = shipmentServiceType;
    }

    public VerifiedPackageEntity getVerifiedPackage() {
        return verifiedPackage;
    }

    public void setVerifiedPackage(VerifiedPackageEntity verifiedPackage) {
        this.verifiedPackage = verifiedPackage;
    }
}
