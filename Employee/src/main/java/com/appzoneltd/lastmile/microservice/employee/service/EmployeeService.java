package com.appzoneltd.lastmile.microservice.employee.service;

import java.security.Principal;
import java.util.List;

import com.appzoneltd.lastmile.microservice.employee.dao.Employee;
import com.appzoneltd.lastmile.microservice.employee.dto.DriverDTO;
import com.appzoneltd.lastmile.microservice.employee.dto.SearchEndPoint;
import com.appzoneltd.lastmile.microservice.employee.dto.UserProfileDTO;
import com.appzoneltd.lastmile.microservice.employee.dto.UserTypeDto;
import com.appzoneltd.lastmile.microservice.enums.OrderBy;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface EmployeeService {
	Object saveEmployee(Employee employee);

	Object updateEmployee(Employee employee) throws EntityNotFoundException;

	Employee findEmployeeById(Long employeeId);

	List<Employee> findAllEmployeesByPage(int pageCount, int pageNum, OrderBy orderType);

	int markDeleteEmployee(Long employeeId) throws JsonProcessingException;

	UserProfileDTO getEmployeeByPrincipal(Principal principal);
	
//	DriverDTO getDriverProfile(Principal principal);
	
	Object updateEmployeeProfile(UserProfileDTO userProfileDTO);
	
	List<Message> updateUserPassword(UserProfileDTO userProfileDTO);
	
	List<Employee> searchByKey(SearchEndPoint searchEndPoint);

	Integer countSearchByKey(String key);
	
	int countAllEmployees();
	
	List<UserTypeDto> getUserTypesDtos();
}
