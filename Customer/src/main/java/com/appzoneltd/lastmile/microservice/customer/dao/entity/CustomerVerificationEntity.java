package com.appzoneltd.lastmile.microservice.customer.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "customer_verification", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "CustomerVerificationEntity.countAll", query = "SELECT COUNT(x) FROM CustomerVerificationEntity x")
})
public class CustomerVerificationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "verification_code", length = 10)
    private String verificationCode;


    public CustomerVerificationEntity() {
        super();
    }

    public CustomerVerificationEntity(Long id, Boolean isVerified, String verificationCode) {
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
