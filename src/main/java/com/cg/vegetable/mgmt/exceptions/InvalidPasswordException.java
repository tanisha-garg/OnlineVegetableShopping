package com.cg.vegetable.mgmt.exceptions;

public class InvalidPasswordException extends RuntimeException {
	public InvalidPasswordException(String msg) {
		super(msg);
	}

}
