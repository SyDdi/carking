package com.car.exception;

public class OtherException extends BaseException {
	
	private static final long serialVersionUID = -6159746836865584456L;

	public OtherException(String message){
		super("other exption :" + message );
	}
	
	public OtherException(Exception e){
		super("other exption :" + e.getMessage() );
	}

	public OtherException(String message,Exception e){
		super("other exption :" + message + "\n" + e.getMessage(), e);
	}
}
