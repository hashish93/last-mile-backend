package com.appzoneltd.lastmile.microservice.freelancedriver.dto;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.appzoneltd.lastmile.microservice.modelobjects.User;

import lombok.Data;

@Data
public class FreelancerDriverListInfoDto extends User {

	private static final long serialVersionUID = 1L;

	private String driverName;

	@NotEmpty(message = "user.nationalId.required")
	private String nationalId;

	private Long nationalIdImage;

	private String cityDriveIn;

	private String vehicleType;

	// @NotEmpty(message = "user.brand.required")
	private String brand;
	private String model;

	@NotEmpty(message = "user.modelYear.required")
	private String modelYear;

	private String freelancerDriverStatus;

	private Long cityId;

	private Long vehicleTypeId;

	private Date drivingLicenseExpDate;

	@NotEmpty(message = "user.drivingLicenseId.required")
	private String drivingLicenseId;

	private BigDecimal rating;

	@NotNull(message = "user.amount.required")
	private Integer amount;

	@NotEmpty(message = "user.color.required")
	private String color;

	@NotEmpty(message = "user.plate.required")
	private String plate;

	@NotEmpty(message = "user.chassis.required")
	private String chassis;

	@NotNull(message = "user.weight.required")
	private BigDecimal weight;

	@NotEmpty(message = "user.purpose.required")
	private String purpose;

	@NotEmpty(message = "user.motor.required")
	private String motor;

	@NotNull(message = "user.National_id_exist.required")
	private Boolean is_National_Id_Exist;

	@NotNull(message = "user.Vehicleownership_Id_Exist.required")
	private Boolean is_Vehicleownership_Id_Exist;

	@NotNull(message = "user.description.required")
	private Boolean is_Criminalrecord_Exist;

	@NotNull(message = "user.Birthcertificate_Exist.required")
	private Boolean is_Birthcertificate_Exist;

	private Boolean isApproved;

	private String vehicleDescription;
	
	@NotEmpty(message = "driver.rejectionReasonDescription.required")
	private String rejectionReasonDescription ;

}
