/**
 * Jul 20, 201612:18:40 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.buildingtype.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.buildingtype.dao.BuildingTypeEntity;
import com.appzoneltd.lastmile.microservice.buildingtype.dao.BuildingTypeJpaRepository;

/**
 * @author alaa.nabil
 *
 */
@Service
public class BuildingTypeServiceImpl implements BuildingTypeService {

	/**
	 * Autowiring our {@link BuildingTypeJpaRepository}
	 * <p>
	 * Injecting repository to create crud operations
	 * </p>
	 * 
	 **/
	@Autowired
	private BuildingTypeJpaRepository buildingTypeJpaRepository;

	/**
	 * Method to invoke find all Building Types and get all of them .
	 * 
	 * @param companyId
	 * @return {@link List<BuildingType>}
	 */
	@Override
	public List<BuildingTypeEntity> getAllBuildingtypes() {
		return (List<BuildingTypeEntity>) buildingTypeJpaRepository.findAll();
	}
}
