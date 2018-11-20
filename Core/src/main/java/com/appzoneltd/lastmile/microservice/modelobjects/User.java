package com.appzoneltd.lastmile.microservice.modelobjects;

import com.appzoneltd.lastmile.microservice.enums.EntityStatus;
import com.appzoneltd.lastmile.microservice.enums.UserType;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

public abstract class User implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -2810737228747824276L;

    private Long userId;


    private String username;

    private String password;
    @NotEmpty(message = "user.firstname.required")
    @Pattern(regexp = "^$|^[a-zA-Z\u0621-\u064A\u0660-\u0669][a-zA-Z\u0621-\u064A\u0660-\u0669 -]*[a-zA-Z\u0621-\u064A\u0660-\u0669]*", message = "user.firstname.errorformat")
    private String firstName;
    @NotEmpty(message = "user.secondname.required")
    @Pattern(regexp = "^$|^[a-zA-Z\u0621-\u064A\u0660-\u0669][a-zA-Z\u0621-\u064A\u0660-\u0669 -]*[a-zA-Z\u0621-\u064A\u0660-\u0669]*", message = "user.secondname.errorformat")
    private String lastName;


    private Long countryCodeId;
    private Long countryCode;
    
    @NotEmpty(message = "user.mobile.required")
    @Pattern(regexp = "^[0-9+]?[0-9]*$", message = "user.phone.errorformat")
    private String phone;

    @NotBlank(message = "employee.email.required")
    @NotEmpty(message = "user.email.required")
    @Pattern(regexp = "^[a-zA-Z]+[a-zA-Z0-9.]+@[a-zA-Z]+.[a-zA-Z]{2,5}$", message = "customer.email.errorformat")
    private String email;

    private Long personalPhoto;

    private EntityStatus entityStatus;

    private UserType userType;

    private Long userTypeId;

    private Boolean enabled;

    private Date created;

    @Size(max = 500, message = "employee.description.size")
    private String description;

    private Long version;

    public User() {

    }

    public User(Long userId, String fullName, String password, String username, String lastName, String phone,
                String email, Long personalPhoto, EntityStatus entityStatus, UserType userType, Boolean enabled,
                Date created, String description, Long version) {
        this.userId = userId;
        this.username = fullName;
        this.password = password;
        this.firstName = username;
        this.lastName = lastName;
        this.phone = phone;
        this.email = email;
        this.personalPhoto = personalPhoto;
        this.entityStatus = entityStatus;
        this.userType = userType;
        this.enabled = enabled;
        this.created = created;
        this.description = description;
        this.version = version;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPersonalPhoto() {
        return personalPhoto;
    }

    public void setPersonalPhoto(Long personalPhoto) {
        this.personalPhoto = personalPhoto;
    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Long getUserTypeId() {return userTypeId;}

    public void setUserTypeId(Long userTypeId) {this.userTypeId = userTypeId;}

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getCountryCodeId() {
        return countryCodeId;
    }

    public void setCountryCodeId(Long countryCodeId) {
        this.countryCodeId = countryCodeId;
    }

    public Long getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Long countryCode) {
        this.countryCode = countryCode;
    }
}
