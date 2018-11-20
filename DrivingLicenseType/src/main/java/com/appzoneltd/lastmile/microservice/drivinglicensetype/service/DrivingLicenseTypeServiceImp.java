package com.appzoneltd.lastmile.microservice.drivinglicensetype.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.drivinglicensetype.dao.DrivingLicenseType;
import com.appzoneltd.lastmile.microservice.drivinglicensetype.dao.DrivingLicenseTypeRepositoryImp;

@Service
public class DrivingLicenseTypeServiceImp implements DrivingLicenseTypeService {

	private final static Logger LOGGER = LoggerFactory.getLogger(DrivingLicenseType.class);

	private final DrivingLicenseTypeRepositoryImp drivingLicenseTypeRepositoryImp;

	/**
	 * Autowiring our Driving LicenseType Repository
	 * 
	 **/
	@Autowired
	public DrivingLicenseTypeServiceImp(DrivingLicenseTypeRepositoryImp drivingLicenseTypeRepositoryImp) {
		this.drivingLicenseTypeRepositoryImp = drivingLicenseTypeRepositoryImp;
	}

	@SuppressWarnings("finally")
	@Override
	public List<DrivingLicenseType> drivingLicenseTypeFindAll() {
		List<DrivingLicenseType> drivingLicenseTypes = null;
		try {
			drivingLicenseTypes = drivingLicenseTypeRepositoryImp.query();
			Collections.sort(drivingLicenseTypes, new Comparator<DrivingLicenseType>() {
				@Override
				public int compare(DrivingLicenseType o1, DrivingLicenseType o2) {
					return o1.getLicenseType().compareToIgnoreCase(o2.getLicenseType());
				}
			});

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			return drivingLicenseTypes;
		}
	}

}
