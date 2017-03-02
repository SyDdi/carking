package com.car.exception;

public class ParameterException extends BaseException {

	private static final long serialVersionUID = 5038919081492734715L;

	public ParameterException(String message){
		super("Parameter exption :" + message );
	}

	public ParameterException(Exception e){
		super("Parameter exption :" + e.getMessage() );
	}

	public ParameterException(String message, Exception e){
		super("Parameter exption :" + message + "\n" + e.getMessage(), e);
	}
}
