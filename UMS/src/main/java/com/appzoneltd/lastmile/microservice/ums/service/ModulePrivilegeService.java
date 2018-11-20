package com.appzoneltd.lastmile.microservice.ums.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.ums.dao.ModulePrivilegeRepository;
import com.appzoneltd.lastmile.microservice.ums.dto.PrivilegeModel;
import com.appzoneltd.lastmile.microservice.ums.dto.SystemModule;
import com.appzoneltd.lastmile.microservice.ums.entity.ModulePrivilegeEntity;
import com.appzoneltd.lastmile.microservice.ums.transformer.ModulePrivilegeTransformer;

@Service
public class ModulePrivilegeService {

	@Autowired
	private ModulePrivilegeRepository modulePrivilegeRepository;

	public List<ModulePrivilegeEntity> findAll() {
		List<ModulePrivilegeEntity> modulePrivilegeEntities = (List<ModulePrivilegeEntity>) modulePrivilegeRepository
				.findAll();
		return modulePrivilegeEntities;
	}

	public List<SystemModule> getAllSystemModules() {

		List<SystemModule> systemModules=new ArrayList<>();		
		List<ModulePrivilegeEntity> modulePrivilegeEntities = findAll();
		if ((modulePrivilegeEntities != null) && (!modulePrivilegeEntities.isEmpty())) {
			systemModules = ModulePrivilegeTransformer
					.getSystemModulesFromEntities(modulePrivilegeEntities);
		}
		return systemModules;
	}

	public ModulePrivilegeEntity findById(Long id){
		return modulePrivilegeRepository.findOne(id);
	}
	
	public List<ModulePrivilegeEntity> transformPriviligesModelsToEntities (List<PrivilegeModel> privilegeModels) {
		 List<ModulePrivilegeEntity>  modulePrivilegeEntities= new ArrayList<>();
		 for(PrivilegeModel privilegeModel : privilegeModels){
			 ModulePrivilegeEntity modulePrivilegeEntity = findById(privilegeModel.getId());
			 if(modulePrivilegeEntity !=null){
				 modulePrivilegeEntities.add(modulePrivilegeEntity);
			 }
		 }
		return modulePrivilegeEntities;
	}
}
