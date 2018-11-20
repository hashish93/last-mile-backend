package com.appzoneltd.lastmile.microservice.customer.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "users", schema = "lastmile_authorization_server")
@NamedQueries({
        @NamedQuery(name = "UsersEntity.countAll", query = "SELECT COUNT(x) FROM UsersEntity x")
})
public class UsersEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "username", length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "firstname", nullable = false, length = 100)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;


    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Column(name = "country_code_id", nullable = false, length = 50)
    private Long CountryCodeId;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "enabled", nullable = false)
    private Boolean enabled;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "version", nullable = false)
    private Long version;

    @ManyToOne
    @JoinColumn(name = "user_type_id", referencedColumnName = "id", insertable = true, updatable = true)
    private UserTypeEntity userTypeEntity;

    @ManyToOne
    @JoinColumn(name = "personal_photo_id", referencedColumnName = "content_id", updatable = true, nullable = true)
    private StaticContentEntity staticContent;

    public UsersEntity() {
        super();
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getEnabled() {
        return this.enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public UserTypeEntity getUserTypeEntity() {return userTypeEntity;}

    public void setUserTypeEntity(UserTypeEntity userTypeEntity) {this.userTypeEntity = userTypeEntity;}

    public StaticContentEntity getStaticContent() {
        return this.staticContent;
    }

    public void setStaticContent(StaticContentEntity staticContent) {
        this.staticContent = staticContent;
    }

    public Long getCountryCodeId() {
        return CountryCodeId;
    }

    public void setCountryCodeId(Long countryCodeId) {
        CountryCodeId = countryCodeId;
    }
}
