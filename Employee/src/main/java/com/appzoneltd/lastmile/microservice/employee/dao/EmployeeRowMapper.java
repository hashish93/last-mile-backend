package com.appzoneltd.lastmile.microservice.employee.dao;

import com.appzoneltd.lastmile.microservice.enums.EntityStatus;
import com.appzoneltd.lastmile.microservice.enums.UserType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {
    private Employee employee;

    @Override
    public Employee mapRow(ResultSet rs, int arg1) throws SQLException {
        employee = new Employee();

        employee.setUserId(rs.getLong("id"));
        employee.setUsername(rs.getString("username"));
        employee.setFirstName(rs.getString("firstname"));
        employee.setLastName(rs.getString("lastname"));
        // employee.setPassword(rs.getString("password"));
        employee.setCountryCodeId(rs.getLong("country_code_id"));
        employee.setCountryCode(rs.getLong("phonecode"));
        employee.setPhone(rs.getString("phone"));
        employee.setEmail(rs.getString("email"));
        employee.setEnabled(rs.getBoolean("enabled"));
        employee.setCreated(rs.getTimestamp("created"));
        employee.setPersonalPhotoId(rs.getLong("personal_photo_id"));
        employee.setPersonalPhoto(null);
        if (rs.wasNull())
            employee.setPersonalPhotoId(null);
        employee.setEntityStatus(EntityStatus.valueOf(rs.getString("status")));
        employee.setDescription(rs.getString("description"));
        employee.setUserType(UserType.valueOf(rs.getString("user_type")));
        employee.setVersion(rs.getLong("version"));
        employee.setNationalId(rs.getString("national_id"));

        return employee;
    }

}
