package com.appzoneltd.lastmile.microservice.exception;

public class FileFilterException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4297809403429570984L;

	public FileFilterException(String message){
		super(message);
	}
	
	public FileFilterException(Throwable throwable){
		super(throwable);
	}
}
