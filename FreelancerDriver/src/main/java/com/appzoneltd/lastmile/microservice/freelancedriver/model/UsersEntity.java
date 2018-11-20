/*
 * Created on 4 Jan 2017 ( Time 17:47:57 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package com.appzoneltd.lastmile.microservice.freelancedriver.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

/**
 * Persistent class for entity stored in table "users"
 *
 * @author Telosys Tools Generator
 */

@Entity
@Table(name = "users", schema = "lastmile_authorization_server")
public class UsersEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "username", nullable = false, length = 100)
    private String username;

    @Column(name = "password", nullable = false, length = 100)
    private String password;

    @Column(name = "firstname", nullable = false, length = 100)
    private String firstname;

    @Column(name = "lastname", nullable = false, length = 100)
    private String lastname;

    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

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

    @Column(name = "user_type_id", nullable = false, length = 100)
    private Long userTypeId;

    @Column(name = "personal_photo_id")
    private Long personalPhotoId;

    @ManyToOne
    @JoinColumn(name = "country_code_id", referencedColumnName = "id")
    private CountryCodesEntity countryCodes;

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

    public Long getUserTypeId() {
        return this.userTypeId;
    }

    public void setUserTypeId(Long userTypeId) {
        this.userTypeId = userTypeId;
    }

    public Long getPersonalPhotoId() {
        return personalPhotoId;
    }

    public void setPersonalPhotoId(Long personalPhotoId) {
        this.personalPhotoId = personalPhotoId;
    }

	public CountryCodesEntity getCountryCodes() {
		return countryCodes;
	}

	public void setCountryCodes(CountryCodesEntity countryCodes) {
		this.countryCodes = countryCodes;
	}

}
