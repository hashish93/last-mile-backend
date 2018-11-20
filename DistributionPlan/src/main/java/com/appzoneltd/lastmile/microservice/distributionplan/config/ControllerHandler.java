package com.appzoneltd.lastmile.microservice.distributionplan.config;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.appzoneltd.lastmile.microservice.validationhandler.ServiceValidationHandler;

@RestControllerAdvice
public class ControllerHandler extends ServiceValidationHandler {

}
