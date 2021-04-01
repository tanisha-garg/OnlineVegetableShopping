package com.cg.vegetable.mgmt.exceptions;

public class CancelOrderException extends RuntimeException {
	public CancelOrderException(String msg) {
		super(msg);
	}
}
