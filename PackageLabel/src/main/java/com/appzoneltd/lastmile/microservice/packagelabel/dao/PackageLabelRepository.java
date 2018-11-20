/**
 * Jul 20, 201610:43:49 AM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.packagelabel.dao;

/**
 * @author alaa.nabil
 *
 */
public interface PackageLabelRepository {
	String SQL_SELECT_ALL = "SELECT package_label_id, label, created, version " + "FROM lastmile_core.package_label";
}
