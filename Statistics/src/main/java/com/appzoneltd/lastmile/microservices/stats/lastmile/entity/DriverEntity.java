package com.appzoneltd.lastmile.microservices.stats.lastmile.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "driver", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "DriverEntity.countAll", query = "SELECT COUNT(x) FROM DriverEntity x")
})
public class DriverEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rating")
	private BigDecimal rating;

    @ManyToOne
	@JoinColumn(name = "id", referencedColumnName = "user_id", insertable = false, updatable = false)
	private UserEntity user;
    

    public DriverEntity() {
        super();
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
