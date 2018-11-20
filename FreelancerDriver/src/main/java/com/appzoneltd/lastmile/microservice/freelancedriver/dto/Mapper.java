package com.appzoneltd.lastmile.microservice.freelancedriver.dto;

import com.appzoneltd.lastmile.microservice.freelancedriver.dao.*;
import com.appzoneltd.lastmile.microservice.freelancedriver.model.FreelancerDriverEntity;
import com.appzoneltd.lastmile.microservice.freelancedriver.model.FreelancerDriverstatus;
import com.appzoneltd.lastmile.microservice.freelancedriver.model.UsersEntity;
import com.appzoneltd.lastmile.microservice.freelancedriver.model.VehicleTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Mapper {

    @Autowired
    private UsersJpaRepository userJpaRepository;

    @Autowired
    private CityJpaRepository cityJpaRepository;

    @Autowired
    private CarsModelsJpaRepository carsModelsJpaRepository;

    @Autowired
    private CarsBrandsJpaRepository carsBrandsJpaRepository;

    @Autowired
    private FreelancerDriverJpaRepository freelancerDriverJpaRepository;

    public FreelancerDriverDto mapToFreelancerDriver(FreelancerDriverEntity driverEntity) {

        UsersEntity user = userJpaRepository.findOne(driverEntity.getId());
        FreelancerDriverDto driverDto = new FreelancerDriverDto();
        driverDto.setUserId(driverEntity.getId());
        driverDto.setDriverName(user.getFirstname() + " " + user.getLastname());
        driverDto.setFirstName(user.getFirstname());
        driverDto.setLastName(user.getLastname());
        driverDto.setCityDriveIn(driverEntity.getCity().getNameEn());
        driverDto.setFreelancerDriverStatus(driverEntity.getStatus());
        driverDto.setNationalId(driverEntity.getNationalId());
        driverDto.setPhone(user.getPhone());
        driverDto.setBrand(driverEntity.getBrand());
        driverDto.setVehicleTypeId(driverEntity.getVehicleType().getVehicleTypeId());
        driverDto.setModelYear(driverEntity.getModelYear());
        driverDto.setCountryCodeId(user.getCountryCodes().getId());
        driverDto.setPersonalPhoto(user.getPersonalPhotoId());

        return driverDto;
    }

    public List<FreelancerDriverDto> mapToFreelancerDrivers(List<FreelancerDriverEntity> driverEntities) {

        List<FreelancerDriverDto> driverDtos = new ArrayList<FreelancerDriverDto>();

        for (FreelancerDriverEntity driverEntity : driverEntities) {

            UsersEntity user = userJpaRepository.findOne(driverEntity.getId());
            FreelancerDriverDto driverDto = new FreelancerDriverDto();
            driverDto.setUserId(driverEntity.getId());
            driverDto.setDriverName(user.getFirstname() + " " + user.getLastname());
            driverDto.setCityDriveIn(driverEntity.getCity().getNameEn());
            driverDto.setFreelancerDriverStatus(driverEntity.getStatus());
            driverDto.setNationalId(driverEntity.getNationalId());
            driverDto.setPhone(user.getPhone());
            driverDto.setBrand(driverEntity.getBrand());
            driverDto.setVehicleType(driverEntity.getVehicleType().getType());
            driverDto.setModelYear(driverEntity.getModelYear());
            driverDto.setModel(driverEntity.getModel());

            driverDtos.add(driverDto);
        }

        return driverDtos;

    }

    public List<FreelancerDriverListInfoDto> mapToFreelancerDriversListPojos(List<FreelancerDriverEntity> driverEntities) {

        List<FreelancerDriverListInfoDto> driverDtos = new ArrayList<FreelancerDriverListInfoDto>();

        for (FreelancerDriverEntity driverEntity : driverEntities) {

            UsersEntity user = userJpaRepository.findOne(driverEntity.getId());

            FreelancerDriverListInfoDto driverDto = new FreelancerDriverListInfoDto();

            driverDto.setUserId(driverEntity.getId());
            driverDto.setDriverName(user.getFirstname() + " " + user.getLastname());
            driverDto.setCityDriveIn(driverEntity.getCity() != null ? driverEntity.getCity().getNameEn() : null);
            driverDto.setFreelancerDriverStatus(driverEntity.getStatus());
            driverDto.setNationalId(driverEntity.getNationalId());
            driverDto.setPhone(user.getPhone());

            driverDto.setVehicleType(driverEntity.getVehicleType() != null ? driverEntity.getVehicleType().getType() : null);
            driverDto.setModelYear(driverEntity.getModelYear());
            driverDto.setBrand(carsBrandsJpaRepository.findOne(driverEntity.getBrand()).getBrandName());
            driverDto.setModel(carsModelsJpaRepository.findOne(driverEntity.getModel()).getModelName());

            driverDtos.add(driverDto);
        }

        return driverDtos;

    }

    public FreelancerDriverDto mapToFreelanceDriverDto(Long id) {
        FreelancerDriverEntity entity = freelancerDriverJpaRepository.findOne(id);
        UsersEntity user = userJpaRepository.findOne(id);

        FreelancerDriverDto freelancerDriverDto = new FreelancerDriverDto();
        freelancerDriverDto.setUserId(entity.getId());
        freelancerDriverDto.setFirstName(user.getFirstname());
        freelancerDriverDto.setLastName(user.getLastname());
        freelancerDriverDto.setDriverName(user.getFirstname() + " " + user.getLastname());
        freelancerDriverDto.setEmail(user.getEmail());
        freelancerDriverDto.setCountryCodeId(user.getCountryCodes() != null ? user.getCountryCodes().getId() : null);
        freelancerDriverDto.setCityId(entity.getCity() != null ? entity.getCity().getCityId() : null);
        freelancerDriverDto.setCityDriveIn(entity.getCity() != null ? entity.getCity().getNameEn() : null);
        freelancerDriverDto.setFreelancerDriverStatus(entity.getStatus());
        freelancerDriverDto.setNationalId(entity.getNationalId());
        freelancerDriverDto.setNationalIdImage(entity.getNationalIdImage());
        freelancerDriverDto.setPhone(user.getPhone());
        freelancerDriverDto.setBrand(entity.getBrand());
        freelancerDriverDto.setVehicleTypeId(entity.getVehicleType() != null ? entity.getVehicleType().getVehicleTypeId() : null);
        freelancerDriverDto.setVehicleType(entity.getVehicleType().getType());
        freelancerDriverDto.setModelYear(entity.getModelYear());
        freelancerDriverDto.setChassis(entity.getChassis());
        freelancerDriverDto.setColor(entity.getColor());
        freelancerDriverDto.setDescription(user.getDescription());
        freelancerDriverDto.setModel(entity.getModel());
        freelancerDriverDto.setPlate(entity.getPlate());
        freelancerDriverDto.setPurpose(entity.getPurpose());
        freelancerDriverDto.setMotor(entity.getMotor());
        freelancerDriverDto.setWeight(entity.getWeight());
        freelancerDriverDto.setChassis(entity.getChassis());
        freelancerDriverDto.setAmount(entity.getAmount());
        freelancerDriverDto.setVehicleDescription(entity.getDescription());
        freelancerDriverDto.setDrivingLicenseId(entity.getDrivingLicenseId());
        freelancerDriverDto.setIs_Birthcertificate_Exist(entity.getIs_Birthcertificate_Exist());
        freelancerDriverDto.setIs_Criminalrecord_Exist(entity.getIs_Criminalrecord_Exist());
        freelancerDriverDto.setIs_National_Id_Exist(entity.getIs_National_id_exist());
        freelancerDriverDto.setIs_Vehicleownership_Id_Exist(entity.getIs_Vehicleownership_Id_Exist());
        freelancerDriverDto.setRejectionReasonDescription(entity.getRejectionReasonDescription());

        return freelancerDriverDto;
    }

    public void mapToFreelancerDriverEntity(FreelancerDriverDto freelancerDriverDto) {

        UsersEntity user = userJpaRepository.findOne(freelancerDriverDto.getUserId());
        FreelancerDriverEntity driverEntity = freelancerDriverJpaRepository.findOne(freelancerDriverDto.getUserId());

        user.setFirstname(freelancerDriverDto.getFirstName());
        user.setLastname(freelancerDriverDto.getLastName());
        user.setEmail(freelancerDriverDto.getEmail());
        user.setPhone(freelancerDriverDto.getPhone());
        user.setDescription(freelancerDriverDto.getDescription());
        user.setEnabled(false);
        user.setStatus("INACTIVE");
        driverEntity.setCity(cityJpaRepository.findOne(freelancerDriverDto.getCityId()));
        driverEntity.setDrivingLicenseId(freelancerDriverDto.getDrivingLicenseId());
        driverEntity.setNationalId(freelancerDriverDto.getNationalId());
        driverEntity.setAmount(freelancerDriverDto.getAmount());
        driverEntity.setModelYear(freelancerDriverDto.getModelYear());
        driverEntity.setBrand(freelancerDriverDto.getBrand());
        driverEntity.setModel(freelancerDriverDto.getModel());
        driverEntity.setPlate(freelancerDriverDto.getPlate());
        driverEntity.setPurpose(freelancerDriverDto.getPurpose());
        driverEntity.setVehicleType(new VehicleTypeEntity().setVehicleTypeId(freelancerDriverDto.getVehicleTypeId()));
        driverEntity.setMotor(freelancerDriverDto.getMotor());
        driverEntity.setWeight(freelancerDriverDto.getWeight());
        driverEntity.setColor(freelancerDriverDto.getColor());
        driverEntity.setChassis(freelancerDriverDto.getChassis());
        driverEntity.setDescription(freelancerDriverDto.getVehicleDescription());
        driverEntity.setStatus(FreelancerDriverstatus.ACCEPTED.name());
        driverEntity.setIs_National_id_exist(freelancerDriverDto.getIs_National_Id_Exist());
        driverEntity.setIs_Criminalrecord_Exist(freelancerDriverDto.getIs_Criminalrecord_Exist());
        driverEntity.setIs_Vehicleownership_Id_Exist(freelancerDriverDto.getIs_Vehicleownership_Id_Exist());
        driverEntity.setIs_Birthcertificate_Exist(freelancerDriverDto.getIs_Birthcertificate_Exist());
        driverEntity.setIsApproved(Boolean.TRUE);

        userJpaRepository.save(user);
        freelancerDriverJpaRepository.save(driverEntity);

    }

}
