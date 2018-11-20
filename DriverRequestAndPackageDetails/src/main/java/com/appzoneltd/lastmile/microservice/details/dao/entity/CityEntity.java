package com.appzoneltd.lastmile.microservice.details.dao.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "city", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "CityEntity.countAll", query = "SELECT COUNT(x) FROM CityEntity x")
})
public class CityEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "city_id", nullable = false)
    private Long cityId;

   @Column(name = "name_ar", nullable = false, length = 100)
    private String nameAr;

    @Column(name = "name_en", nullable = false, length = 100)
    private String nameEn;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getCityId() {
        return cityId;
    }

    public CityEntity setCityId(Long cityId) {
        this.cityId = cityId;
        return this;
    }

    public String getNameAr() {
        return nameAr;
    }

    public CityEntity setNameAr(String nameAr) {
        this.nameAr = nameAr;
        return this;
    }

    public String getNameEn() {
        return nameEn;
    }

    public CityEntity setNameEn(String nameEn) {
        this.nameEn = nameEn;
        return this;
    }
}
