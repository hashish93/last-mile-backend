package com.appzoneltd.lastmile.microservice.freelancedriver.dao;

import com.appzoneltd.lastmile.microservice.freelancedriver.model.FreelancerDriverEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository : FreelancerDriver.
 */
public interface FreelancerDriverJpaRepository extends PagingAndSortingRepository<FreelancerDriverEntity, Long> {

    List<FreelancerDriverEntity> findAllByNationalId(String nationalId);

    List<FreelancerDriverEntity> findAllByDrivingLicenseId(String drivingLicenseId);

    @Query(value = "SELECT f FROM FreelancerDriverEntity f " +
            "WHERE (:driverName = '' OR lower(f.usersEntity.username) LIKE CONCAT('%',:driverName,'%')) " +
            "AND (:city = '' OR lower(f.city.nameEn) LIKE CONCAT('%',:city,'%') OR f.city.nameAr LIKE CONCAT('%',:city,'%')) " +
            "AND (:mobileNumber = '' OR lower(f.usersEntity.phone) LIKE CONCAT('%',:mobileNumber,'%')) " +
            "AND (:brand = '' OR lower(f.carsBrandsEntity.brandName) LIKE CONCAT('%',:brand,'%')) " +
            "AND (:model = '' OR lower(f.carsModelsEntity.modelName) LIKE CONCAT('%',:model,'%')) " +
            "AND (:status = '' OR lower(f.status) LIKE CONCAT('%',:status,'%'))")
    List<FreelancerDriverEntity> findAllPageable(@Param("driverName") String driverName, @Param("city") String city, @Param("mobileNumber") String mobileNumber
            , @Param("brand") String brand, @Param("model") String model, @Param("status") String status, Pageable pageable);


    @Query(value = "SELECT f FROM FreelancerDriverEntity f " +
            "WHERE (:driverName = '' OR lower(f.usersEntity.username) LIKE CONCAT('%',:driverName,'%')) " +
            "AND (:city = '' OR lower(f.city.nameEn) LIKE CONCAT('%',:city,'%') OR f.city.nameAr LIKE CONCAT('%',:city,'%')) " +
            "AND (:mobileNumber = '' OR lower(f.usersEntity.phone) LIKE CONCAT('%',:mobileNumber,'%')) " +
            "AND (:brand = '' OR lower(f.carsBrandsEntity.brandName) LIKE CONCAT('%',:brand,'%')) " +
            "AND (:model = '' OR lower(f.carsModelsEntity.modelName) LIKE CONCAT('%',:model,'%')) " +
            "AND (:status = '' OR lower(f.status) LIKE CONCAT('%',:status,'%'))")
    List<FreelancerDriverEntity> findAllAndSort(@Param("driverName") String driverName, @Param("city") String city, @Param("mobileNumber") String mobileNumber
            , @Param("brand") String brand, @Param("model") String model, @Param("status") String status, Sort sort);
}
