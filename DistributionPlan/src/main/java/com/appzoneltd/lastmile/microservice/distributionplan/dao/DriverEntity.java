package com.appzoneltd.lastmile.microservice.distributionplan.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


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

    @Column(name = "national_id", length = 50)
    private String nationalId;

    @Temporal(TemporalType.DATE)
    @Column(name = "driving_license_exp_date")
    private Date drivingLicenseExpDate;

    @Column(name = "driving_license_id", length = 50)
    private String drivingLicenseId;

    @Column(name = "rating")
    private BigDecimal rating;

/*
    @ManyToOne
    @JoinColumn(name = "driving_license_type_id", referencedColumnName = "driving_license_type_id")
    private DrivingLicenseTypeEntity drivingLicenseType;
*/

    @OneToMany(mappedBy = "driver", targetEntity = ActiveVehicleEntity.class)
    private List<ActiveVehicleEntity> listOfActiveVehicle;


    public DriverEntity() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationalId() {
        return this.nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Date getDrivingLicenseExpDate() {
        return this.drivingLicenseExpDate;
    }

    public void setDrivingLicenseExpDate(Date drivingLicenseExpDate) {
        this.drivingLicenseExpDate = drivingLicenseExpDate;
    }

    public String getDrivingLicenseId() {
        return this.drivingLicenseId;
    }

    public void setDrivingLicenseId(String drivingLicenseId) {
        this.drivingLicenseId = drivingLicenseId;
    }

    public BigDecimal getRating() {
        return this.rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public List<ActiveVehicleEntity> getListOfActiveVehicle() {
        return this.listOfActiveVehicle;
    }

    public void setListOfActiveVehicle(List<ActiveVehicleEntity> listOfActiveVehicle) {
        this.listOfActiveVehicle = listOfActiveVehicle;
    }

}
