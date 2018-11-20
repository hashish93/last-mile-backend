package com.appzoneltd.lastmile.microservice.employee.dao;

public interface DriverRepository {
    String SQL_INSERT_DRIVER = "INSERT INTO lastmile_core.driver(id, national_id, driving_license_type_id, driving_license_exp_date, driving_license_id) VALUES (?, ?, ?, ?, ?)";

    String SQL_UPDATE_DRIVER = "UPDATE lastmile_core.driver SET national_id=?, driving_license_type_id=?, driving_license_exp_date=?, driving_license_id=? WHERE id=?";

    String SELECT_DRIVER_BY_ID = "SELECT driver.id, national_id, driver.driving_license_type_id, license_type, driving_license_exp_date, driver.driving_license_id, users.version, username, firstname, lastname, codes.id AS country_code_id, codes.phonecode, phone, email, enabled, users.created, personal_photo_id, status, description, user_type "
            + "FROM lastmile_core.driver "
            + "INNER JOIN lastmile_authorization_server.users users ON driver.id=users.user_id "
            + "INNER JOIN lastmile_core.country_codes codes ON users.country_code_id=codes.id "
            + "INNER JOIN lastmile_core.driving_license_type ON driving_license_type.driving_license_type_id=driver.driving_license_type_id "
            + "WHERE status='ACTIVE' AND driver.id=?";

    String SELECT_ALL_DRIVERS = "SELECT driver.id, national_id, driver.driving_license_type_id, license_type, driving_license_exp_date, driver.driving_license_id, users.version, username, firstname, lastname, codes.id AS country_code_id, codes.phonecode, phone, email, enabled, users.created, personal_photo_id, status, description, user_type "
            + "FROM lastmile_core.driver "
            + "INNER JOIN lastmile_authorization_server.users users ON driver.id=users.user_id "
            + "INNER JOIN lastmile_core.country_codes codes ON users.country_code_id=codes.id "
            + "INNER JOIN lastmile_core.driving_license_type ON driving_license_type.driving_license_type_id=driver.driving_license_type_id "
            + "WHERE status='ACTIVE' ORDER BY users.created ";

    String COUNT_ALL_DRIVERS = "SELECT count(*) " + "FROM lastmile_core.driver "
            + "INNER JOIN lastmile_authorization_server.users users ON driver.id=users.user_id "
            + "INNER JOIN lastmile_core.driving_license_type ON driving_license_type.driving_license_type_id=driver.driving_license_type_id "
            + "WHERE status='ACTIVE'";


    String DRIVER_COUNT_RELATED_ACTIVE_VEHICLE = "SELECT COUNT (id) FROM lastmile_core.active_vehicle WHERE driver_id=?";
}
