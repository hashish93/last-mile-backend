package com.appzoneltd.lastmile.microservices.stats.lastmile.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "freelancer_driver", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "FreelancerDriverEntity.countAll", query = "SELECT COUNT(x) FROM FreelancerDriverEntity x")})
public class FreelancerDriverEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rating")
    private BigDecimal rating;
    @Column(name = "status")
    private String status;

    public FreelancerDriverEntity() {
        super();
    }

    public String getStatus() {
        return status;
    }

    public FreelancerDriverEntity setStatus(String status) {
        this.status = status;
        return this;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getRating() {
        return rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

}
