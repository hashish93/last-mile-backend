package com.appzoneltd.lastmile.microservice.returnrequest.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

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

    @Column(name = "gender", nullable = false, length = 50)
    private String gender;

    @Temporal(TemporalType.DATE)
    @Column(name = "birthdate", nullable = false)
    private Date birthdate;

//    @OneToMany(mappedBy = "customer2", targetEntity = PickupRequestEntity.class)
//    private List<PickupRequestEntity> listOfPickupRequest2;
//
//    @OneToMany(mappedBy = "customer", targetEntity = PickupRequestEntity.class)
//    private List<PickupRequestEntity> listOfPickupRequest;

    @OneToMany(mappedBy = "recipient", targetEntity = DeliveryRequestEntity.class)
    private List<DeliveryRequestEntity> listOfDeliveryRequest;

//    @ManyToOne
//    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
//    private CountryEntity country;
//
//    @ManyToOne
//    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
//    private CityEntity city;

    public CustomerEntity() {
        super();
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public List<DeliveryRequestEntity> getListOfDeliveryRequest() {
        return listOfDeliveryRequest;
    }

    public void setListOfDeliveryRequest(List<DeliveryRequestEntity> listOfDeliveryRequest) {
        this.listOfDeliveryRequest = listOfDeliveryRequest;
    }
}
