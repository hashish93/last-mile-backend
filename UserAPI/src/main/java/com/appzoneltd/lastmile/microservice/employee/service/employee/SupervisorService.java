package com.appzoneltd.lastmile.microservice.employee.service.employee;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.employee.entity.RoleEntity;
import com.appzoneltd.lastmile.microservice.employee.entity.UserTypeEntity;
import com.appzoneltd.lastmile.microservice.employee.model.Employee;
import com.appzoneltd.lastmile.microservice.employee.service.RoleService;
import com.appzoneltd.lastmile.microservice.employee.service.UserTypeService;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.utility.Utils;

@Service
public class SupervisorService extends EmployeeAbstractService{

	@Autowired
	private RoleService roleService;

	@Autowired
	private UserTypeService userTypeService;
	
	public List<Message> saveOrUpdate(Employee employee, Principal principal) {
		
		UserTypeEntity userTypeEntity = userTypeService.getUserTypeEntity(employee.getUserTypeId());
		if(userTypeEntity !=null){
			System.out.println(userTypeEntity.toString());
			RoleEntity roleEntity = roleService.getRoleByName(userTypeEntity.getName());
			if(roleEntity !=null){
				List<Long> roles= employee.getRoles();
				if(roles == null){
					roles = new ArrayList<>();
				}
				roles.add(roleEntity.getRoleId());
				employee.setRoles(roles);
			}
		}
		if(employee.getUserId()==null){
			employee.setUserId(Utils.generateUUID());
		}
		
		return saveUserData(employee);
	}
	
}
