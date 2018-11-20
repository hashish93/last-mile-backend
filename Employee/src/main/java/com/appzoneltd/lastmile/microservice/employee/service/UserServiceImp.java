package com.appzoneltd.lastmile.microservice.employee.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.employee.dao.Employee;
import com.appzoneltd.lastmile.microservice.employee.dao.UserRepositoryImp;
import com.appzoneltd.lastmile.microservice.enums.OrderBy;

@Service
public class UserServiceImp {

	@Autowired
	private UserRepositoryImp userRepositoryImp ;
	
	
	public List<Employee> findAllEmployeeWithPage ( int pageNum, int pageCount,  OrderBy orderType){
		return userRepositoryImp.queryWithPaging(pageNum, pageCount, orderType);
		
	} 
}
