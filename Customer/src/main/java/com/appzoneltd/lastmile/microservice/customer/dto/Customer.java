package com.appzoneltd.lastmile.microservice.customer.dto;

import com.appzoneltd.lastmile.microservice.modelobjects.User;

import java.util.Date;

public class Customer extends User {
    /**
     *
     */
    private static final long serialVersionUID = 2124422058663499239L;
    //  	@NotNull(message = "customer.country.notnull")
    private Long countryId;
    //   	@NotNull(message = "customer.city.notnull")
    private Long cityId;
    //    @NotEmpty(message = "customer.gender.notempty")
    private String gender;
    //    @NotNull(message = "customer.date.notnull")
    private Date birthdate;

    public Customer() {

    }

    public Customer(Long countryId, Long cityId, String gender, Date birthdate) {
        this.countryId = countryId;
        this.cityId = cityId;
        this.gender = gender;
        this.birthdate = birthdate;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
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

    @Override
    public String toString() {
        return "Customer [countryId=" + countryId + ", cityId=" + cityId + ", gender=" + gender + ", birthdate="
                + birthdate + "]";
    }


}
