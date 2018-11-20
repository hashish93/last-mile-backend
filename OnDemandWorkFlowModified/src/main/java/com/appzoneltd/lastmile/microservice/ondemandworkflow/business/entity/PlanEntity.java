package com.appzoneltd.lastmile.microservice.ondemandworkflow.business.entity;

import java.io.Serializable;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;

@Data
@Entity
@Table(name = "plan", schema = "lastmile_plan")
public class PlanEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id", nullable = false)
	private Long id;

	@Column(name = "building_id", nullable = false)
	private Long buildingId;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created")
	private Date created;

	@OneToMany(mappedBy = "plan", targetEntity = PlanOrderEntity.class)
	private List<PlanOrderEntity> listOfPlanOrder;
}
