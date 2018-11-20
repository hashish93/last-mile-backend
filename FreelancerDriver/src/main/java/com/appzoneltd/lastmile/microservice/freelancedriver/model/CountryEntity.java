package com.appzoneltd.lastmile.microservice.freelancedriver.model;

import javax.persistence.*;
import java.io.Serializable;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCountryId() {
        return countryId;
    }

    public CountryEntity setCountryId(Long countryId) {
        this.countryId = countryId;
        return this;
    }

    public String getNameAr() {
        return nameAr;
    }

    public CountryEntity setNameAr(String nameAr) {
        this.nameAr = nameAr;
        return this;
    }

    public String getNameEn() {
        return nameEn;
    }

    public CountryEntity setNameEn(String nameEn) {
        this.nameEn = nameEn;
        return this;
    }
}
