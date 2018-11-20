package com.appzoneltd.lastmile.microservice.packagelabel.service;

import java.util.List;

import com.appzoneltd.lastmile.microservice.packagelabel.dao.PackageLabel;

/**
 * @author alaa.nabil
 *
 */
public interface PackageLabelService {

	List<PackageLabel> packageLabelFindAll();
}
