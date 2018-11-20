package com.appzoneltd.lastmile.microservice.freelancedriver.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "driver_forgot_password", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "DriverForgotPasswordEntity.countAll", query = "SELECT COUNT(x) FROM DriverForgotPasswordEntity x")
})
public class DriverForgotPasswordEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "driver_id", nullable = false)
    private Long driverId;

    @Column(name = "token", nullable = false, length = 50)
    private String token;

    @Column(name = "attempts", nullable = false)
    private Integer attempts;

    @Temporal(TemporalType.TIME)
    @Column(name = "created", nullable = false)
    private Date created;

    @ManyToOne
    @JoinColumn(name = "driver_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private UsersEntity users;

    public DriverForgotPasswordEntity() {
        super();
    }

    public Long getDriverId() {
        return this.driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String token) {
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

    public UsersEntity getUsers() {
        return this.users;
    }

    public void setUsers(UsersEntity users) {
        this.users = users;
    }

}
