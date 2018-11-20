package com.appzoneltd.lastmile.microservice.customer.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "country", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "CountryEntity.countAll", query = "SELECT COUNT(x) FROM CountryEntity x")
})
public class CountryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "country_id", nullable = false)
    private Long countryId;

    @Column(name = "name_ar", nullable = false, length = 50)
    private String nameAr;

    @Column(name = "name_en", nullable = false, length = 50)
    private String nameEn;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "version", nullable = false)
    private Long version;

    @OneToMany(mappedBy = "country", targetEntity = CityEntity.class)
    private List<CityEntity> listOfCity;

    public CountryEntity() {
        super();
    }

    public Long getCountryId() {
        return this.countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getNameAr() {
        return this.nameAr;
    }

    public void setNameAr(String nameAr) {
        this.nameAr = nameAr;
    }

    public String getNameEn() {
        return this.nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public Date getCreated() {
        return this.created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<CityEntity> getListOfCity() {
        return this.listOfCity;
    }

    public void setListOfCity(List<CityEntity> listOfCity) {
        this.listOfCity = listOfCity;
    }

}
