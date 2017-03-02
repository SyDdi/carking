package com.car.exception;

public class PersistenceException extends BaseException {

	private static final long serialVersionUID = 4714701524080982181L;

	public PersistenceException(String message){
		super("persistence exption :" + message );
	}
	
	public PersistenceException(Exception e){
		super("persistence exption :" + e.getMessage() );
	}

	public PersistenceException(String message,Exception e){
		super("persistence exption :" + message + "\n" + e.getMessage(), e);
	}
}
