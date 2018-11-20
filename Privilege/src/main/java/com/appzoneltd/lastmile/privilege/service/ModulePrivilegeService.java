package com.appzoneltd.lastmile.privilege.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.privilege.dao.ModulePrivilegeDao;
import com.appzoneltd.lastmile.privilege.model.ModulePrivilege;

@Service
public class ModulePrivilegeService {

	@Autowired
	private ModulePrivilegeDao modulePrivilegeDao;
	
	public List<ModulePrivilege> findAll(){
		return (List<ModulePrivilege>) modulePrivilegeDao.findAll();
	}
	
	public ModulePrivilege findById(Integer id){
		return modulePrivilegeDao.findById(id);
	}
	
}
