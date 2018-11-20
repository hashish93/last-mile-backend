package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "work_shift", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "WorkShiftEntity.countAll", query = "SELECT COUNT(x) FROM WorkShiftEntity x")
})
public class WorkShiftEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_from", nullable = false)
    private Date dateFrom;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_to", nullable = false)
    private Date dateTo;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "version", nullable = false)
    private Long version;

    @OneToMany(mappedBy = "workShift", targetEntity = ActiveVehicleEntity.class)
    private List<ActiveVehicleEntity> listOfActiveVehicle;

    public WorkShiftEntity() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateFrom() {
        return this.dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return this.dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
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

    public List<ActiveVehicleEntity> getListOfActiveVehicle() {
        return this.listOfActiveVehicle;
    }

    public void setListOfActiveVehicle(List<ActiveVehicleEntity> listOfActiveVehicle) {
        this.listOfActiveVehicle = listOfActiveVehicle;
    }
}
