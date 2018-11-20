package com.appzoneltd.lastmile.microservice.packagetype.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.packagetype.dao.PackageType;
import com.appzoneltd.lastmile.microservice.packagetype.dao.PackageTypeRepositoryImp;

/**
 * 
 * @author Alaa Nabil
 * 
 *         Find All Package Types service to return all Package Types in the
 *         system also has a service to return all count of Package Types .
 * 
 * 
 **/
@Service
public class PackageTypeServiceImp implements PackageTypeService {
	/**
	 * Autowiring our Package Repository
	 * 
	 **/
	private final PackageTypeRepositoryImp packageTypeRepo;

	@Autowired
	public PackageTypeServiceImp(PackageTypeRepositoryImp packageTypeRepositoryImp) {
		this.packageTypeRepo = packageTypeRepositoryImp;
	}

	@Override
	public List<PackageType> findAllPackageTypes() {
		return packageTypeRepo.query();
	}

}
