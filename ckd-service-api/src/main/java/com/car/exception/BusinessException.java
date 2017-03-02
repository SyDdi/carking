package com.car.exception;

public class BusinessException extends BaseException {

	private static final long serialVersionUID = 5038919081492734715L;

	public BusinessException(String message){
		super("business exption :" + message );
	}
	
	public BusinessException(Exception e){
		super("business exption :" + e.getMessage() );
	}

	public BusinessException(String message,Exception e){
		super("business exption :" + message + "\n" + e.getMessage(), e);
	}
}
