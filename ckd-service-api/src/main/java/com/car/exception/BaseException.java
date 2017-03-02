package com.car.exception;


public class BaseException extends RuntimeException{

	private static final long serialVersionUID = 3437188522711747456L;

	private String code;

	public BaseException(){
		super();
	}

	public BaseException(Exception e){
		super(e);
	}

    public BaseException(String message) {
    	super(message);
    }

	public BaseException(String message, String code){
        super(message);
        this.code = code;
	}


    public BaseException(String message, Exception e) {
        super(message + "\n" + e.getMessage(), e);
    }

    public BaseException(String message, Exception e, String code) {
        super(message + "\n" + e.getMessage(), e);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
	}

}
