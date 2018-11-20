package com.appzoneltd.lastmile.privilege.config;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.appzoneltd.lastmile.microservice.validationhandler.ServiceValidationHandler;

@RestControllerAdvice
public class PrivilegeValidationHandler extends ServiceValidationHandler{

}
