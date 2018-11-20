package com.appzoneltd.lastmile.microservice.freelancedriver.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "building", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "BuildingEntity.countAll", query = "SELECT COUNT(x) FROM BuildingEntity x")
})
public class BuildingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "building_id", nullable = false)
    private Long buildingId;

    @Column(name = "buildingname", nullable = false, length = 100)
    private String buildingname;

    @Column(name = "buildingnumber", length = 50)
    private String buildingnumber;

    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Column(name = "area", length = 100)
    private String area;

    @Column(name = "street", length = 100)
    private String street;

    @Column(name = "waselcode", length = 100)
    private String waselcode;

    @Column(name = "longitude", length = 2147483647)
    private String longitude;

    @Column(name = "latitude", length = 2147483647)
    private String latitude;

    @Column(name = "description", length = 500)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created", nullable = false)
    private Date created;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "version", nullable = false)
    private Long version;


//    @ManyToOne
//    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
//    private CityEntity city;

//    @ManyToOne
//    @JoinColumn(name = "building_type_id", referencedColumnName = "building_type_id")
//    private BuildingTypeEntity buildingType;

//    @OneToMany(mappedBy = "building", targetEntity = BuildingServingAreaEntity.class)
//    private List<BuildingServingAreaEntity> listOfBuildingServingArea;

    @OneToMany(mappedBy = "building", targetEntity = VehicleEntity.class)
    private List<VehicleEntity> listOfVehicle;

    @ManyToOne
    @JoinColumn(name = "country_code_id", referencedColumnName = "id")
    private CountryCodesEntity countryCodes;

//    @ManyToOne
//    @JoinColumn(name = "country_id", referencedColumnName = "country_id")
//    private CountryEntity country;


    public BuildingEntity() {
        super();
    }

    public Long getBuildingId() {
        return this.buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingname() {
        return this.buildingname;
    }

    public void setBuildingname(String buildingname) {
        this.buildingname = buildingname;
    }

    public String getBuildingnumber() {
        return this.buildingnumber;
    }


    public void setBuildingnumber(String buildingnumber) {
        this.buildingnumber = buildingnumber;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStreet() {
        return this.street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getWaselcode() {
        return this.waselcode;
    }

    public void setWaselcode(String waselcode) {
        this.waselcode = waselcode;
    }

    public String getLongitude() {
        return this.longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return this.latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public List<VehicleEntity> getListOfVehicle() {
        return this.listOfVehicle;
    }

    public void setListOfVehicle(List<VehicleEntity> listOfVehicle) {
        this.listOfVehicle = listOfVehicle;
    }

    public CountryCodesEntity getCountryCodes() {
        return this.countryCodes;
    }

    public void setCountryCodes(CountryCodesEntity countryCodes) {
        this.countryCodes = countryCodes;
    }

}
