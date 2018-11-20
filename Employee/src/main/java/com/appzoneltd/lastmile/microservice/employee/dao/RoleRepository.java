package com.appzoneltd.lastmile.microservice.employee.dao;

import org.springframework.data.repository.CrudRepository;
import javax.transaction.Transactional;

@Transactional
public interface RoleRepository extends CrudRepository<RoleEntity, Long> {

}