package com.appzoneltd.lastmile.microservice.exception;

/**
 * @author Alaa Nabil
 * 
 *         class extends exception class to throw an exception of duplicated
 *         keys
 * 
 **/
public class DuplicatedKeyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final String fieldName;
	private final String fieldValue;
	private final String message;

	public DuplicatedKeyException(String message, String fieldName, String fieldValue) {
		this.message = message;
		this.fieldValue = fieldValue;
		this.fieldName = fieldName;
	}

	public String getMessage() {
		return message;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public String getFieldName() {
		return fieldName;
	}

}
