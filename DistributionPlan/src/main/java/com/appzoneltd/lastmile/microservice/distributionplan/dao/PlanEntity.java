package com.appzoneltd.lastmile.microservice.distributionplan.dao;

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
    
	@Column(name = "building_id", nullable = false)
	private Long buildingId ;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created = new Date();

    @OneToMany(mappedBy = "plan", targetEntity = PlanOrderEntity.class)
    private List<PlanOrderEntity> listOfPlanOrders;

    public PlanEntity() {
        super();
    }

    public PlanEntity(Long id, Date created) {
        this.id = id;
        this.created = created;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBuildingId() {
		return buildingId;
	}

	public void setBuildingId(Long buildingId) {
		this.buildingId = buildingId;
	}

	public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public List<PlanOrderEntity> getListOfPlanOrders() {
        return this.listOfPlanOrders;
    }

    public void setListOfPlanOrders(List<PlanOrderEntity> listOfPlanOrders) {
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
