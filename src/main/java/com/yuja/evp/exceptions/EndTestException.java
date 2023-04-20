package com.yuja.evp.exceptions;

public class EndTestException extends RuntimeException{
	
	public EndTestException() {
        super("The test case did not complete all of its steps");
    }
	
	public EndTestException(String errorMessage) {
        super(errorMessage);
    }
}
