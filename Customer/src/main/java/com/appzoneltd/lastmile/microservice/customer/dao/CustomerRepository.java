package com.appzoneltd.lastmile.microservice.customer.dao;

public interface CustomerRepository {

    String SQL_INSERT_USER = "INSERT INTO lastmile_authorization_server.users(user_id, username, password, firstname, lastname, country_code_id, phone, email, personal_photo_id, description, user_type_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    String SQL_INSERT_CUSTOMER = "INSERT INTO lastmile_core.customer(customer_id, country_id, city_id, gender, birthdate, country_code_id) VALUES (?, ?, ?, ?, ?, ?)";

    String SQL_UPDATE_USER = "UPDATE lastmile_authorization_server.users SET username=?, firstname=?, lastname=?, country_code_id=?, phone=?, email=?, enabled=?, personal_photo_id=?, status=?, description=?, version=version+1 WHERE status='ACTIVE' AND version=? AND user_id=?";
    String SQL_UPDATE_CUSTOMER = "UPDATE lastmile_core.customer SET country_id=?, city_id=?, gender=?, birthdate=? WHERE customer_id=?";

    String SQL_DEACTIVATE_QUERY = "UPDATE lastmile_authorization_server.users SET enabled=FALSE, status='INACTIVE', version=version+1 WHERE user_id=?";

//	String SELECT_CUSTOMER_BY_ID = "SELECT customer_id, gender, birthdate, users.version, username,firstname, lastname, phone, email, enabled, users.created, personal_photo_id, status, description,"
//			+ " user_type, country.name_ar AS country_name_ar, country.name_en AS country_name_en, city.name_ar AS city_name_ar, city.name_en AS city_name_en, is_verified "
//			+ "FROM lastmile_core.customer customer "
//			+ "INNER JOIN lastmile_authorization_server.users users ON customer_id=user_id "
//			+ "INNER JOIN lastmile_core.country country ON customer.country_id=country.country_id "
//			+ "INNER JOIN lastmile_core.city city ON customer.city_id=city.city_id "
//            + "INNER JOIN lastmile_core.customer_verification v ON customer.customer_id=v.id "
//			+ "WHERE status='ACTIVE' AND customer_id=?";

//	String SELECT_ALL_CUSTOMERS = "SELECT customer_id, gender, birthdate, users.version, username, firstname, lastname, phone, email, enabled, users.created, personal_photo_id, status, description, user_type, country.name_ar AS country_name_ar, country.name_en AS country_name_en, city.name_ar AS city_name_ar, city.name_en AS city_name_en "
//			+ "FROM lastmile_core.customer customer "
//			+ "INNER JOIN lastmile_authorization_server.users users ON customer_id=user_id "
//			+ "INNER JOIN lastmile_core.country country ON customer.country_id=country.country_id "
//			+ "INNER JOIN lastmile_core.city city ON customer.city_id=city.city_id "
//			+ "WHERE status='ACTIVE' ORDER BY users.created ";

    String COUNT_ALL_CUSTOMERS = "SELECT COUNT(*) FROM lastmile_core.customer customer "
            + "INNER JOIN lastmile_authorization_server.users users ON customer_id=user_id "
            + "INNER JOIN lastmile_core.country country ON customer.country_id=country.country_id "
            + "INNER JOIN lastmile_core.city city ON customer.city_id=city.city_id " + "WHERE status='ACTIVE'";


    String FIND_USER_ID = "SELECT user_id FROM lastmile_authorization_server.users WHERE phone=? OR email=? ";

}
