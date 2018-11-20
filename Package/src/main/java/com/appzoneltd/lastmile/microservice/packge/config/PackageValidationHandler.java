package com.appzoneltd.lastmile.microservice.packge.config;

import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.appzoneltd.lastmile.microservice.validationhandler.ServiceValidationHandler;


/**
 * @author Alaa Nabil
 * 
 *         Class that extends abstract class ServiceValidationHandler To
 *         provide handling Vehicle Validations .
 * 
 **/
@RestControllerAdvice
public class PackageValidationHandler extends ServiceValidationHandler {

}
