package com.appzoneltd.lastmile.microservice.forgetpassword.dao;

import org.springframework.data.repository.CrudRepository;

import com.appzoneltd.lastmile.microservice.forgetpassword.entity.ForgetPasswordEntity;

public interface ForgetPasswordRepository extends CrudRepository<ForgetPasswordEntity, Long> {

	ForgetPasswordEntity findByCodeGenerated(String generatedCode);

}
