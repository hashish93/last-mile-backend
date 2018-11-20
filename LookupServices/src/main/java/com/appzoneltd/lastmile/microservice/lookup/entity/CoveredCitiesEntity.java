package com.appzoneltd.lastmile.microservice.lookup.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="drive_in_city", schema="lastmile_core" )
public class CoveredCitiesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
//    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="city_id", nullable=false)
    private Integer    id           ;

    @ManyToOne
    @JoinColumn(name="city_id", referencedColumnName="city_id" , insertable=false , updatable = false)
    private CityEntity cityEntity        ;

}
