package com.appzoneltd.lastmile.microservice.details.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "customer", schema = "lastmile_core")
@NamedQueries({@NamedQuery(name = "CustomerEntity.countAll", query = "SELECT COUNT(x) FROM CustomerEntity x")})
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @Column(name = "gender", nullable = false, length = 50)
    private String gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

    @OneToMany(mappedBy = "requester", targetEntity = PickupRequestEntity.class)
    private List<PickupRequestEntity> listOfRequesterPickupRequest;

    @OneToMany(mappedBy = "recipient", targetEntity = PickupRequestEntity.class)
    private List<PickupRequestEntity> listOfRecipientPickupRequest;

    @ManyToOne
    @JoinColumn(name = "country_code_id", referencedColumnName = "id")
    private CountryCodesEntity countryCodes;


    public CustomerEntity() {
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public List<PickupRequestEntity> getListOfRequesterPickupRequest() {
        return listOfRequesterPickupRequest;
    }

    public void setListOfRequesterPickupRequest(List<PickupRequestEntity> listOfRequesterPickupRequest) {
        this.listOfRequesterPickupRequest = listOfRequesterPickupRequest;
    }

    public List<PickupRequestEntity> getListOfRecipientPickupRequest() {
        return listOfRecipientPickupRequest;
    }

    public void setListOfRecipientPickupRequest(List<PickupRequestEntity> listOfRecipientPickupRequest) {
        this.listOfRecipientPickupRequest = listOfRecipientPickupRequest;
    }

    public CountryCodesEntity getCountryCodes() {
        return countryCodes;
    }

    public void setCountryCodes(CountryCodesEntity countryCodes) {
        this.countryCodes = countryCodes;
    }
}
