package com.appzoneltd.lastmile.microservice.details.service;

import java.util.List;

import com.appzoneltd.lastmile.microservice.details.dto.PackageLabel;

/**
 * @author alaa.nabil
 *
 */
public interface PackageLabelService {

	List<PackageLabel> packageLabelFindAll();
}
