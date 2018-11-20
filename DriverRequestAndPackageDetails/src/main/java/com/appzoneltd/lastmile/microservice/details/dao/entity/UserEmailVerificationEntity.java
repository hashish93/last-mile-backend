package com.appzoneltd.lastmile.microservice.details.dao.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_email_verification", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "UserEmailVerificationEntity.countAll", query = "SELECT COUNT(x) FROM UserEmailVerificationEntity x")
})
public class UserEmailVerificationEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "verification_code", length = 50)
    private String verificationCode;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "user_id", insertable = false, updatable = false)
    private UsersEntity users;

    public UserEmailVerificationEntity() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getIsVerified() {
        return this.isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public String getVerificationCode() {
        return this.verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }

    public UsersEntity getUsers() {
        return this.users;
    }

    public void setUsers(UsersEntity users) {
        this.users = users;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
