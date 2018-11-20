

package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "rejection_reason", schema = "lastmile_plan")
@NamedQueries({
        @NamedQuery(name = "RejectionReasonEntity.countAll", query = "SELECT COUNT(x) FROM RejectionReasonEntity x")
})
public class RejectionReasonEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = true)
    private Long id;

    @Column(name = "reason", nullable = true, length = 200)
    private String reason;


    @OneToMany(mappedBy = "rejectionReason", targetEntity = PlanOrderEntity.class)
    private List<PlanOrderEntity> listOfPlanOrders;

    public RejectionReasonEntity() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return this.id;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getReason() {
        return this.reason;
    }

    public void setListOfPlanOrders(List<PlanOrderEntity> listOfPlanOrders) {
        this.listOfPlanOrders = listOfPlanOrders;
    }

    public List<PlanOrderEntity> getListOfPlanOrders() {
        return this.listOfPlanOrders;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(id);
        sb.append("]:");
        sb.append(reason);
        return sb.toString();
    }

}
