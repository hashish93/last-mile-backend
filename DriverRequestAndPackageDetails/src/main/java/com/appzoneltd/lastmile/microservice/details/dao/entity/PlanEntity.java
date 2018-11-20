package com.appzoneltd.lastmile.microservice.details.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "plan", schema = "lastmile_plan")
@NamedQueries({
        @NamedQuery(name = "PlanEntity.countAll", query = "SELECT COUNT(x) FROM PlanEntity x")
})
public class PlanEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "building_id")
    private Long buildingId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    @OneToMany(mappedBy = "plan", targetEntity = PlanOrderEntity.class)
    private List<PlanOrderEntity> listOfPlanOrder;

    public PlanEntity() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<PlanOrderEntity> getListOfPlanOrder() {
        return this.listOfPlanOrder;
    }

    public void setListOfPlanOrder(List<PlanOrderEntity> listOfPlanOrder) {
        this.listOfPlanOrder = listOfPlanOrder;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public PlanEntity setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
        return this;
    }
}
