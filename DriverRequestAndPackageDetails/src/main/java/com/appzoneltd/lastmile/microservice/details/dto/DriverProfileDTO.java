package com.appzoneltd.lastmile.microservice.details.dto;

import javax.validation.constraints.Pattern;
import java.time.LocalTime;

//@Data
public class DriverProfileDTO {

    private Long driverId;
    private String firstName;
    private String lastName;
    private String email;
    
    @Pattern(regexp = "^[0-9+]?[0-9]*$", message = "user.phone.errorformat")
    private String phoneNumber;
    
    private Long imageId;
    private String nationalId;
    private Long activeVehicleId;
    private LocalTime workShiftFrom;
    private LocalTime WorkShiftTo;
    private String hubName;
    private String hubLongitude;
    private String hubLatitude;
    private String hubCountryCode;
    private String hubPhoneNumber;
    
    private String freelanceCityToDriveIn;
    private String driverType;
    private String plateNumber;
    private String vehicleBrand;
    private String vehicleModel;
    private String vehicleModelYear;
    private String verificationCode;

    private Boolean isVerified;
    private String purpose;


    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public Long getActiveVehicleId() {
        return activeVehicleId;
    }

    public void setActiveVehicleId(Long activeVehicleId) {
        this.activeVehicleId = activeVehicleId;
    }

    public String getHubName() {
        return hubName;
    }

    public void setHubName(String hubName) {
        this.hubName = hubName;
    }

    public String getHubLongitude() {
        return hubLongitude;
    }

    public void setHubLongitude(String hubLongitude) {
        this.hubLongitude = hubLongitude;
    }

    public String getHubLatitude() {
        return hubLatitude;
    }

    public void setHubLatitude(String hubLatitude) {
        this.hubLatitude = hubLatitude;
    }

    public String getHubCountryCode() {
        return hubCountryCode;
    }

    public void setHubCountryCode(String hubCountryCode) {
        this.hubCountryCode = hubCountryCode;
    }

    public String getHubPhoneNumber() {
        return hubPhoneNumber;
    }

    public void setHubPhoneNumber(String hubPhoneNumber) {
        this.hubPhoneNumber = hubPhoneNumber;
    }

    public LocalTime getWorkShiftFrom() {
        return workShiftFrom;
    }

    public void setWorkShiftFrom(LocalTime workShiftFrom) {
        this.workShiftFrom = workShiftFrom;
    }

    public LocalTime getWorkShiftTo() {
        return WorkShiftTo;
    }

    public void setWorkShiftTo(LocalTime workShiftTo) {
        WorkShiftTo = workShiftTo;
    }

	public String getFreelanceCityToDriveIn() {
		return freelanceCityToDriveIn;
	}

	public void setFreelanceCityToDriveIn(String freelanceCityToDriveIn) {
		this.freelanceCityToDriveIn = freelanceCityToDriveIn;
	}

	public String getDriverType() {
		return driverType;
	}

	public void setDriverType(String driverType) {
		this.driverType = driverType;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getVehicleBrand() {
		return vehicleBrand;
	}

	public void setVehicleBrand(String vehicleBrand) {
		this.vehicleBrand = vehicleBrand;
	}

	public String getVehicleModel() {
		return vehicleModel;
	}

	public void setVehicleModel(String vehicleModel) {
		this.vehicleModel = vehicleModel;
	}

	public String getVehicleModelYear() {
		return vehicleModelYear;
	}

	public void setVehicleModelYear(String vehicleModelYear) {
		this.vehicleModelYear = vehicleModelYear;
	}

	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

    public Boolean getVerified() {
        return isVerified;
    }

    public void setVerified(Boolean verified) {
        isVerified = verified;
    }

    public String getPurpose() {
        return purpose;
    }

    public DriverProfileDTO setPurpose(String purpose) {
        this.purpose = purpose;
        return this;
    }
}
