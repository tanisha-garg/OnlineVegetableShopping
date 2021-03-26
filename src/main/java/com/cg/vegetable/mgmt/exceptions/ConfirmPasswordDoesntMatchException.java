package com.cg.vegetable.mgmt.exceptions;

public class ConfirmPasswordDoesntMatchException extends RuntimeException{
	public ConfirmPasswordDoesntMatchException(String msg) {
		super(msg);
	}

}
