package com.ecom.carrierselector.exception;

public class CSException extends Exception {
	private static final long serialVersionUID = 1L;
	private int errorCode;

	public CSException() {
		super();
	}

	public CSException(String message) {
		super(message);
	}
	
	public CSException(int errorcode, String message) {
		super(message);
		this.setErrorCode(errorcode);
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
}
