package com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "pickup_request_type", schema = "lastmile_request")
@NamedQueries({
        @NamedQuery(name = "PickupRequestTypeEntity.countAll", query = "SELECT COUNT(x) FROM PickupRequestTypeEntity x")
})
public class PickupRequestTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "pickup_request_type_id", nullable = false)
    private Long pickupRequestTypeId;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    @Column(name = "version", nullable = false)
    private Long version;

    @OneToMany(mappedBy = "pickupRequestType", targetEntity = PickupRequestEntity.class)
    private List<PickupRequestEntity> listOfPickupRequest;

    public PickupRequestTypeEntity() {
        super();
    }

    public Long getPickupRequestTypeId() {
        return this.pickupRequestTypeId;
    }

    public void setPickupRequestTypeId(Long pickupRequestTypeId) {
        this.pickupRequestTypeId = pickupRequestTypeId;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
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

    public List<PickupRequestEntity> getListOfPickupRequest() {
        return this.listOfPickupRequest;
    }

    public void setListOfPickupRequest(List<PickupRequestEntity> listOfPickupRequest) {
        this.listOfPickupRequest = listOfPickupRequest;
    }
}
