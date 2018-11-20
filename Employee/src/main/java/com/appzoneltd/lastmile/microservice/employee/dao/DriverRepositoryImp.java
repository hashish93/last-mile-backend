package com.appzoneltd.lastmile.microservice.employee.dao;

import com.appzoneltd.lastmile.microservice.abstractconfig.AbstractDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DriverRepositoryImp extends AbstractDao<DriverInfo> implements DriverRepository {

    @Autowired
    public DriverRepositoryImp(JdbcTemplate jdbcTemplate) {
        super(jdbcTemplate, new DriverRowMapper(), EmployeeRepository.SQL_INSERT_USER,
                EmployeeRepository.SQL_UPDATE_USER, EmployeeRepository.SQL_DEACTIVATE_QUERY, SELECT_ALL_DRIVERS,
                SELECT_DRIVER_BY_ID, null, COUNT_ALL_DRIVERS);
    }

    public Integer createDriver(Driver driver) {
        int result = this.save(driver.getUserId(), driver.getUsername().toLowerCase(), driver.getPassword(), driver.getFirstName().toLowerCase(),
                driver.getLastName().toLowerCase(), driver.getCountryCodeId(), driver.getPhone(), driver.getEmail().toLowerCase(), driver.getPersonalPhotoId(),
                driver.getDescription(), driver.getUserType().getValue());
        if (result == 1)
            return jdbcTemplate.update(SQL_INSERT_DRIVER, driver.getUserId(), driver.getNationalId(),
                    driver.getDrivingLicenseTypeId(), driver.getDrivingLicenseExpDate(), driver.getDrivingLicenseId());
        else
            return null;
    }

    public Integer updateDriver(Driver driver) {
        int result = this.update(driver.getUsername().toLowerCase(), driver.getFirstName().toLowerCase(), driver.getLastName().toLowerCase(), driver.getCountryCodeId(), driver.getPhone(),
                driver.getEmail().toLowerCase(), driver.getEnabled(), driver.getPersonalPhotoId(),
                driver.getEntityStatus().getValue(), driver.getDescription(), driver.getVersion(), driver.getUserId());

        result = jdbcTemplate.update(SQL_UPDATE_DRIVER, driver.getNationalId(), driver.getDrivingLicenseTypeId(),
                driver.getDrivingLicenseExpDate(), driver.getDrivingLicenseId(), driver.getUserId());
        if (result == 1)
            return result;
        else
            return null;
    }

    public Long countDriverRelatedToActiveVehicle(Long driverId) {

        return jdbcTemplate.queryForObject(DRIVER_COUNT_RELATED_ACTIVE_VEHICLE, Long.class, driverId);
    }

    public Long getDriverId(String credentials) {
        return jdbcTemplate.queryForObject(
                "SELECT user_id FROM lastmile_authorization_server.users WHERE email=? OR phone=?", Long.class,
                credentials, credentials);
    }

}
