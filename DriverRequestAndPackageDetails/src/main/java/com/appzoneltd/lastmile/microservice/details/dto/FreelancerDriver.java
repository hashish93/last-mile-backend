package com.appzoneltd.lastmile.microservice.details.dto;

import com.appzoneltd.lastmile.microservice.modelobjects.User;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by alaa on 6/28/17.
 */
public class FreelancerDriver extends User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3336895316500248810L;

	// @NotNull(message = "countryId.notnull")
	// private Long countryId;
	@NotNull(message = "cityId.notnull")
	private Long cityId;
	@NotNull(message = "imageId.notnull")
	private Long imageId;
	
	@NotNull(message = "brand.notempty")
	private Long brand;
//	@NotNull(message = "model.notempty")
	private Long model;
	
	@NotNull(message = "vehicleType.notnull")
	private Long vehicleTypeId;

	@NotEmpty(message = "drivingLicenseId.notempty")
	private String drivingLicenseId;

	@NotEmpty(message = "modelYear.notempty")
	private String modelYear;

	public Long getCityId() {
		return cityId;
	}

	public FreelancerDriver setCityId(Long city_id) {
		this.cityId = city_id;
		return this;
	}

	public Long getImageId() {
		return imageId;
	}

	public FreelancerDriver setImageId(Long image_id) {
		this.imageId = image_id;
		return this;
	}

	public Long getVehicleTypeId() {
		return vehicleTypeId;
	}

	public void setVehicleTypeId(Long vehicleTypeId) {
		this.vehicleTypeId = vehicleTypeId;
	}

	public String getModelYear() {
		return modelYear;
	}

	public void setModelYear(String modelYear) {
		this.modelYear = modelYear;
	}


	public String getDrivingLicenseId() {
		return drivingLicenseId;
	}

	public void setDrivingLicenseId(String drivingLicenseId) {
		this.drivingLicenseId = drivingLicenseId;
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

}
