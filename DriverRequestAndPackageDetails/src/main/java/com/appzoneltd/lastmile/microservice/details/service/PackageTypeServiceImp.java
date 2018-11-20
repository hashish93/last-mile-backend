package com.appzoneltd.lastmile.microservice.details.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.details.ObjectMapper;
import com.appzoneltd.lastmile.microservice.details.dao.entity.PackageTypeEntity;
import com.appzoneltd.lastmile.microservice.details.dao.repos.PackageTypeJpaRepository;
import com.appzoneltd.lastmile.microservice.details.dto.PackageType;

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
	private final PackageTypeJpaRepository packageTypeRepo;

	@Autowired
	public PackageTypeServiceImp(PackageTypeJpaRepository packageTypeRepositoryImp) {
		this.packageTypeRepo = packageTypeRepositoryImp;
	}

	@Override
	public List<PackageType> findAllPackageTypes() {
		return ObjectMapper.toPackageTypes((List<PackageTypeEntity>) packageTypeRepo.findAll());
	}

}
