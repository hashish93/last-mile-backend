package com.appzoneltd.lastmile.microservice.employee.dao;

import com.appzoneltd.lastmile.microservice.enums.EntityStatus;
import com.appzoneltd.lastmile.microservice.enums.UserType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DriverRowMapper implements RowMapper<DriverInfo> {
    private DriverInfo driverInfo;

    @Override
    public DriverInfo mapRow(ResultSet rs, int arg1) throws SQLException {
        driverInfo = new DriverInfo();

        driverInfo.setUserId(rs.getLong("id"));
        driverInfo.setUsername(rs.getString("username"));
        driverInfo.setFirstName(rs.getString("firstname"));
        driverInfo.setLastName(rs.getString("lastname"));
        // driverInfo.setPassword(rs.getString("password"));
        driverInfo.setCountryCodeId(rs.getLong("country_code_id"));
        driverInfo.setCountryCode(rs.getLong("phonecode"));
        driverInfo.setPhone(rs.getString("phone"));
        driverInfo.setEmail(rs.getString("email"));
        driverInfo.setEnabled(rs.getBoolean("enabled"));
        driverInfo.setCreated(rs.getTimestamp("created"));
        driverInfo.setPersonalPhotoId(rs.getLong("personal_photo_id"));
        driverInfo.setPersonalPhoto(null);
        if (rs.wasNull())
            driverInfo.setPersonalPhotoId(null);
        driverInfo.setEntityStatus(EntityStatus.valueOf(rs.getString("status")));
        driverInfo.setDescription(rs.getString("description"));
        driverInfo.setUserType(UserType.valueOf(rs.getString("user_type")));
        driverInfo.setVersion(rs.getLong("version"));

        driverInfo.setDrivingLicenseExpDate(rs.getTimestamp("driving_license_exp_date"));
        driverInfo.setDrivingLicenseTypeId(rs.getLong("driving_license_type_id"));
        driverInfo.setDrivingLicenseType(rs.getString("license_type"));
        driverInfo.setDrivingLicenseId(rs.getString("driving_license_id"));
        driverInfo.setNationalId(rs.getString("national_id"));

        return driverInfo;
    }

}
