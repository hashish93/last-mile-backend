package com.appzoneltd.lastmile.microservice.returnrequest.dao.entity;

import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "plan_order", schema = "lastmile_plan")
@Data
public class PlanOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "active_vehicle_id", nullable = false)
    private Long activeVehicleId;

    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "order_type", nullable = false, length = 100)
    private String orderType;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    @Column(name = "excluded", length = 100)
	private boolean excluded;

}
