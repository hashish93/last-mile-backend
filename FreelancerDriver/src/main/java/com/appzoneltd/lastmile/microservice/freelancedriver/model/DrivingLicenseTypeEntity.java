package com.appzoneltd.lastmile.microservice.freelancedriver.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "driving_license_type", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "DrivingLicenseTypeEntity.countAll", query = "SELECT COUNT(x) FROM DrivingLicenseTypeEntity x")
})
public class DrivingLicenseTypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "driving_license_type_id", nullable = false)
    private Long drivingLicenseTypeId;

    @Column(name = "license_type", nullable = false, length = 50)
    private String licenseType;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getDrivingLicenseTypeId() {
        return drivingLicenseTypeId;
    }

    public DrivingLicenseTypeEntity setDrivingLicenseTypeId(Long drivingLicenseTypeId) {
        this.drivingLicenseTypeId = drivingLicenseTypeId;
        return this;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public DrivingLicenseTypeEntity setLicenseType(String licenseType) {
        this.licenseType = licenseType;
        return this;
    }
}
