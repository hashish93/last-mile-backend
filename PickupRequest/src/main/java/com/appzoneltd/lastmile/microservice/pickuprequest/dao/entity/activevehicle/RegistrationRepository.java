package com.appzoneltd.lastmile.microservice.pickuprequest.dao.entity.activevehicle;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author alaa.nabil
 */
@Repository
public interface RegistrationRepository extends CrudRepository<RegistrationModel, Long> {

}
