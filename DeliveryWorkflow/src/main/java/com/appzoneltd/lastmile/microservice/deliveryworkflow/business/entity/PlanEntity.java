package com.appzoneltd.lastmile.microservice.deliveryworkflow.business.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "plan", schema = "lastmile_plan")
@Data
public class PlanEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created = new Date();

    @OneToMany(mappedBy = "plan", targetEntity = PlanOrderEntity.class)
    private List<PlanOrderEntity> listOfPlanOrders;

    @Column(name = "building_id", nullable = false)
    private Long buildingId;
}
