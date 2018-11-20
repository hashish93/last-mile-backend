package com.appzoneltd.lastmile.microservice.details.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "rejection_reason", schema = "lastmile_plan")
@NamedQueries({
        @NamedQuery(name = "RejectionReasonEntity.countAll", query = "SELECT COUNT(x) FROM RejectionReasonEntity x")
})
public class RejectionReasonEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "reason", nullable = false, length = 200)
    private String reason;

    @OneToMany(mappedBy = "rejectionReason", targetEntity = PlanOrderEntity.class)
    private List<PlanOrderEntity> listOfPlanOrder;

    public RejectionReasonEntity() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<PlanOrderEntity> getListOfPlanOrder() {
        return this.listOfPlanOrder;
    }

    public void setListOfPlanOrder(List<PlanOrderEntity> listOfPlanOrder) {
        this.listOfPlanOrder = listOfPlanOrder;
    }

}
