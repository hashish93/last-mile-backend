package com.appzoneltd.lastmile.microservice.details.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "driver_verification", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "DriverVerificationEntity.countAll", query = "SELECT COUNT(x) FROM DriverVerificationEntity x")
})
public class DriverVerificationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "verification_code", length = 10)
    private String verificationCode;

    public DriverVerificationEntity() {}

    public DriverVerificationEntity(Long id, Boolean isVerified, String verificationCode) {
        this.id = id;
        this.isVerified = isVerified;
        this.verificationCode = verificationCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVerificationCode() {
        return this.verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

}
