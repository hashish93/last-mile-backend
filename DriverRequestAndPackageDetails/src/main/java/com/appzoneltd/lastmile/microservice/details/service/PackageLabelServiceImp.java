/**
 * Jul 20, 201612:18:40 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.details.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.details.ObjectMapper;
import com.appzoneltd.lastmile.microservice.details.dao.entity.PackageLabelEntity;
import com.appzoneltd.lastmile.microservice.details.dao.repos.PackageLabelJpaRepository;
import com.appzoneltd.lastmile.microservice.details.dto.PackageLabel;

/**
 * @author alaa.nabil
 *
 */
@Service
public class PackageLabelServiceImp implements PackageLabelService {

	private final static Logger LOGGER = LoggerFactory.getLogger(PackageLabelServiceImp.class);

	/**
	 * Autowiring our Package Label DAO
	 * 
	 **/

	private final PackageLabelJpaRepository packageLabelRepo;

	@Autowired
	public PackageLabelServiceImp(PackageLabelJpaRepository packageLabelRepositoryImp) {
		this.packageLabelRepo = packageLabelRepositoryImp;
	}

	/**
	 * Method to invoke fin all Package labels and get all of them .
	 * 
	 * @param companyId
	 * @return {@link List<PackageLabel>}
	 */
	@SuppressWarnings("finally")
	@Override
	public List<PackageLabel> packageLabelFindAll() {
		List<PackageLabel> packageLabels = null;
		try {
			packageLabels = ObjectMapper.toPackageLabels((List<PackageLabelEntity>) packageLabelRepo.findAll());
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			return packageLabels;
		}

	}
}
