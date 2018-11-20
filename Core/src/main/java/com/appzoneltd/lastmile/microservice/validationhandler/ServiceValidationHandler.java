package com.appzoneltd.lastmile.microservice.validationhandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.appzoneltd.lastmile.microservice.enums.MessageType;
import com.appzoneltd.lastmile.microservice.exception.DuplicatedKeyException;
import com.appzoneltd.lastmile.microservice.exception.EntityNotFoundException;
import com.appzoneltd.lastmile.microservice.modelobjects.Message;

/**
 * @author Alaa Nabil
 * 
 *         Abstract Class to be extended to provide validations handling How to
 *         do it : 1- You should extend it 2- Annotate your class with
 *         "@ControllerAdvice"
 * 
 **/
public abstract class ServiceValidationHandler {

	@Autowired
	private MessageSource messageSource;

	/**
	 * Method to get Error validation in services passes errors to process field
	 * error to generate error from message source
	 * 
	 * 
	 **/
	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Map<String, List<String>>> processValidationError(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<FieldError> fieldErrors = result.getFieldErrors();
		Map<String, List<String>> fieldErrorsMessages = new HashMap<String, List<String>>();

		for (FieldError fieldError : fieldErrors) {

			if (fieldErrorsMessages.containsKey(fieldError.getField())) {
				fieldErrorsMessages.get(fieldError.getField()).add(processFieldError(fieldError));
			} else {
				List<String> messages = new ArrayList<>();
				messages.add(processFieldError(fieldError));
				fieldErrorsMessages.put(fieldError.getField(), messages);
			}

		}

		return new ResponseEntity<Map<String, List<String>>>(fieldErrorsMessages, HttpStatus.BAD_REQUEST);
	}

	/**
	 * Method to handle duplicate column in databases .
	 * 
	 * 
	 **/
	@ExceptionHandler(DuplicatedKeyException.class)
	protected ResponseEntity<Message> processDuplicateKeyError(DuplicatedKeyException ex) {
		String message = ex.getMessage();
		String fieldValue = ex.getFieldValue();
		String field = ex.getFieldName();
		return new ResponseEntity<Message>(
				new Message(MessageType.ERROR, field, processErrorMessage(message, fieldValue)), HttpStatus.CONFLICT);
	}

	/**
	 * Method to handle duplicate column in databases .
	 * 
	 * 
	 **/
	@ExceptionHandler(EntityNotFoundException.class)
	protected ResponseEntity<Message> processDuplicateKeyError(EntityNotFoundException ex) {
		String message = ex.getMessage();
		return new ResponseEntity<Message>(new Message(MessageType.ERROR, null, processErrorMessage(message)),
				HttpStatus.BAD_REQUEST);
	}

	/**
	 * 
	 * getting fields error from message source and its error messages with the
	 * current locale
	 * 
	 **/
	private String processFieldError(FieldError fieldError) {
		if (fieldError != null) {

			String msg = messageSource.getMessage(fieldError.getDefaultMessage(), null, fieldError.getDefaultMessage(),
					LocaleContextHolder.getLocale());

			return msg;
		}
		return "";
	}

	/**
	 * Method to get Error Duplicate Key in services and passes this id message
	 * to message source with Locale to get correct message with language
	 * 
	 * 
	 **/
	private String processErrorMessage(String messageId, String fieldValue) {
		if (messageId != null) {
			String errorMsg = messageSource.getMessage(messageId, null, messageId, LocaleContextHolder.getLocale());
			return fieldValue + " " + errorMsg;
		}
		return "";
	}

	private String processErrorMessage(String messageId) {
		if (messageId != null) {
			String errorMsg = messageSource.getMessage(messageId, null, messageId, LocaleContextHolder.getLocale());
			return errorMsg;
		}
		return "";
	}
}
