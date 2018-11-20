package com.appzoneltd.lastmile.microservice.customer.dao;

import com.appzoneltd.lastmile.microservice.customer.dto.CustomerInfo;
import com.appzoneltd.lastmile.microservice.enums.EntityStatus;
import com.appzoneltd.lastmile.microservice.enums.UserType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author alaa.nabil
 */
public class CustomerRowMapper implements RowMapper<CustomerInfo> {

    @Override
    public CustomerInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
        CustomerInfo customerInfo = new CustomerInfo();

        customerInfo.setUserId(rs.getLong("customer_id"));
        customerInfo.setUsername(rs.getString("username"));
        customerInfo.setFirstName(rs.getString("firstname"));
        customerInfo.setLastName(rs.getString("lastname"));
        customerInfo.setBirthdate(rs.getDate("birthdate"));
        customerInfo.setGender(rs.getString("gender"));
//		customerInfo.setPassword(rs.getString("password"));
        customerInfo.setPhone(rs.getString("phone"));
        customerInfo.setEmail(rs.getString("email"));
        customerInfo.setEnabled(rs.getBoolean("enabled"));
        customerInfo.setCreated(rs.getTimestamp("created"));
        customerInfo.setPersonalPhoto(rs.getLong("personal_photo_id"));
        if (rs.wasNull())
            customerInfo.setPersonalPhoto(null);
        customerInfo.setEntityStatus(EntityStatus.valueOf(rs.getString("status")));
        customerInfo.setDescription(rs.getString("description"));
        customerInfo.setUserTypeId(rs.getLong("user_type_id"));
        if(customerInfo.getUserTypeId()!=null && customerInfo.getUserTypeId() == 8L){
            customerInfo.setUserType(UserType.CUSTOMER);
        }
        if(customerInfo.getUserTypeId()!=null && customerInfo.getUserTypeId() == 7L){
            customerInfo.setUserType(UserType.FREELANCER_DRIVER);
        }
        if(customerInfo.getUserTypeId()!=null && customerInfo.getUserTypeId() == 6L){
            customerInfo.setUserType(UserType.CORPORATE_DRIVER);
        }
        if(customerInfo.getUserTypeId()!=null && customerInfo.getUserTypeId() == 5L){
            customerInfo.setUserType(UserType.OPERATION_BACKOFFICE);
        }
        if(customerInfo.getUserTypeId()!=null && customerInfo.getUserTypeId() == 4L){
            customerInfo.setUserType(UserType.SUPERVISOR);
        }
        if(customerInfo.getUserTypeId()!=null && customerInfo.getUserTypeId() == 3L){
            customerInfo.setUserType(UserType.HUB_ADMIN);
        }
        if(customerInfo.getUserTypeId()!=null && customerInfo.getUserTypeId() == 2L){
            customerInfo.setUserType(UserType.FREELANCER_ADMIN);
        }
        if(customerInfo.getUserTypeId()!=null && customerInfo.getUserTypeId() == 1L){
            customerInfo.setUserType(UserType.SUPER_ADMIN);
        }

        customerInfo.setVersion(rs.getLong("version"));
        customerInfo.setCountryNameAr(rs.getString("country_name_ar"));
        customerInfo.setCountryNameEn(rs.getString("country_name_en"));
        customerInfo.setCityNameAr(rs.getString("city_name_ar"));
        customerInfo.setCityNameEn(rs.getString("city_name_en"));
        customerInfo.setVerified(rs.getBoolean("is_verified"));
        return customerInfo;
    }

}
