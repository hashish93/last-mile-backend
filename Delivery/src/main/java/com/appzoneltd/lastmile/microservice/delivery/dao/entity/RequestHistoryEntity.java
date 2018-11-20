package com.appzoneltd.lastmile.microservice.delivery.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "request_history", schema = "lastmile_request")
@NamedQueries({
        @NamedQuery(name = "RequestHistoryEntity.countAll", query = "SELECT COUNT(x) FROM RequestHistoryEntity x")
})
public class RequestHistoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "request_history_id", nullable = false)
    private Long requestHistoryId;

    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "package_id")
    private PackageEntity packageEntity;

    @Column(name = "request_status", length = 50)
    private String requestStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    @Column(name = "request_id", nullable = false)
    private Long requestId;

    @Column(name = "version", nullable = false)
    private Long version;

    @Column(name = "request_type", length = 50)
    private String requestType;


    public RequestHistoryEntity() {
        super();
    }

    public Long getRequestHistoryId() {
        return this.requestHistoryId;
    }

    public void setRequestHistoryId(Long requestHistoryId) {
        this.requestHistoryId = requestHistoryId;
    }

    public String getRequestStatus() {
        return this.requestStatus;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getRequestId() {
        return this.requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public PackageEntity getPackageEntity() {
        return packageEntity;
    }

    public RequestHistoryEntity setPackageEntity(PackageEntity packageEntity) {
        this.packageEntity = packageEntity;
        return this;
    }
}
