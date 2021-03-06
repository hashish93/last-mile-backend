package com.appzoneltd.lastmile.microservice.freelancedriver.model;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "freelancer_driver", schema = "lastmile_core")
@NamedQueries({
        @NamedQuery(name = "FreelancerDriverEntity.countAll", query = "SELECT COUNT(x) FROM FreelancerDriverEntity x")})
public class FreelancerDriverEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "national_id", length = 50)
    private String nationalId;

    @Temporal(TemporalType.DATE)
    @Column(name = "driving_license_exp_date")
    private Date drivingLicenseExpDate;

    @Column(name = "driving_license_id", length = 50)
    private String drivingLicenseId;

    @Column(name = "rating")
    private BigDecimal rating;

    @Column(name = "amount")
    private Integer amount;

    @Column(name = "brand", nullable = false, length = 50)
    private Long brand;

    @Column(name = "model", nullable = false, length = 50)
    private Long model;

    @Column(name = "color", length = 50)
    private String color;

    @Column(name = "plate", length = 50)
    private String plate;

    @Column(name = "chassis", length = 100)
    private String chassis;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "purpose", length = 50)
    private String purpose;

    @Column(name = "motor", length = 50)
    private String motor;

    @Column(name = "model_year", length = 50)
    private String modelYear;

    @Column(name = "status", nullable = false, length = 50)
    private String status;

    @Column(name = "is_national_id_exit")
    private Boolean is_National_id_exist;

    @Column(name = "is_vehicle_ownership_id_exist")
    private Boolean is_Vehicleownership_Id_Exist;

    @Column(name = "is_criminalrecord_exist")
    private Boolean is_Criminalrecord_Exist;

    @Column(name = "is_birthcertificate_exist")
    private Boolean is_Birthcertificate_Exist;

    @Column(name = "is_approved")
    private Boolean isApproved;

    @Column(name = "description", length = 500)
    private String description;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    @Column(name = "version")
    private Long version;

    @Column(name = "rejectionreasondescription")
    private String rejectionReasonDescription;

    @ManyToOne
    @JoinColumn(name = "building_id", referencedColumnName = "building_id")
    private BuildingEntity building;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "city_id")
    private CityEntity city;

    @Column(name = "image_id", nullable = false)
    private Long nationalIdImage;

    @ManyToOne
    @JoinColumn(name = "vehicle_type_id", referencedColumnName = "vehicle_type_id")
    private VehicleTypeEntity vehicleType;

    // @ManyToOne
    // @JoinColumn(name = "country_id", referencedColumnName = "country_id")
    // private CountryEntity country;

    @ManyToOne
    @JoinColumn(name = "driving_license_type_id", referencedColumnName = "driving_license_type_id")
    private DrivingLicenseTypeEntity drivingLicenseType;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "userId", updatable = false)
    private UsersEntity usersEntity;

    @ManyToOne
    @JoinColumn(name = "brand", referencedColumnName = "brand_id", insertable = false, updatable = false)
    private CarsBrandsEntity carsBrandsEntity;

    @ManyToOne
    @JoinColumn(name = "model", referencedColumnName = "model_id", insertable = false, updatable = false)
    private CarsModelsEntity carsModelsEntity;

    public FreelancerDriverEntity() {
        super();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNationalId() {
        return this.nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Date getDrivingLicenseExpDate() {
        return this.drivingLicenseExpDate;
    }

    public void setDrivingLicenseExpDate(Date drivingLicenseExpDate) {
        this.drivingLicenseExpDate = drivingLicenseExpDate;
    }

    public String getDrivingLicenseId() {
        return this.drivingLicenseId;
    }

    public void setDrivingLicenseId(String drivingLicenseId) {
        this.drivingLicenseId = drivingLicenseId;
    }

    public BigDecimal getRating() {
        return this.rating;
    }

    public void setRating(BigDecimal rating) {
        this.rating = rating;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlate() {
        return this.plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getChassis() {
        return this.chassis;
    }

    public void setChassis(String chassis) {
        this.chassis = chassis;
    }

    public BigDecimal getWeight() {
        return this.weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public String getPurpose() {
        return this.purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getRejectionReasonDescription() {
        return rejectionReasonDescription;
    }

    public void setRejectionReasonDescription(String rejectionReasonDescription) {
        this.rejectionReasonDescription = rejectionReasonDescription;
    }

    public String getMotor() {
        return this.motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }


    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Boolean getIs_National_id_exist() {
        return is_National_id_exist;
    }

    public void setIs_National_id_exist(Boolean is_National_id_exist) {
        this.is_National_id_exist = is_National_id_exist;
    }

    public Boolean getIs_Vehicleownership_Id_Exist() {
        return is_Vehicleownership_Id_Exist;
    }

    public void setIs_Vehicleownership_Id_Exist(Boolean is_Vehicleownership_Id_Exist) {
        this.is_Vehicleownership_Id_Exist = is_Vehicleownership_Id_Exist;
    }

    public Boolean getIs_Criminalrecord_Exist() {
        return is_Criminalrecord_Exist;
    }

    public void setIs_Criminalrecord_Exist(Boolean is_Criminalrecord_Exist) {
        this.is_Criminalrecord_Exist = is_Criminalrecord_Exist;
    }

    public Boolean getIs_Birthcertificate_Exist() {
        return is_Birthcertificate_Exist;
    }

    public void setIs_Birthcertificate_Exist(Boolean is_Birthcertificate_Exist) {
        this.is_Birthcertificate_Exist = is_Birthcertificate_Exist;
    }

    public Boolean getIsApproved() {
        return this.isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
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

    public Long getVersion() {
        return this.version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public BuildingEntity getBuilding() {
        return this.building;
    }

    public void setBuilding(BuildingEntity building) {
        this.building = building;
    }

    public CityEntity getCity() {
        return this.city;
    }

    public void setCity(CityEntity city) {
        this.city = city;
    }

    public Long getNationalIdImage() {
        return this.nationalIdImage;
    }

    public void setNationalIdImage(Long nationalIdImage) {
        this.nationalIdImage = nationalIdImage;
    }

    public VehicleTypeEntity getVehicleType() {
        return this.vehicleType;
    }

    public void setVehicleType(VehicleTypeEntity vehicleType) {
        this.vehicleType = vehicleType;
    }

    // public CountryEntity getCountry() {
    // return this.country;
    // }
    //
    // public void setCountry(CountryEntity country) {
    // this.country = country;
    // }

    public DrivingLicenseTypeEntity getDrivingLicenseType() {
        return this.drivingLicenseType;
    }

    public void setDrivingLicenseType(DrivingLicenseTypeEntity drivingLicenseType) {
        this.drivingLicenseType = drivingLicenseType;
    }

    public Long getBrand() {
        return brand;
    }

    public void setBrand(Long brand) {
        this.brand = brand;
    }

    public Long getModel() {
        return model;
    }

    public void setModel(Long model) {
        this.model = model;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        sb.append(id);
        sb.append("]:");
        sb.append(nationalId);
        sb.append("|");
        sb.append(drivingLicenseExpDate);
        sb.append("|");
        sb.append(drivingLicenseId);
        sb.append("|");
        sb.append(rating);
        sb.append("|");
        sb.append(brand);
        sb.append("|");
        sb.append(model);
        sb.append("|");
        sb.append(color);
        sb.append("|");
        sb.append(plate);
        sb.append("|");
        sb.append(chassis);
        sb.append("|");
        sb.append(weight);
        sb.append("|");
        sb.append(purpose);
        sb.append("|");
        sb.append(motor);
        sb.append("|");
        sb.append(status);
        sb.append("|");
        sb.append(isApproved);
        sb.append("|");
        sb.append(description);
        sb.append("|");
        sb.append(created);
        sb.append("|");
        sb.append(version);
        return sb.toString();
    }

    public UsersEntity getUsersEntity() {
        return usersEntity;
    }

    public void setUsersEntity(UsersEntity usersEntity) {
        this.usersEntity = usersEntity;
    }

    public CarsBrandsEntity getCarsBrandsEntity() {
        return carsBrandsEntity;
    }

    public void setCarsBrandsEntity(CarsBrandsEntity carsBrandsEntity) {
        this.carsBrandsEntity = carsBrandsEntity;
    }

    public CarsModelsEntity getCarsModelsEntity() {
        return carsModelsEntity;
    }

    public void setCarsModelsEntity(CarsModelsEntity carsModelsEntity) {
        this.carsModelsEntity = carsModelsEntity;
    }
}
