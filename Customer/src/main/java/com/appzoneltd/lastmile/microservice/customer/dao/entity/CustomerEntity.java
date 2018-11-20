package com.appzoneltd.lastmile.microservice.customer.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "customer", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "CustomerEntity.countAll", query = "SELECT COUNT(x) FROM CustomerEntity x")
})
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "gender", length = 50)
    private String gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate")
    private Date birthdate;

    @ManyToOne
    @JoinColumn(name = "country_code_id", referencedColumnName = "id", updatable = true, nullable = true)
    private CountryCodesEntity countryCodes;

    @OneToOne()
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    private CustomerVerificationEntity customerVerification;

    @ManyToOne
    @JoinColumn(name = "country_id", referencedColumnName = "country_id", updatable = true, nullable = true)
    private CountryEntity country;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id", updatable = true, nullable = true)
    private CityEntity city;

    @OneToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "user_id")
    private UsersEntity usersEntity;

    public CustomerEntity() {
        super();
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return this.birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public CountryCodesEntity getCountryCodes() {
        return this.countryCodes;
    }

    public void setCountryCodes(CountryCodesEntity countryCodes) {
        this.countryCodes = countryCodes;
    }

    public CountryEntity getCountry() {
        return this.country;
    }

    public void setCountry(CountryEntity country) {
        this.country = country;
    }

    public CityEntity getCity() {
        return this.city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public CustomerVerificationEntity getCustomerVerification() {
        return customerVerification;
    }

    public void setCustomerVerification(CustomerVerificationEntity customerVerification) {
        this.customerVerification = customerVerification;
    }

    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
    }

    @Override
    public String toString() {
        return "CustomerEntity [customerId=" + customerId + ", gender=" + gender + ", birthdate=" + birthdate
                + ", countryCodes=" + countryCodes + ", customerVerification=" + customerVerification + ", country="
                + country + ", city=" + city + ", usersEntity=" + usersEntity + "]";
    }


}
