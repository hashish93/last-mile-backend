package com.appzoneltd.lastmile.microservice.details;

import com.appzoneltd.lastmile.microservice.details.service.component.ConfirmationCodeError;
import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;
import com.appzoneltd.lastmile.microservice.validationhandler.ServiceValidationHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DriverRequestAndPackageDetailsValidationHandler extends ServiceValidationHandler {

    @ExceptionHandler(ConfirmationCodeError.class)
    protected ResponseEntity<Message> confirmationCodeException(ConfirmationCodeError ex) {
        return new ResponseEntity<Message>(
                new Message(MessageType.ERROR, ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
