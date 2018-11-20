package com.appzoneltd.lastmile.microservice.employee.dao;

public interface EmployeeRepository {
    String SQL_INSERT_USER = "INSERT INTO lastmile_authorization_server.users(user_id, username, password, firstname, lastname, country_code_id, phone, email, personal_photo_id, description, user_type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String SQL_UPDATE_USER = "UPDATE lastmile_authorization_server.users SET username=?, firstname=?, lastname=?, country_code_id=?, phone=?, email=?, enabled=?, personal_photo_id=?, status=?, description=?, version=version+1 WHERE status='ACTIVE' AND version=? AND user_id=?";
    String SQL_DEACTIVATE_QUERY = "UPDATE lastmile_authorization_server.users SET enabled=FALSE, status='INACTIVE', version=version+1 WHERE user_id=?";

    String SQL_INSERT_EMPLOYEE = "INSERT INTO lastmile_core.back_office_employee(id, national_id) VALUES (?, ?)";

    String SQL_UPDATE_EMPLOYEE = "UPDATE lastmile_core.back_office_employee SET national_id=? WHERE id=?";

    String SELECT_EMPLOYEE_BY_ID = "SELECT back_office_employee.id, national_id, users.version, username,firstname, lastname, codes.id AS country_code_id, codes.phonecode, phone, email, enabled, users.created, personal_photo_id, status, description, user_type "
            + "FROM lastmile_core.back_office_employee "
            + "INNER JOIN lastmile_authorization_server.users users ON back_office_employee.id=users.user_id "
            + "INNER JOIN lastmile_core.country_codes codes ON users.country_code_id=codes.id "
            + "WHERE status='ACTIVE' AND back_office_employee.id=?";

    String SELECT_ALL_EMPLOYEES = "SELECT DISTINCT back_office_employee.id, national_id, users.version, username,firstname, lastname, codes.phonecode, codes.id AS country_code_id, phone, email, enabled, users.created, personal_photo_id, status, description, user_type "
            + "FROM lastmile_core.back_office_employee "
            + "INNER JOIN lastmile_authorization_server.users users ON back_office_employee.id=users.user_id "
            + "INNER JOIN lastmile_authorization_server.users_roles  roles ON id=roles.user_id "
            + "INNER JOIN lastmile_core.country_codes codes ON users.country_code_id=codes.id "
            + "WHERE status='ACTIVE' AND roles.role_id!=1 ORDER BY users.created ";

    String COUNT_ALL_EMPLOYEES = "SELECT COUNT(*) " + "FROM lastmile_core.back_office_employee "
            + "INNER JOIN lastmile_authorization_server.users users ON back_office_employee.id=users.user_id "
            + "INNER JOIN lastmile_authorization_server.users_roles  roles ON id=roles.user_id "
            + "INNER JOIN lastmile_core.country_codes codes ON users.country_code_id=codes.id "
            + "WHERE status='ACTIVE' AND roles.role_id!=1 ";


}
