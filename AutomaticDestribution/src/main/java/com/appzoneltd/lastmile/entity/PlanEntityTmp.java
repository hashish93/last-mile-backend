//package com.appzoneltd.lastmile.entity;
//
//import javax.persistence.*;
//import java.io.Serializable;
//import java.util.Date;
//
//@Entity
//@Table(name = "plan_tmp", schema = "lastmile_plan")
//public class PlanEntityTmp implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@Column(name = "id", nullable = false)
//	private Long id;
//
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name = "created", nullable = false, insertable = false)
//	private Date created;
//
//	public PlanEntityTmp() {
//
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public Long getId() {
//		return this.id;
//	}
//
//	public Date getCreated() {
//		return created;
//	}
//
//	public void setCreated(Date created) {
//		this.created = created;
//	}
//
//
//	public String toString() {
//		StringBuffer sb = new StringBuffer();
//		sb.append("[");
//		sb.append(id);
//		sb.append("]:");
//		return sb.toString();
//	}
//
//}
