/**
 * Jul 20, 201610:43:49 AM
 *	alaa.nabil
 */
package com.appzoneltd.lastmile.microservice.buildingtype.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author alaa.nabil
 *
 */
@Repository
public interface BuildingTypeJpaRepository extends CrudRepository<BuildingTypeEntity, Long> {

}
