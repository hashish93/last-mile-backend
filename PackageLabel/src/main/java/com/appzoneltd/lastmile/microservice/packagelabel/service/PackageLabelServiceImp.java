/**
 * Jul 20, 201612:18:40 PM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.packagelabel.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appzoneltd.lastmile.microservice.packagelabel.dao.PackageLabel;
import com.appzoneltd.lastmile.microservice.packagelabel.dao.PackageLabelRepositoryImp;

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

	private final PackageLabelRepositoryImp packageLabelRepo;

	@Autowired
	public PackageLabelServiceImp(PackageLabelRepositoryImp packageLabelRepositoryImp) {
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
			packageLabels = packageLabelRepo.query();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		} finally {
			return packageLabels;
		}

	}
}
