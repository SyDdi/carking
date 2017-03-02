package com.car.exception;

public class ServiceException extends BaseException {

	private static final long serialVersionUID = -5003372086187128343L;

	public ServiceException(String message){
		super("service exption :" + message );
	}
	
	public ServiceException(Exception e){
		super("service exption :" + e.getMessage() );
	}

	public ServiceException(String message,Exception e){
		super("service exption :" + message + "\n" + e.getMessage(), e);
	}
}
