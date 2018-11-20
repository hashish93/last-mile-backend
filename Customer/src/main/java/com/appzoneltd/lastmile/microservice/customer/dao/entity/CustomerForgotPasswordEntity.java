package com.appzoneltd.lastmile.microservice.customer.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "customer_forgot_password", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "CustomerForgotPasswordEntity.countAll", query = "SELECT COUNT(x) FROM CustomerForgotPasswordEntity x")
})
public class CustomerForgotPasswordEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "token", nullable = false, length = 50)
    private java.lang.String token;

    @Column(name = "attempts", nullable = false)
    private Integer attempts;

    @Temporal(TemporalType.TIME)
    @Column(name = "created", nullable = false)
    private Date created;

    public CustomerForgotPasswordEntity() {
        super();
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public java.lang.String getToken() {
        return this.token;
    }

    public void setToken(java.lang.String token) {
        this.token = token;
    }

    public Integer getAttempts() {
        return this.attempts;
    }

    public void setAttempts(Integer attempts) {
        this.attempts = attempts;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

}
