package com.appzoneltd.lastmile.microservice.employee.service.employee;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.employee.model.Employee;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.utility.Utils;

@Service
public class OperationBackOfficeService extends EmployeeAbstractService{

	public List<Message> saveOrUpdate(Employee employee, Principal principal) {
		if(employee.getUserId()==null){
			employee.setUserId(Utils.generateUUID());
		}
		employee = SetHubForEmployee(employee, principal);
		return saveUserData(employee);
	}
	
	
}
