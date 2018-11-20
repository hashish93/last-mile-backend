package com.appzoneltd.lastmile.microservices.devices.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservices.devices.dao.BrandDao;
import com.appzoneltd.lastmile.microservices.devices.entity.BrandEntity;


@Service
public class BrandService {

	@Autowired
	private BrandDao brandDao;

	public List<BrandEntity> findAllBrand() {

		return  (List<BrandEntity>) brandDao.findAll();

	}

}
