package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by hashish on 4/9/2017.
 */

@Entity
@Table(name = "plan_tmp", schema = "lastmile_plan")
@NamedQueries({
        @NamedQuery(name = "PlanEntityTmp.countAll", query = "SELECT COUNT(x) FROM PlanEntityTmp x")
})
public class PlanEntityTmp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created = new Date();

    @OneToMany(mappedBy = "plan", targetEntity = PlanOrderTmpEntity.class)
    private List<PlanOrderTmpEntity> listOfPlanOrders;

    public PlanEntityTmp() {
        super();
    }

    public PlanEntityTmp(Long id, Date created) {
        this.id = id;
        this.created = created;
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

    public List<PlanOrderTmpEntity> getListOfPlanOrders() {
        return this.listOfPlanOrders;
    }

    public void setListOfPlanOrders(List<PlanOrderTmpEntity> listOfPlanOrders) {
        this.listOfPlanOrders = listOfPlanOrders;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(id);
        sb.append("]:");
        sb.append(created);
        return sb.toString();
    }

}
