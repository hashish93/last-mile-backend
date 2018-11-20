package com.appzoneltd.lastmile.microservice.employee.dao;

public interface  UserRepository {

	String FIND_ALL_EMPLOYEE="select DISTINCT users.user_id AS id,username, phone, email,user_type,users.created " 
			+"FROM lastmile_authorization_server.users " 
			+"INNER JOIN lastmile_authorization_server.users_roles  roles ON  users.user_id=roles.user_id " 
			+"WHERE status='ACTIVE' AND roles.role_id!=1 AND user_type!='CUSTMOER' ORDER BY users.created ";
}
